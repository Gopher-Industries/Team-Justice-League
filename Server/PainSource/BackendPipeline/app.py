from flask import Flask, render_template, request, redirect, url_for, abort, send_from_directory, send_file
import cv2
import os
import glob
import base64
import numpy as np
#from pain_detector import PainDetector
from PainAssessment.PainAssessmentCNN.pain_detector import PainDetector
from PainAssessment.PainAssessmentVGG.assess_pain import initialize_model, do_pain_assessment
from FaceRecognition import face_detection, face_recognition
from FaceQuality.Main import ImageQuality
import requests
from werkzeug.utils import secure_filename
import imghdr
from torchvision.transforms import functional as F
import json

app = Flask(__name__)

model_1 = None
model_2 = None
model_3 = None

app.config['UPLOAD_EXTENSIONS'] = ['.jpg', '.png']
app.config['REFERENCE_FRAME_PATH'] = 'FaceRecognition/SampleBaseImages/'
app.config['TARGET_FRAME_PATH'] = 'TargetFrames'


def init_app():
    global model_2, model_3
    _model2_path = 'PainAssessment/PainAssessmentCNN/checkpoints/50342566/50343918_3/model_epoch4.pt'
    model_2 = PainDetector(image_size=160, checkpoint_path=_model2_path, num_outputs=40)

    _model3_path = 'PainAssessment/PainAssessmentVGG/models/2.pth'
    model_3, input_size = initialize_model(_model3_path)

    face_detection.init_face_detection()
    face_recognition.init_face_recognition()

@app.route('/test',methods=['GET'])
def dummy():
    data = {
        "Pain": True,
        "PainRate": 5.0,
        "id": "test_name",
        "base_image_id": "test_id"
    }
    return data

@app.route('/webtest')
def display_target():
    files = os.listdir(app.config['TARGET_FRAME_PATH'])
    for f in files:
        read_path = os.path.join(app.config['TARGET_FRAME_PATH'], f)
        os.remove(read_path)
    files = os.listdir(app.config['TARGET_FRAME_PATH'])
    return render_template('webtest.html', files=files)

@app.route('/webtest', methods=['POST'])
def webtest():
    model_type = "model2"
    uploaded_file = request.files['file']
    filename = secure_filename(uploaded_file.filename)
    if filename != '':
        file_ext = os.path.splitext(filename)[1]
        uploaded_file.save(os.path.join(app.config['TARGET_FRAME_PATH'], filename))
        read_path = os.path.join(app.config['TARGET_FRAME_PATH'], filename)
    image = cv2.imread(read_path)
    data = PainRateProcess(image, os.path.basename(read_path), model_type)
    pain_estimate = data["PainRate"]
    print("Pain :", pain_estimate)
    print("Data:", data)
    files_ref = data["base_image_id"]
    files_target = os.listdir(app.config['TARGET_FRAME_PATH'])
    return render_template('result.html', output=pain_estimate, files_ref=files_ref, files_target=files_target)

@app.route('/uploads/<filename>')
def upload(filename):
    return send_from_directory(app.config['REFERENCE_FRAME_PATH'], filename)

@app.route('/uploads_target/<filename>')
def upload_target(filename):
    return send_from_directory(app.config['TARGET_FRAME_PATH'], filename)


def PainRateProcess(img, ID, model_type, quality_check = True):
    if quality_check:
        #TODO: Quality block seems to change image within, make sure the quality block works on a copy of image and not the original image
        quality_json = json.loads(ImageQuality.do_main(img.copy()))
        print(quality_json)

        if (quality_json["PASS"] != "PASS"):
            data = {
                "Pain": quality_json["PASS"],
                "PainRate": quality_json["PASS"],
                "id": ID,
                "base_image_id": "NA"
            }
            return data
        
    #Do Face Detection
    face_image = face_detection.detect_face(img)
    print("Face detected:", face_image.dtype)
    #Fetch Base Image
    #get base image from DB, face recognition module
    face_id = face_recognition.search_face_id(face_image)

    if face_id == "NA":
        data = {
            "Pain": "No Base Image",
            "PainRate": "No Base Image",
            "id": ID,
            "base_image_id": face_id,
        }
        return data

    base_image = face_recognition.get_base_image(face_id)

    #if all passed, then do pain assessment
    if model_type == "T1_2022" or model_type == "model1":
        #TODO: Add pkl from T1 RnD folder
        data = {
            "Pain": "Model not integrated",
            "PainRate": "Model not integrated",
            "id": ID,
            "base_image_id": face_id
        }
        pass

    if model_type == "T2_2022" or model_type == "model2":
        model_2.add_references([base_image])

        #TODO: Change to use face_image and offload face detection, quality to Android
        pain_estimate = model_2.predict_pain(img) #does face detection within, T2 2022 pipeline
        pain_thresh = 2.0
        if (pain_estimate > pain_thresh):
            pain_flag = True
        else:
            pain_flag = False
        data = {
            "Pain": pain_flag,
            "PainRate": float(pain_estimate),
            "id": ID,
            "base_image_id": face_id
        }

    if model_type == "T3_2022" or model_type == "model3":
        #This can be avoided, as the assesspain function transforms the image to tensor so no need to 
        #convert in opencv/numpy image, kept it to make the function generic
        opencv_face_image = face_image.numpy().transpose(1, 2, 0)
        #opencv_face_image = opencv_face_image[:, :, ::-1].copy()
        opencv_face_image = cv2.cvtColor(opencv_face_image, cv2.COLOR_RGB2BGR)
        pain_estimate = do_pain_assessment(model_3, opencv_face_image)
        pain_thresh = 1.0
        if (pain_estimate > pain_thresh):
            pain_flag = True
        else:
            pain_flag = False
        data = {
            "Pain": pain_flag,
            "PainRate": float(pain_estimate),
            "id": ID,
            "base_image_id": face_id
        }
    return data

@app.route('/getPainRate',methods=['POST'])
def GetPainRate():
    if request.method == 'POST':
        model_type = None
        quality_check = False
        if "quality" in request.form:
            quality_check = request.form.get("quality")
        model_type = request.form.get("model_type")
        ID = request.form.get("identity")
        image = request.files['image'].read()
        nparr = np.frombuffer(image, np.uint8)
        img = cv2.imdecode(nparr, cv2.IMREAD_ANYCOLOR)

        data = PainRateProcess(img, ID, model_type, quality_check)
        return data
    else:
        return "Invalid Method"

if __name__ == "__main__":
    init_app()
    #app.debug = True
    app.run(host='0.0.0.0',port=5000)
