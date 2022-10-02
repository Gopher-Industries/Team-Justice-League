from flask import Flask, render_template, request, redirect, url_for, abort, send_from_directory
import cv2
import os
import glob
import base64
import numpy as np
#import PainCNN16 as pain_detector
from pain_detector import PainDetector
import requests
from werkzeug.utils import secure_filename
import imghdr
app = Flask(__name__)

#app.config['MAX_CONTENT_LENGTH'] = 1024 * 1024
app.config['UPLOAD_EXTENSIONS'] = ['.jpg', '.png', '.gif']
app.config['REFERENCE_FRAME_PATH'] = 'ReferenceFrames'
app.config['TARGET_FRAME_PATH'] = 'TargetFrames'

pain_detector = PainDetector(image_size=160, checkpoint_path='checkpoints/50342566/50343918_3/model_epoch4.pt', num_outputs=40)
print ("Loaded model")

data = {
  "Pain": True,
  "PainRate": 5.0,
  "id": "test_name"
}




def pretty_print_POST(req):
    """
    At this point it is completely built and ready
    to be fired; it is "prepared".

    However pay attention at the formatting used in
    this function because it is programmed to be pretty
    printed and may differ from the actual request.
    """
    print('{}\n{}\r\n{}\r\n\r\n{}'.format(
        '-----------START-----------',
        req.method + ' ' + req.url,
        '\r\n'.join('{}: {}'.format(k, v) for k, v in req.headers.items()),
        req.body,
    ))

@app.route('/')
def index():
    files = os.listdir(app.config['REFERENCE_FRAME_PATH'])
    for f in files:
        read_path = os.path.join(app.config['REFERENCE_FRAME_PATH'], f)
        os.remove(read_path)
    files = os.listdir(app.config['REFERENCE_FRAME_PATH'])
    return render_template('index.html', files=files)

@app.route('/', methods=['POST'])
def upload_files():
    for uploaded_file in request.files.getlist('file'):
        filename = secure_filename(uploaded_file.filename)
        if filename != '':
            file_ext = os.path.splitext(filename)[1]
            uploaded_file.save(os.path.join(app.config['REFERENCE_FRAME_PATH'], filename))
            read_path = os.path.join(app.config['REFERENCE_FRAME_PATH'], filename)

            image = cv2.imread(read_path)
            pain_detector.add_references([image])
            print("Added ref images")
    return redirect(url_for('webtest'))

@app.route('/uploads/<filename>')
def upload(filename):
    return send_from_directory(app.config['REFERENCE_FRAME_PATH'], filename)

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
    uploaded_file = request.files['file']
    filename = secure_filename(uploaded_file.filename)
    if filename != '':
        file_ext = os.path.splitext(filename)[1]
        uploaded_file.save(os.path.join(app.config['TARGET_FRAME_PATH'], filename))
        read_path = os.path.join(app.config['TARGET_FRAME_PATH'], filename)
    image = cv2.imread(read_path)
    pain_estimate = pain_detector.predict_pain(image)
    print("Pain :", pain_estimate)
    #return redirect(url_for('display_target'))
    files_ref = os.listdir(app.config['REFERENCE_FRAME_PATH'])
    files_target = os.listdir(app.config['TARGET_FRAME_PATH'])
    return render_template('result.html', output=pain_estimate, files_ref=files_ref, files_target=files_target)

@app.route('/uploads_target/<filename>')
def upload_target(filename):
    return send_from_directory(app.config['TARGET_FRAME_PATH'], filename)


@app.route('/test', methods=['GET','POST'])
def salvador():
    #print("test")
    data = {
        "Pain": True,
        "PainRate": 5.0,
        "id": "test_name"
    }
    #print("test urlfor: ", url_for('webtest'))
    return data


@app.route('/sendBaseImage',methods=['POST'])
def SendBaseImage():
    if request.method == 'POST':
        ID = request.form.get("identity")
        image = request.files['image'].read()
        nparr = np.frombuffer(image, np.uint8)
        img = cv2.imdecode(nparr, cv2.IMREAD_ANYCOLOR)
        print ("Base image received id: ", ID, "dimensions: ", img.shape)
        pain_detector.add_references([img, img])
        data = {
            "msg":  "Img Registered",
            "id": ID
        }
        return data
    else:
        return "Invalid"

@app.route('/getPainRate',methods=['POST'])
def GetPainRate():
    if request.method == 'POST':
        ID = request.form.get("identity")
        image = request.files['image'].read()
        nparr = np.frombuffer(image, np.uint8)
        print (nparr.shape)
        img = cv2.imdecode(nparr, cv2.IMREAD_ANYCOLOR)
        print ("Test image received id: ", ID, "dimensions: ", img.shape)
        pain_estimate = pain_detector.predict_pain(img)
        pain_thresh = 2.0
        if (pain_estimate > pain_thresh):
            pain_flag = True
        else:
            pain_flag = False
        data = {
            "Pain": pain_flag,
            "PainRate": float(pain_estimate),
            "id": ID
        }
        return data
    else:
        return "Invalid"

if __name__ == "__main__":
    app.run(host='0.0.0.0',port=5000)
