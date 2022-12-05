#%pip install nbformat
# !pip install ipynb

from FaceQuality_Utils import *
from FaceDetector import *
# from ipynb.fs.full.FaceDetector import *
# from FaceDetector import *
# %run FaceDetector.ipynb
import numpy as np
import cv2
import pandas as pd
import csv
import os
import json
import pprint as pp
import _uuid
import itertools
import sys


def main():
    LIST = []
    Headers = ['Image Path',\
            'Image Brightness - Status',\
            'Image Brightness - Level',\
            'Image Focus - Status',\
            'Image Focus - Level',\
            'Face Distance - Status',\
            'Face Distance - Distance',\
            'Confidence',\
            'Nose Location - X',\
            'Nose Location - Y',\
            'PASS']


    RESULTS = {'Image Path':'',\
            'Brightness': \
                {'Status': '',\
                'Level': ''},\

            'Focus': \
                {'Status': '',\
                'Level': ''},\

            'Face': \
                {'Status': '',\
                'Distance': '',\
                'Count':'',\
                'Confidence': '',\
                'Nose': \
                    {'X_loc': '',\
                    'Y_loc': ''}},\
            'PASS': ""}

    Input = pd.read_csv('faces.csv', index_col = False)
    detector = FaceDetector()

    #Export to CSV - Create file and write headers
    with open('TEST1.csv', 'w', newline='') as f:
        writer = csv.DictWriter(f, fieldnames=RESULTS.keys())
        writer.writeheader()

        for i in range(len(Input)):
            #save path as image - later change to incoming image
            image = cv2.imread(Input["path"][i])
            path = Input["path"][i]
            root, ext = os.path.splitext(path)
            fileName = root.split('/')
            fileName = fileName[-1]
            RESULTS['Image Path']= fileName + ext


            #brightness test
            BS ,BL = testBrightness(image)
            RESULTS['Brightness']['Status']=BS
            RESULTS['Brightness']['Level']= BL

            #Focus test
            FS, FL = blurrinesDetection(image)
            RESULTS['Focus']['Status']= FS
            RESULTS['Focus']['Level']= FL

            #Head count test
            Dist_status , H_percent , count = faceDetect(image, i)
            RESULTS['Face']['Status'] = Dist_status
            RESULTS['Face']['Distance'] = H_percent
            RESULTS['Face']['Count']= count
            
            #Overall quality check
            if  BS == 'Good' and FS == 'Sharp' and Dist_status == 'Good':
                print(f'checking image {i+1}')
                #Find face mesh and nose position to determine if face is not close to centre

                #find face mesh
                FaceMesh =  detector.findFaceMesh(image)   
                
                #find nose Location on image            
                scroe, Xloc, Yloc = detector.findNose(image, i)          
            
                # cv2.imshow('image', FaceMesh)
                cv2.imwrite('FaceMesh{}.jpg'.format(i+1), FaceMesh)
            
                RESULTS['Face']['Confidence'] = scroe
                RESULTS['Face']['Nose']['X_loc'] = Xloc
                RESULTS['Face']['Nose']['Y_loc'] = Yloc
                RESULTS['PASS'] = True
            
            else:
                RESULTS['Face']['Confidence'] = ""
                RESULTS['Face']['Nose']['X_loc'] = ""
                RESULTS['Face']['Nose']['Y_loc'] = ""
                RESULTS['PASS'] = False
                # print(json.dumps(RESULTS, indent=4))
                
            #prepare data to be sent as JSON
            DataJson = json.dumps(RESULTS, indent= 4)
            #*************  EXPORT DataJson  ******************** 

            # Export to CSV - ROW
            writer.writerow(RESULTS) #***WORKS***




        
if __name__ == "__main__":
    main()

