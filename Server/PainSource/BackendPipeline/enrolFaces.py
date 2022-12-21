from FaceRecognition import face_detection, face_recognition
import glob

base_image_path = "FaceRecognition/SampleBaseImages/"
json_path = "./FaceRecognition/"

def do_offline_enrollment():
    face_recognition.init_face_recognition()
    images_list = glob.glob(base_image_path+"/*")
    face_recognition.enrol_faces(images_list, json_path)


def main():
    do_offline_enrollment()

if __name__ == "__main__":
    do_offline_enrollment()