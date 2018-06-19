import numpy as np
import os
#import six.moves.urllib as urllib
import sys
#import tarfile
import tensorflow as tf
import zipfile#first run tar file unzip karne ke liye
import sys
from collections import defaultdict
from io import StringIO
from matplotlib import pyplot as plt
from PIL import Image
import face_recognition





import cv2
cap = cv2.VideoCapture(0)






from utils import label_map_util

from utils import visualization_utils as vis_util
#FOR LABELLING ON REALTIME OPENCV OUTPUT --REMOVE DOG BEFORE HACKATHON PRESENTATION


MODEL_NAME = 'ssd_mobilenet_v1_coco_2017_11_17'#inception model ki accuracy check karni hai 
MODEL_FILE = MODEL_NAME + '.tar.gz'



PATH_TO_CKPT = MODEL_NAME + '/frozen_inference_graph.pb'


PATH_TO_LABELS = os.path.join('data', 'mscoco_label_map.pbtxt')
#dog ka label delete karna hai


NUM_CLASSES = 90



#HAS TO BE INTEGRATED IN UJJWAL's CODE remember in every code Graph has to be called only once----speed ka issue
detection_graph = tf.Graph()
with detection_graph.as_default():
  od_graph_def = tf.GraphDef()#genr mach for metdat
  with tf.gfile.GFile(PATH_TO_CKPT, 'rb') as fid:
    serialized_graph = fid.read()
    od_graph_def.ParseFromString(serialized_graph)
    tf.import_graph_def(od_graph_def, name='')





label_map = label_map_util.load_labelmap(PATH_TO_LABELS)
categories = label_map_util.convert_label_map_to_categories(label_map, max_num_classes=NUM_CLASSES, use_display_name=True)
category_index = label_map_util.create_category_index(categories)





with detection_graph.as_default():
  with tf.Session(graph=detection_graph) as sess:
    while True:
      ret, image_np = cap.read()
#tensorflow image has got to be in np array --speed ka issue
      image_np_expanded = np.expand_dims(image_np, axis=0)
      image_tensor = detection_graph.get_tensor_by_name('image_tensor:0')


      boxes = detection_graph.get_tensor_by_name('detection_boxes:0')

      scores = detection_graph.get_tensor_by_name('detection_scores:0')

      classes = detection_graph.get_tensor_by_name('detection_classes:0')
      #print(classes)

      num_detections = detection_graph.get_tensor_by_name('num_detections:0')


#FACE RECOGNITION CODE OF DEBTANU GOES HERE



      # Actual detection.
      (boxes, scores, classes, num_detections) = sess.run(
          [boxes, scores, classes, num_detections],
          feed_dict={image_tensor: image_np_expanded})
      s_class = classes[scores > 0.5]
      #print(len(s_class))
      x=0
      for i in s_class:
        if category_index.get(i)=='person':
          x+=1
    #  print(x,'x')    
      for i in s_class:
        face_locations = face_recognition.face_locations(image_np)
        print("I found {} face(s) in this photograph.".format(len(face_locations)))
        print(category_index.get(i))

        if(category_index.get(i)['name'])==sys.argv[1]:#:'person'
            x+=1 
            print(x)#sys.argv[1]+'found')
            
        vis_util.visualize_boxes_and_labels_on_image_array(image_np,np.squeeze(boxes),np.squeeze(classes).astype(np.int32),np.squeeze(scores),category_index,use_normalized_coordinates=True,line_thickness=8)
        cv2.imshow('object detection', cv2.resize(image_np, (800,600)))
        if cv2.waitKey(25) & 0xFF == ord('q'):
            cv2.destroyAllWindows()
            break
