from flask import Flask, render_template, request, redirect, url_for, abort, send_from_directory
import cv2
import os
import glob
import base64
import numpy as np
#from pain_detector import PainDetector
from PainAssessment.PainAssessmentCNN.pain_detector import PainDetector
import requests
from werkzeug.utils import secure_filename
import imghdr

app = Flask(__name__)

@app.route('/test',methods=['GET, POST'])
def dummy():
    #print("test")
    data = {
        "Pain": True,
        "PainRate": 5.0,
        "id": "test_name"
    }
    return data

#@app.route('/getPainRate',methods=['POST'])
#def GetPainRate('/getPainRate', methods=['POST']):
#    if request.method == 'POST':
#        ID = request.form.get("identity")
#        image = request.files['image'].read()
#        nparr = np.frombuffer(image, np.uint8)
#        print (nparr.shape)
#        img = cv2.imdecode(nparr, cv2.IMREAD_ANYCOLOR)
#        print ("Test image received id: ", ID, "dimensions: ", img.shape)
#
#        #Do Quality Check
#
#        #Fetch Base Image
#
#
#        #if all passed, then do pain assessment
#        if model_id == "T1_2022":
#            #TODO: Add pkl from T1 RnD folder
#            pass
#        if model_id == "T2_2022":
#            pain_estimate = pain_detector.predict_pain(img)
#            pain_thresh = 2.0
#            if (pain_estimate > pain_thresh):
#                pain_flag = True
#            else:
#                pain_flag = False
#            data = {
#                "Pain": pain_flag,
#                "PainRate": float(pain_estimate),
#                "id": ID
#            }
#
#        if model_id == "T3_2022":
#            pain_estimate = 5.0
#
#            pain_thresh = 2.0
#            if (pain_estimate > pain_thresh):
#                pain_flag = True
#            else:
#                pain_flag = False
#            data = {
#                "Pain": pain_flag,
#                "PainRate": float(pain_estimate),
#                "id": ID
#            }
#
#        return data
#    else:
#        return "Invalid Method"

if __name__ == "__main__":
    app.run(host='0.0.0.0',port=5000)
