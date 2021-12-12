import cv2
import sys
import numpy as np
import os
import time

global frame
cwd = os.getcwd()               # save images to filepath

def main(argv):
    hand_captured = False       # know whether 'c' has been pressed
    cap = cv2.VideoCapture(0)   # camera input
    one_third = 0.45            # constants
    two_third = 0.55
    hsv = 0                     # hsv needed throughout entire function
    hsv_upper, hsv_lower = 0, 0
    # begin iterating through camera input
    while cap.isOpened():
    # capture frames and copies
        ret, frame = cap.read()                 # frame - original camera input
        # flip frames so that user will intuitively understand camera feedback
        frame = cv2.flip(frame, 1)
        
        frame_annotations = frame.copy()        # annotations - to capture HSV
        
        w, h = frame.shape[1], frame.shape[0]   # width, height of frame
        
        # blue rectangle to indicate where user should place palm
        # cv2.rectangle(frame_annotations, (int(x_1), int(y_1)),
        #          (int(x_2), int(y_2)), (255, 0, 0), 2)
        x1 = (w/2-w/4)
        y1 = (h/2-h/4)

        x2 = x1 + w/2
        y2 = y1 + h/2

        cv2.line(frame_annotations, (0,0), (x1,y1), (255,0,0))
        cv2.line(frame_annotations, (w,0), (x2,y1), (255,0,0))
        cv2.line(frame_annotations, (0,h), (x1,y2), (255,0,0))
        cv2.line(frame_annotations, (w,h), (x2,y2), (255,0,0))
        
        # convert RGB input to HSV
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
        
        # simple text display on frame_annotations
        hand_text_pt = (1*frame.shape[1]/5, 6*frame.shape[0]/9)
        # cv2.putText(frame_annotations,'Please center your palm on the blue rectangle', \
        #             hand_text_pt, cv2.FONT_HERSHEY_SIMPLEX, .5, (255, 255, 255), 1)
        # hand_text_pt = (1*frame.shape[1]/5, 7*frame.shape[0]/9)

        # show annotations
        cv2.imshow('annotated_original', frame_annotations)
        
        # break program if 'q' is entered
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
        
    # release resources
    cap.release()
    cv2.destroyAllWindows()

if __name__ == '__main__':
    main(sys.argv)