import sys
sys.path.insert(1, 'RnD/Face Quality')

import FaceQuality_Utils
import cv2
import unittest

# ********** TESTING - IMAGE BRIGHTNESS ****************

def test_brightnesttTest_return_flag():
    instance = FaceQuality_Utils
    image = cv2.imread('RnD/Face Quality/tests/50percent_brightness.png')
    a, b, c = instance.BrightnessTest(image)
    assert isinstance(c, bool)

def test_brightnesttTest_50_gray():
    instance = FaceQuality_Utils
    image = cv2.imread('RnD/Face Quality/tests/50percent_brightness.png')
    a, b, c = instance.BrightnessTest(image)
    #128 is 50% brightness level
    assert b == 128.0 and isinstance(b, float),f"Brightness level not 50% expected, got: {b}"
  
def test_findDark_too_bright():
    instance = FaceQuality_Utils
    image = cv2.imread('RnD/Face Quality/tests/20percent_brightness.png')
    a, b, c = instance.BrightnessTest(image)
    #80 is 20% brightness level
    assert b == 80.0 and isinstance(b, float),f"Brightness level not 80% expected, got: {b}"

def test_findDark_too_dark():
    instance = FaceQuality_Utils
    image = cv2.imread('RnD/Face Quality/tests/80percent_brightness.png')
    a, b, c = instance.BrightnessTest(image)
    #236 is 80% brightness level
    assert b == 236.0 and isinstance(b, float),f"Brightness level not 50% expected, got: {b}"
  

# ********** TESTING - IMAGE FOCUS ****************


def test_blurrinessDetection_invalid_image():
    instance = FaceQuality_Utils
    image = [1, 2] # <-------  not np.ndarray
    status, focus, flag = instance.blurrinesDetection(image)
    assert status == "Invalid image" and flag is False,f'Unexpected image type'

def test_blurrinessDetection_not_focused():
    instance = FaceQuality_Utils
    image = cv2.imread('RnD/Face Quality/tests/TestImage1.png')
    status, focus, flag = instance.blurrinesDetection(image)
    assert focus < instance.THRESHOLD, f'Image is in focus'

def test_blurrinessDetection_in_focus():
    instance = FaceQuality_Utils
    image = cv2.imread('RnD/Face Quality/tests/TestImage2.png')
    status, focus, flag = instance.blurrinesDetection(image)
    assert focus > instance.THRESHOLD, f'Image is NOT in focus'

# ********** TESTING - FORMATTING ****************

def test_generate_csv():
    pass #to be completed 