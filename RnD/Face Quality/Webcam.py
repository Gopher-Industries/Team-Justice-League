import cv2
import os
from datetime import datetime

def get_image():
    path = 'RnD/Face Quality/Results/Temp_webcam_images/'
    # class webcam():

    cam = cv2.VideoCapture(1)
    cv2.namedWindow("Pain detection application")
    img_counter = 0

    while img_counter < 1:
        ret, frame = cam.read()
        if not ret:
            print("failed to grab frame")
            break
        cv2.imshow("Pain detection application", frame)

        k = cv2.waitKey(1)
        # if k%256 == 27:
        #     # ESC pressed
        #     print("Escape hit, closing...") 
        #     break
        if k%256 == 32:
            # SPACE pressed
            dateTimenow = f"{datetime.now() :%Y-%m-%d_%H-%M-%S}"

            img_name = f"opencv_frame_{dateTimenow}.png"
            cv2.imwrite(path + img_name , frame)
            print("{} written!".format(img_name))
            img_counter += 1
    
    cam.release()
    cv2.destroyAllWindows()
    return img_name, frame




