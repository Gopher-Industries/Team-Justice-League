import cv2
import mediapipe as mp

# useful packeges
import numpy as np
import pandas as pd
import math

class FaceDetector():
    DETECTIONCONF = 0.5
    TRACKINGCONF = 0.5
    MAXFACE = 2
    

    def __init__(self, staticMode = True, noFaces = MAXFACE, minDetectCon = DETECTIONCONF,minTrackCon = TRACKINGCONF):
        self.staticMode = staticMode
        self.noFaces = noFaces
        self.minDetectCon = minDetectCon
        self.minTrackCon = minTrackCon
        
        self.mpDraw = mp.solutions.drawing_utils
        self.mpFaceMesh = mp.solutions.face_mesh
        self.face_detection = mp.solutions.face_detection
        
        self.faceMesh = self.mpFaceMesh.FaceMesh( static_image_mode=self.staticMode, max_num_faces=self.noFaces, min_detection_confidence=self.minDetectCon, min_tracking_confidence=self.minTrackCon)
        self.DrawSpec = self.mpDraw.DrawingSpec(thickness= 1.5, circle_radius= 3.5)

    def findFaceMesh(self, image):
        self.results = self.faceMesh.process(cv2.cvtColor(image, cv2.COLOR_BGR2RGB)) #was imgRGB

        if self.results.multi_face_landmarks:
            for faceLMs in self.results.multi_face_landmarks:
                self.mpDraw.draw_landmarks(image, faceLMs, self.mpFaceMesh.FACEMESH_TESSELATION)    

        return image

        
    
    def findNose(self, image, index):
        with self.face_detection.FaceDetection(model_selection=0, min_detection_confidence=0.5) as face_detection:
            self.FaceResults = face_detection.process(cv2.cvtColor(image, cv2.COLOR_BGR2RGB)) 
            # NoseLocation = image.copy()

            if self.FaceResults.detections:
                for detection in self.FaceResults.detections:
                    print(f'Nose tip Face{index+1} :')

                    score = round(detection.score[0]*100,2)
                    print(f'face score: {score}%')
                    
                    #draw X on nose tip
                    height, width, channels = image.shape 
                    X = math.floor(width * detection.location_data.relative_keypoints[2].x )
                    Y = math.floor(height * detection.location_data.relative_keypoints[2].y )

                    X_len = 100
                    # Drawing the lines
                    cv2.line(image, (X - X_len, Y - X_len), (X + X_len, Y + X_len), (0, 255, 0), 5)
                    cv2.line(image, (X + X_len, Y - X_len), (X - X_len, Y + X_len), (0, 255, 0), 5)



                    return score, X, Y
            else:
                print('Nose tip: NOT FOUND')
                return '', '', ''

        

    