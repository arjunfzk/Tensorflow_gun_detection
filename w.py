

import face_recognition
import cv2

import os

video_capture = cv2.VideoCapture(0)

t_image = face_recognition.load_image_file("03.jpg")
t_image2 = face_recognition.load_image_file("4.jpg")
t_image3 = face_recognition.load_image_file("5.jpg")
#t_image4 = face_recognition.load_image_file("xxxxx.png")





t_face_encoding = face_recognition.face_encodings(t_image)[0]
t_face1_encoding = face_recognition.face_encodings(t_image2)[0]
t_face2_encoding = face_recognition.face_encodings(t_image3)[0]
#t_face3_encoding = face_recognition.face_encodings(t_image4)[0]




known_face_encodings = [
    t_face_encoding,t_face1_encoding,t_face2_encoding]
known_face_names = [
'tiplu','tiplu','tiplu'
]
while True:

    ret, frame = video_capture.read()

    
    rgb_frame = frame[:, :, ::-1]


    face_locations = face_recognition.face_locations(frame)
    face_encodings = face_recognition.face_encodings(frame, face_locations)


    for (top, right, bottom, left), face_encoding in zip(face_locations, face_encodings):

        matches = face_recognition.compare_faces(known_face_encodings, face_encoding)

        name = "Unknown"																																																																																																																							

        n=0
        if True in matches:
            n=0
            print("tiplu found")
            first_match_index = matches.index(True)
            name = known_face_names[first_match_index]
        else:
            n+=1
            if n>10:
                os.system('java Client1')
                print('not found')
                
        cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)


        cv2.rectangle(frame, (left, bottom - 35), (right, bottom), (0, 0, 255), cv2.FILLED)
        font = cv2.FONT_HERSHEY_DUPLEX
        cv2.putText(frame, name, (left + 6, bottom - 6), font, 1.0, (255, 255, 255), 1)

     


    #print("USER IN FRAME")
    
    print()
    cv2.imshow('Video', frame)

    # Hit 'q' on the keyboard to quit!
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
 

video_capture.release()
cv2.destroyAllWindows()
