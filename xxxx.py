import cv2
from darkflow.net.build import TFNet
import numpy as np
import time
import os
option = {
    'model': 'cfg/tiny-yolo-voc-1c.cfg',
    'load': -1,
    'threshold': 0.15,
    'gpu': 0.5
}

tfnet = TFNet(option)

capture = cv2.VideoCapture('25fps.avi')#25fps.avi,ltest2.MP4,test1.MP4
colors = [tuple(255 * np.random.rand(3)) for i in range(5)]
count = 0

while (capture.isOpened()):
    stime = time.time()
    ret, frame = capture.read()
    results = tfnet.return_predict(frame)
    if ret:
        for color, result in zip(colors, results):
            tl = (result['topleft']['x'], result['topleft']['y'])
            br = (result['bottomright']['x'], result['bottomright']['y'])
            label = result['label']
            
            if count == 2:
                #time.sleep(2)
                #os.system("python Skype.py")
                
                os.system("java Client1")
            count += 1
            print(label)
            frame = cv2.rectangle(frame, tl, br, color, 7)
            frame = cv2.putText(frame, label, tl, cv2.FONT_HERSHEY_COMPLEX, 1, (0, 0, 0), 2)
        cv2.imshow('frame', frame)
       # print('FPS {:.1f}'.format(1 / (time.time() - stime)))
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        capture.release()
        cv2.destroyAllWindows()
        break





    """python flow --model cfg/tiny-yolo-1c.cfg --load -1 --train --annotationlast/annotations --dataset last/image --gpu 0.7"""
