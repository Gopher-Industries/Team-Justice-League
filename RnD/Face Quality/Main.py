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

def main():
  
    df = pd.read_csv('faces.csv', index_col = False)
    data = [{'Image Path': 'N/A',\
            'Brightness status':'N/A',\
            'Brightness level': 0,\
            'Focus status': 'N/A',\
            'Focus level': 0, \
            'Distance status': 'N/A', \
            'Distance': 0, \
            'Face count': 0, \
            'conf_score': 0, \
            'X_Y': 0, \
            'Quality': 'FAIL'}]
        
    RESULTS = pd.DataFrame(data)
    RESULTS.to_csv('ImageQuality.csv', index=True, header=True)

    detector = FaceDetector()

    for i in range(len(df)):
        #save path as image - later change to incoming image
        image = cv2.imread(df["path"][i])
        path = df["path"][i]
        root, ext = os.path.splitext(path)
        fileName = root.split('/')
        fileName = fileName[-1]
        RESULTS['Image Path']= fileName + ext
        RESULTS['Quality']= 'FAIL'

        #brightness test
        BS ,BL = testBrightness(image)
        RESULTS['Brightness status']= BS
        RESULTS['Brightness level']= BL

        #Focus test
        FS, FL = blurrinesDetection(image)
        RESULTS['Focus status']= FS
        RESULTS['Focus level']= FL

        #Head count test
        Dist_status , H_percent , count = faceDetect(image, i)
        RESULTS['Distance status']= Dist_status
        RESULTS['Distance']= H_percent
        RESULTS['Face count']= count
        
        #Overall quality check
        if  BS == 'Good' and FS == 'Sharp' and Dist_status == 'Good':
            print(f'checking image {i+1}')
            RESULTS['Quality'] = 'PASS'
            #Find face mesh and nose position to determine if face is not close to centre

            #find face mesh
            FaceMesh =  detector.findFaceMesh(image)   
            
            #find nose location on image            
            scroe, Xloc, Yloc = detector.findNose(image, i)          
        
            # cv2.imshow('image', FaceMesh)
            cv2.imwrite('FaceMesh{}.jpg'.format(i+1), FaceMesh)
        
            RESULTS['conf_score']= scroe
            RESULTS['X_Y']= f'X{Xloc}, Y{Yloc}'

        else:
            RESULTS['Quality'] = 'FAIL'
            RESULTS['conf_score']= ""
            RESULTS['X_Y']= ""

        #write results to CSV output
        RESULTS.to_csv('ImageQuality.csv', mode ='a', header=False)

if __name__ == "__main__":
    main()

