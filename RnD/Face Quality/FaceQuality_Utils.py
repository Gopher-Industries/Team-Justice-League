import numpy as np
import cv2
import pandas as pd
import os 
import math
from cv2 import IMREAD_COLOR,IMREAD_UNCHANGED


# useful packeges
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt 
import seaborn as sns
import skimage

# statistic packeges
from scipy.ndimage import variance
from skimage import io
from skimage.color import rgb2gray
from skimage.filters import laplace
from skimage.transform import resize

    


THRESHOLD = 500
BIGHTNESS_LEVEL_LOW = 85
BIGHTNESS_LEVEL_HIGH = 170


# ********** IMAGE BRIGHTNESS ****************

def BrightnessTest(img):
    frame_gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    Brightness_status , Brightness_level, flag = algo_findDark(frame_gray)
    return Brightness_status , Brightness_level, flag

def algo_findDark(image):
    blur = cv2.blur(image, (5, 5))
    mean = round(np.mean(blur),2)
    if mean < BIGHTNESS_LEVEL_LOW:
        return 'Dark', mean , True

    elif mean > BIGHTNESS_LEVEL_LOW and mean < BIGHTNESS_LEVEL_HIGH:
        return 'Good', mean, False

    else:
        return 'Too Bright' , mean, True


# ********** IMAGE FOCUS ****************


def variance_of_laplacian(img2):
    # compute the Laplacian of the image and then return the focus
    # measure, which is simply the variance of the Laplacian
    gray = cv2.cvtColor(img2, cv2.COLOR_RGB2BGR)
    return cv2.Laplacian(gray, cv2.CV_64F).var()

# def BGR2RGB(BGR_img):
#     # turning BGR pixel color to RGB
#     rgb_image = cv2.cvtColor(BGR_img, cv2.COLOR_BGR2RGB)
#     return rgb_image

def blurrinesDetection(img):

    #check if valid image
    if not isinstance(img, np.ndarray):
        return 'Invalid image', 0, False

    img = cv2.resize(img, (400, 320)) 
    Focus_Status = "Sharp"

    Focus_Level = variance_of_laplacian(img)
    if Focus_Level < THRESHOLD:
        Focus_Status = "Blurry"
        return Focus_Status , round(Focus_Level,2), True
    else:
        return Focus_Status , round(Focus_Level,2), False




# def laplaceEdgeVariance(path):
#     # Load image
#     img = cv2.imread(path)

#     # Resize image
#     img = resize(img, (400, 600))
    
#     # Gray-Scale change
#     img = rgb2gray(img)

#     # Edge detection
#     edge_laplace = laplace(img, ksize=5)
#     return [variance(edge_laplace),np.amax(edge_laplace)]

def HeadPercent(img, cropH, cropW):
    height, width, channels = img.shape 
    overall = height*width
    cropped = cropH*cropW
    percent = round(cropped/overall*100,2)

    if percent < 10:
        return 'face too far', percent
    elif percent > 70:
        return 'face too close', percent
    else:
        return 'Good', percent

# ********** FACE DETECTION ****************



def faceDetect(img):
    gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    # Load the cascade

    face_cascade = cv2.CascadeClassifier('/Users/nadav/Desktop/Uni/2023_tri_1/TeamB/Github/Team-Justice-League/RnD/Face Quality/haarcascade_frontalface_default.xml')
    # Detect faces
    faces = face_cascade.detectMultiScale(gray, 1.1,  5 )
    
    count = len(faces)
    # if no face found
    if count < 1:
        return 'N/A' , 'N/A' , 'N/A'

        #if more than 1 face found, select the largest
    elif count == 1:
        x, y, w, h = faces[0]
        dist_status, percent= HeadPercent(img, h, w)
        # cv2.rectangle(img, (x, y), (x+w, y+h), (0, 0, 255), 2)
        faces = img[y:y + h, x:x + w]
        
        #       Write out image to directory (only save face im image)
        #  **** Send image here to serve  ****
        if dist_status == 'Good':
            # cv2.imwrite('{}.jpg'.format(index+1), faces)        
            # # To print faces
            return dist_status, percent , 1, False
        else:
            return dist_status, percent , 1, True

    # if more than 1 face in image      
    else:
        largest = [0 , 0]
        for i,  (x,y,h,w) in enumerate(faces):
            a = HeadPercent(img, h, w)
            if  a[1] > largest[0]:
                largest = [a[1], i]

        # cv2.imwrite('{}largest.jpg'.format(index+1), faces[largest[1]])        
        # # To print faces
        x1, y1, w1, h1 = faces[largest[1]]
        LH_dist, LH_percent =  HeadPercent(img, w1, h1)
        return LH_dist, LH_percent , count, False


# ********** FORMATTING ****************

def normalize_json(data: dict) -> dict:
  
    new_data = dict()
    for key, value in data.items():
        if not isinstance(value, dict):
            new_data[key] = value
        else:
            for k, v in value.items():
                if not isinstance(v, dict):
                    new_data[key + "_" + k] = v
                else:
                    for a, b in v.items():
                        new_data[key + "_" + k + "_" + a] = b
    return new_data

def generate_csv_data(data, i) -> str:
  
    # Defining CSV columns in a list to maintain
    # the order
    csv_columns = data.keys()
    
    if i == 0:
        # Generate the first row of CSV 
        csv_data = ",".join(csv_columns) + "\n"
  
        # Generate the single record present
        new_row = list()
        for col in csv_columns:
            new_row.append(str(data[col]))
    
        # Concatenate the record with the column information 
        # in CSV format
        csv_data += ",".join(new_row) + "\n"
    
        return csv_data
    else:
        new_row = list()
        for col in csv_columns:
            new_row.append(str(data[col]))
        return new_row

def normalize_json(data: dict) -> dict:
  
    new_data = dict()
    for key, value in data.items():
        if not isinstance(value, dict):
            new_data[key] = value
        else:
            for k, v in value.items():
                if not isinstance(v, dict):
                    new_data[key + "_" + k] = v
                else:
                    for a, b in v.items():
                        new_data[key + "_" + k + "_" + a] = b
    return new_data

def generate_csv_data(data, i) -> str:
  
    # Defining CSV columns in a list to maintain
    # the order
    csv_columns = data.keys()
    
    if i == 0:
        # Generate the first row of CSV 
        csv_data = ",".join(csv_columns) + "\n"
  
        # Generate the single record present
        new_row = list()
        for col in csv_columns:
            new_row.append(str(data[col]))
    
        # Concatenate the record with the column information 
        # in CSV format
        csv_data += ",".join(new_row) + "\n"
    
        return csv_data
    else:
        new_row = list()
        for col in csv_columns:
            new_row.append(str(data[col]))
        return new_row