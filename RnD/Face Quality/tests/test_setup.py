import sys
sys.path.insert(1, '/Users/nadav/Desktop/Uni/2022_tri_3/TeamA/ML/Project/Team-Justice-League/RnD/Face Quality')

import Setup
import pandas 

def test_checkpath():
    newSetup = Setup
    path = newSetup.get_path()
    assert '.csv' in path
   

def test_read_data():
    newSetup = Setup
    test = newSetup.read_data()
    assert isinstance(test, pandas.core.frame.DataFrame)

 
def test_get_image_extenstion():
    newSetup = Setup
    Input = newSetup.read_data()
    file = Input["path"][0]
    fileName , ext = newSetup.get_image_name(file)
    assert "png" or 'jpg' in ext




    