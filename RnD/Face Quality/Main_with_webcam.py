from FaceQuality_Utils import *
from FaceDetector import *
import Setup
import Webcam
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


class ImageQuality():
    
    def main():
        detector = FaceDetector()
        
        RESULTS = {'Image Path':'',
                'Brightness': 
                    {'Status': '',
                    'Level': ''},
                'Focus': 
                    {'Status': '',
                    'Level': ''},
                'Face': 
                    {'Status': '',
                    'Distance': '',
                    'Count':'',
                    'Confidence': '',
                    'Nose': 
                        {'X_loc': '',
                        'Y_loc': ''}},
                'PASS': ""}
    
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

        path = 'RnD/Face Quality/Results/'
        #Export to CSV - Create file and write headers
        with open(path + 'TEST3.csv', 'w+',newline='\n', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=RESULTS.keys())
            writer.writeheader()
            
            fileName , image = Webcam.get_image()
            cv2.imshow('temp',image)
            #save image name
            RESULTS['Image Path']= f'{fileName}.jpg'

            #brightness test
            BS ,BL, BrightnessFlag = BrightnessTest(image) 
            RESULTS['Brightness']['Status']=BS
            RESULTS['Brightness']['Level']= BL
            if BrightnessFlag:
                RESULTS['PASS']= 'FAIL - Brightness'

            #Focus test
            FS, FL, FocusFlag = blurrinesDetection(image)
            RESULTS['Focus']['Status']= FS
            RESULTS['Focus']['Level']= FL
            if FocusFlag:
                RESULTS['PASS']= 'FAIL - Focus'

            #Head count test
            Dist_status , H_percent , count ,DistFlag = faceDetect(image)
            RESULTS['Face']['Status'] = Dist_status
            RESULTS['Face']['Distance'] = H_percent
            RESULTS['Face']['Count']= count

            if DistFlag:
                RESULTS['PASS']= 'FAIL - Distance'
            #Overall quality check
            if  BS == 'Good' and FS == 'Sharp' and Dist_status == 'Good':
                
                #Find face mesh and nose position to determine if face is not close to centre
                #find face mesh
                FaceMesh =  detector.findFaceMesh(image)   
                
                #find nose Location on image            
                scroe, Xloc, Yloc = detector.findNose(image)          
            
                ### ENABLE BELOW TO SAVE IMAGE WITH FACEMESH + NOSE CROSS     
                cv2.imwrite(path + 'FaceMesh.jpg', FaceMesh)

                RESULTS['Face']['Confidence'] = scroe
                RESULTS['Face']['Nose']['X_loc'] = Xloc
                RESULTS['Face']['Nose']['Y_loc'] = Yloc
                RESULTS['PASS'] = 'PASS'
            
            else:
                RESULTS['Face']['Confidence'] = ""
                RESULTS['Face']['Nose']['X_loc'] = ""
                RESULTS['Face']['Nose']['Y_loc'] = ""

                # print(json.dumps(RESULTS, indent=4))
                
            #prepare data to be sent as JSON
            DataJson = json.dumps(RESULTS, indent= 4)
            #*************  EXPORT DataJson  ******************** 
        
            # Export to CSV - ROW
            # Setup.save_data(RESULTS)
            writer.writerow(RESULTS) #***WORKS*** 

    if __name__ == "__main__":
        main()

