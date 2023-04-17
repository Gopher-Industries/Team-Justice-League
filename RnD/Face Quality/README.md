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


![image](https://user-images.githubusercontent.com/62866537/205485368-a13e0ede-b1e7-4057-945a-33c7ad023dac.png)

<img width="1170" alt="project path" src="https://user-images.githubusercontent.com/62866537/205485392-f0ebffff-12a0-4799-bde2-83a608321163.png">


#### Sample JSON output
```json
{
    "Image Path": "Test1.png",
    "Brightness": {
        "Status": "Good",
        "Level": 133.28
    },
    "Focus": {
        "Status": "Sharp",
        "Level": 1032.37
    },
    "Face": {
        "Status": "Good",
        "Distance": 26.44,
        "Count": 1,
        "Confidence": 86.68,
        "Nose": {
            "X_loc": 1289,
            "Y_loc": 1611
        }
    },
    "PASS": true
}
```


##### NOTE 1.0
Added JSON file exporting to allow for ease of compilation and the ability to send as a single object from mobile to backend. 

##### NOTE 1.1
Added Where each test fails to the PASS/FAIL column. This will indicate to us and the (and in turn) the user where the image needs to be improved in order to be processed for pain assessment.

## Unit testing V1.0
<img width="689" alt="Unit testing_V1" src="https://user-images.githubusercontent.com/62866537/206883860-326bea33-b5b9-4cd6-8917-b88ce78184f7.png">



##### NOTE V2.0
Added webcam integration to allow for ease of testing while application is not working. 
This will allow us to test with images taken from webcam and send to check for pain assessment.