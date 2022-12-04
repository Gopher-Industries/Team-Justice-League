# Image Quality Feature

The image quality feature has the following checks:

- Image focus

- Image brightness

- User distance from camera

- Check if a human is in the image

- User count in image

- Nose locatio (to determine how far from centre the face is)

- Confidence percent (of face)


### Data table (CSV output)
| Image Path | Brightness status | 	Brightness level  |  Focus status | Focus level |  Distance status | Distance |  Face count | Confidence |   Nose X_Y |  Quality | 
|--|--|--|--|--|--|--|--|--|--|--|
| Test1.png | good | 133.28 | Sharp | 1032.37 | Good | 26.44 | 1 | 86.68 | X1289, T1611  | PASS | 
| Test2.png | good | 118.22 | Blurry | 196.04 | Face too far | 12.92 | 1 |  |  | FAIL |
| Test3.png | Good | 129.1 | Sharp | 1341.99 | Good | 48.47 | 1 | 80.14 | X740, Y1080  | PASS |


With the above tests, we are able to only parse through images which pass all the minimum requirements and save on unnecessary computation done on the GCP. 


#### Data stream

This feature is currently implemented by reading in data from a local CSV file that will keep all the images paths. This will act as the stream of images coming from users.

  

#### Feature integration into current project
The image quality will be positioned right after each user takes an image. All the computation will be done on the mobile device as it is lightweight enough to not disturbe the user experience.

#### Sample input / output files

  
![FaceDetectOutputSample]{FaceMesh18.jpg "Output file"}
