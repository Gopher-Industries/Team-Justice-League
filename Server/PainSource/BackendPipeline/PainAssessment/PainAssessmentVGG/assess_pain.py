import sys
import torch
import torch.nn as nn
import torch.optim as optim
import numpy as np
import torchvision
from torchvision import datasets, models, transforms
import time
import os
import copy
import importlib
import sklearn.metrics
import matplotlib.pyplot as plt
import random

def set_parameter_requires_grad(model, feature_extracting):
    if feature_extracting:
        for param in model.parameters():
            param.requires_grad = False
    

def initialize_model(model_path, model_name="vgg", num_classes=10, feature_extract=False, use_pretrained=True):
    # Initialize these variables which will be set in this if statement. Each of these
    #   variables is model specific.
    model = None
    input_size = 0

    if model_name == "vgg":
        """ VGG16_bn
        """
        if use_pretrained:
          model = models.vgg16_bn(weights="VGG16_BN_Weights.DEFAULT")
        else:
          model = models.vgg16_bn()

        set_parameter_requires_grad(model, feature_extract)
        num_ftrs = model.classifier[6].in_features
        model.classifier[6] = nn.Linear(num_ftrs,num_classes)
        input_size = 224
        device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
        model = model.to(device)
        model.load_state_dict(torch.load(model_path, map_location=device))
    else:
        print("Invalid model name, exiting...")
        exit()

    return model, input_size


def get_model_result(model, image):
  transform = transforms.Compose([
      transforms.ToTensor()
      ])
  device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
  inputs = transform(image)
  inputs = inputs.to(device)
  inputs = inputs.unsqueeze(0)
  since = time.time()
  last_layer = list(model.children())[-1]
  try:
    last_layer = last_layer[-1]
  except:
    last_layer = last_layer
  my_embedding = torch.zeros((inputs.shape[0],last_layer.in_features))
  def fun(m, i, o): 
    my_embedding.copy_(i[0].data)
  h = last_layer.register_forward_hook(fun)
  with torch.set_grad_enabled(False):
    # Get model outputs
    outputs = model(inputs) * torch.FloatTensor([16] + [5]*9).to(device)
  h.remove()
  pspi = (outputs.data.cpu().numpy()[0][0])
  return pspi

def do_pain_assessment(model_ft, face_image):
  pspi = get_model_result(model_ft, face_image)    
  return pspi

###test this script
def main():
  import cv2
  model_ft = None
  model_path = "models/2.pth"
  test_image_path = ("./example-target-frame.png")
  face_image = cv2.imread(test_image_path)

  model_name = "vgg"
  num_classes = 10
  feature_extract = False
  input_size = 0

  if (model_ft is None):
    print("Loading Pain model")
    model_ft, input_size = initialize_model(model_path, model_name, num_classes, feature_extract, use_pretrained=True)
  else:
    print("Using preloaded model")

  pspi = do_pain_assessment(model_ft, face_image)
  print(pspi)
  pspi = do_pain_assessment(model_ft, face_image)
  print(pspi)

if __name__=="__main__":
  main()