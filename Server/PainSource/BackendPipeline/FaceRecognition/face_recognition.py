from facenet_pytorch import MTCNN, InceptionResnetV1
import torch
from torch.utils.data import DataLoader
from torchvision import datasets
import numpy as np
import pandas as pd
import os
from scipy.spatial import distance

from PIL import Image

import json
from json import JSONEncoder
import numpy

import cv2

resnet = None
workers = 0
faces_in_memory = []
json_path = "" #Offline for base images, UI should have an option to enrol faces
images_path = ""

##Only for enrol faces, remove once DB is set up
device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
mtcnn = MTCNN(margin=40, select_largest=False, post_process=False, device=device)

class NumpyArrayEncoder(JSONEncoder):
    def default(self, obj):
        if isinstance(obj, numpy.ndarray):
            return obj.tolist()
        return JSONEncoder.default(self, obj)

def init_face_recognition():
    global resnet, workers, faces_in_memory
    workers = 0 if os.name == 'nt' else 4
    resnet = InceptionResnetV1(pretrained='vggface2').eval()
    json_path = 
    with open(json_path + '/faces.json') as json_file:
        faces_in_memory = json.loads(json_file)

def get_face_embedding(face_img):
    face_embedding = None
    face_embedding = resnet(face_img.unsqueeze(0))
    return face_embedding


def compute_face_distance(face_vec1, face_vec2):
    face_dist = -1.0
    face_dist = distance.euclidean(face_vec1, face_vec2)
    return face_dist

def search_face_id(query_face_image):
    face_id = -1
    query_embedding = get_face_embedding(query_face_image)
    _dist = []
    for face in faces_in_memory:
        _id = face['id']
        _embedding = face['embedding'] 
        #Storing all distances incase we need to access top N results
        _dist.append(compute_face_distance(query_embedding, _embedding))     
    face_index = _dist.index(min(_dist))
    face_id = faces_in_memory[face_index]
    return face_id

#this is a temporary helper method to read faces from dict and create an offline json file for base image faces
#TODO: Add function to enrol a single face/multiple faces, provide access from UI
def enrol_faces(images_path_list, json_path):
    numpyData = []
    for file in enumerate(images_path_list):
        frame = cv2.imread(file)
        frame = Image.fromarray(frame)
        face = mtcnn(frame)
        _base_embedding = get_face_embedding(face)
        numpyData.append({"id":file, "embedding": _base_embedding})

    out_file = open(json_path + "/faces.json", "w")
    encodedNumpyData = json.dumps(numpyData, out_file, cls=NumpyArrayEncoder)  # use dump() to write array into file
    out_file.close()
    return json_path

#This function should ideally interact with DB 
#but due to lack of DB this function just reads the images stored in file for demonstration
#TODO: Fetch face id from DB and location to read the base image
def get_base_image(face_id):
    base_image = None
    #for now face id is the image name
    #TODO: path_to_face_image = face_id_dict[face_id]
    path_to_face_image = images_path + face_id
    base_image = cv2.imread(path_to_face_image)
    return base_image