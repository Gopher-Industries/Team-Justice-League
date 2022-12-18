import torch
from facenet_pytorch import MTCNN, InceptionResnetV1
import cv2
from PIL import Image
import glob

mtcnn = None
def init_face_detection():
    global mtcnn
    device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
    mtcnn = MTCNN(margin=40, select_largest=False, post_process=False, device='cuda:0')
    print('Running on device: {}'.format(device))

##TODO: Add face alignment block
def detect_face(frame):
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    frame = Image.fromarray(frame)
    face = mtcnn(frame)
    return face
