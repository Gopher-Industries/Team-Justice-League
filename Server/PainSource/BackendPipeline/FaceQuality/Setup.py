import numpy as np
import pandas as pd
import os



def get_path():
    return r'/Users/nadav/Desktop/Uni/2022_tri_3/TeamA/ML/Project/RnD/Face Quality/faces.csv'

def read_data():
    path = get_path()
    CSV_data = pd.read_csv(path, index_col = False)
    print(type(CSV_data), 'type')
    return CSV_data


    
def get_image_name(file):
    root, ext = os.path.splitext(file)
    fileName = root.split('/')
    fileName = fileName[-1]
    return fileName, ext

