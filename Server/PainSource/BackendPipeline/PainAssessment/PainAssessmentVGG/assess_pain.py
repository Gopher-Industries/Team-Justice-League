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
import imp
import sklearn.metrics
import matplotlib.pyplot as plt
import random


def get_model_result(model, image):
  transform = transforms.Compose([
      transforms.ToTensor()
      ])  
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