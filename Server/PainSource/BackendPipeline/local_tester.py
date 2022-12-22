###Simple script to test backend pipeline
import requests
import json
import cv2
import base64
import os.path

model_type = "model3"

def post_encoded_image(url, headers, imgPath):
    img = open(imgPath, 'rb').read()
    req_id =  os.path.basename(imgPath)
    file = {'image': img}
    data = {'model_type': model_type, 'identity': req_id}
    r = requests.post(url, data=data, files=file)
    print(r)
    print (json.loads(r.text))


url = "http://127.0.0.1"
port = "5000"
endpoint = "/getPainRate"
#register_endpoint = "/sendBaseImage"

test_url = url + ":" + port + endpoint
#register_url = url + ":" + port + register_endpoint


content_type = 'multipart/form-data'
headers = {'content-type': content_type}

#Uncomment if need to test T2 2022 version
#imgPath = "C:\\Users\\singh\\Documents\\MastersAppliedAIDeakin\\SIT764_Project_A\\POC\\pain_detection_demo-master\\example_frames\\example-reference-frame.png"
#post_encoded_image(register_url, headers, imgPath)

testImgPath = "./example-target-frame.png"
post_encoded_image(test_url, headers, testImgPath)

def UT_CheckAPI():
    utFlag = True
    try:
        img = open(imgPath, 'rb').read()
        req_id =  os.path.basename(imgPath)
        file = {'image': img}
        data = {'model_type': model_type, 'identity': req_id}
        r = requests.post(url, data=data, files=file)
        print(r)
        print (json.loads(r.text))   
    except:
        print("An exception occurred")
        utFlag = False
    return utFlag