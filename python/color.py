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
        frame_debug = frame.copy()              # debug - COM, defects
        frame_checklist = frame.copy()          # checklist - to list conditions
        
        x_1 = one_third * frame.shape[1]        # x_1...y_2 used for rect dimensions
        y_1 = one_third * frame.shape[0]
        y_2 = two_third * frame.shape[0]
        x_2 = two_third * frame.shape[1]
        
        # blue rectangle to indicate where user should place palm
        cv2.rectangle(frame_annotations, (int(x_1), int(y_1)),
                 (int(x_2), int(y_2)), (255, 0, 0), 2)
        
        # convert RGB input to HSV
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
        
        # simple text display on frame_annotations
        hand_text_pt = (1*frame.shape[1]/5, 6*frame.shape[0]/9)
        cv2.putText(frame_annotations,'Please center your palm on the blue rectangle', \
                    hand_text_pt, cv2.FONT_HERSHEY_SIMPLEX, .5, (255, 255, 255), 1)
        hand_text_pt = (1*frame.shape[1]/5, 7*frame.shape[0]/9)
        cv2.putText(frame_annotations,'Press \'c\' to continue',\
                    hand_text_pt,cv2.FONT_HERSHEY_SIMPLEX,.5, (255, 255, 255), 1)
        # show annotations
        cv2.imshow('annotated_original', frame_annotations)
        
        # once user presses 'c', begin evaluating HSV range of palm
        if cv2.waitKey(1) & 0xFF == ord('c'):
            hand_captured = True
            # chosen_box - where pixels of interest are located
            chosen_box = hsv[int(y_1):int(y_2), int(x_1):int(x_2)]
            # compute min and max HSV values of chosen pixels
            min_h = np.amin(chosen_box[:, :, 0])
            min_s = np.amin(chosen_box[:, :, 1])
            min_v = np.amin(chosen_box[:, :, 2])
            max_h = np.amax(chosen_box[:, :, 0])
            max_s = np.amax(chosen_box[:, :, 1])
            max_v = np.amax(chosen_box[:, :, 2])
            min_v = min_v * 1.2 # some offset needed for poor lighting in room
            # save HSV range into upper and lower bounds
            hsv_lower = np.array([min_h, min_s, \
                                  min_v], dtype="uint8")
            hsv_upper = np.array([max_h, max_s, \
                                  max_v], dtype="uint8")
        
        # once HSV range is calculated, begin image processing
        if hand_captured:
            # filter out background noise
            filtered = cv2.blur(hsv, (3,3))
            # create binary mask using HSV range
            mask = cv2.inRange(filtered, hsv_lower, hsv_upper)   
            
            # kernel matrices for morphological transformation    
            kernel_sqr = np.ones((11,11), np.uint8)
            kernel_ell = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5,5))
            # perform dilation and erosion to filter out background noise
            filtered = cv2.dilate(mask, kernel_ell, iterations = 4)
            filtered = cv2.erode(filtered, kernel_sqr, iterations = 1)            
            # threshold once more to get cleaned out image
            ret, thresh = cv2.threshold(filtered, 127, 255, 0)
            # show mask to user
            cv2.imshow('mask', thresh)
            """
            # contours found in threshold image
            contours = cv2.findContours(thresh, cv2.RETR_EXTERNAL, \
                                        cv2.CHAIN_APPROX_SIMPLE)[-2]
            
            maximum_area = 500
            contour_i = 0
            # get number of contours and then iterate to find max area found
            n = range(len(contours))
            for i in n:
                count = contours[i]
                area = cv2.contourArea(count)
                # max area found
                if(area > maximum_area):
                    maximum_area = area
                    contour_i = i  
            
            # getting COM of hand
            if not contours:
                continue
            if len(contours) > 0:
                #find largest contour
                max_contour = max(contours, key=cv2.contourArea)
                
            # find the area of the largest contour
            area = cv2.contourArea(max_contour)
            # get moment values
            M = cv2.moments(max_contour)
            if (M['m00'] == 0):
                continue
            # compute center of mass (user palm)
            x_com = int(M['m10'] / M['m00'])
            y_com = int(M['m01'] / M['m00'])
            com = (x_com, y_com)
            
            cv2.circle(frame_debug, (x_com, y_com), 20, (255, 255, 255), 5)
            # cv2.imshow('debug_frame', frame_debug)

        	  # biggest area contour 			  
            counts = contours[contour_i]
            con_hull = cv2.convexHull(counts)
    
            # convex defects
            hull_defect = cv2.convexHull(counts, returnPoints = False)
            defects = cv2.convexityDefects(counts, hull_defect)
            
            # get defect points and draw inside image     
            far_defect = []
            if defects is None:
                continue
            else:
                # run through defects, create circles to indicate
                for i in range(defects.shape[0]):
                    st, en, fa, dy = defects[i,0]
                    start = tuple(counts[st][0])
                    end = tuple(counts[en][0])
                    far = tuple(counts[fa][0])
                    # append far defects that are far from start and ends
                    far_defect.append(far)
                    # line is used to indicate overall shape of captured palm
                    cv2.line(frame_debug, start, end, [0, 255, 10], 1)
                    # circle to indicate defects
                    cv2.circle(frame_debug, far, 7, [100, 200, 255], 3)
                
                # distance from finger defects to COM
                distance_defect_center = []
                for i in range(0, len(far_defect)):
                    x =  np.array(far_defect[i])
                    center_mass = np.array(com)
                    distance = np.sqrt(np.power(x[0] - center_mass[0], 2) + \
                                       np.power(x[1] - center_mass[1], 2))
                    distance_defect_center.append(distance)
                
                # get average of shortest distances from finger defect to COM
                sorted_distances = sorted(distance_defect_center)
                average_distance = np.mean(sorted_distances[0:2])
             
                # get fingertip points from contour hull, if points are in 
                # proximity of X pixels, consider as single point in the group
                finger = []
                max_len = len(con_hull) - 1
                for i in range(0, max_len):
                    if (np.absolute(con_hull[i][0][0] - con_hull[i+1][0][0]) > 40) \
                            or ( np.absolute(con_hull[i][0][1] - con_hull[i+1][0][1]) > 40):
                        if con_hull[i][0][1] < 500:
                            finger.append(con_hull[i][0])
                
                if len(finger) < 0:
                    continue
                else:
                    # fingertip points are 5 hull points   
                    finger =  sorted(finger, key = lambda x: x[1])   
                    fingers = finger[0:5]
                    
                    # calculate distance of each finger tip to COM
                    finger_distance = []
                    for i in range(0,len(fingers)):
                        # use sq root formula
                        distance = np.sqrt(np.power(fingers[i][0] - center_mass[0], 2) + \
                                           np.power(fingers[i][1] - center_mass[0], 2))
                        finger_distance.append(distance)
                    
                    # finger is raised if distance of between fingertip to COM
                    # is larger than Qdistance of average finger defect to COM by X pixels
                    result = 0
                    for i in range(0,len(fingers)):
                        if finger_distance[i] > average_distance:
                            result = result + 1
                    
                    data_reduction(result, com, frame_checklist)
                    # return number of pointed fingers
                    
                    # annotate image with conditions
                    
                    # cv2.imshow('debug_frame', frame_debug)
                    cv2.imwrite(filename_debug, frame_debug)
        """
        # break program if 'q' is entered
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
        
    # release resources
    cap.release()
    cv2.destroyAllWindows()

# displays conditions to user displays
def display_checklist(sector, fingers, frame):
    time.sleep(1) # cause system to delay 1 second
    global global_number
    splayed = False
    fist = False
    if (fingers >= 3):
        splayed = True
    if (fingers < 3):
        fist = True
    finger_text = "number of fingers: " + str(fingers)
    pt = (1*frame.shape[1]/5, 6*frame.shape[0]/9)
    cv2.putText(frame, finger_text, (pt[0], pt[1]), \
                cv2.FONT_HERSHEY_DUPLEX, .3, (255,255,255), 1)
    sector_text = "sector: " + str(sector)
    cv2.putText(frame, sector_text, \
                (pt[0], pt[1] + 10), cv2.FONT_HERSHEY_DUPLEX, .3, (255, 255, 255), 1)
    attribute = "gesture: "
    if splayed:
        attribute += "splayed"
    if fist:
        attribute += "fist"
    cv2.putText(frame, attribute, \
                (pt[0], pt[1] + 20), cv2.FONT_HERSHEY_DUPLEX, .3, (255, 255, 255), 1)

    # cv2.imshow('checklist', frame)

# com is tuple, use [] - frame has shape property, fingers is an integer
def data_reduction(fingers, com, frame):
    # divide image frame into 9 sections
    length, width, di = frame.shape
    xcom = com[0]
    ycom = com[1]
    # x divisions
    x1 = width/3
    x2 = 2*width/3
    x3 = width
    # y divisions
    y1 = length/3
    y2 = 2*length/3
    y3 = length
    # sector 1
    if ((xcom >= 0) and (xcom <= x1)) and ((ycom >= 0) and (ycom <= y1)):
        #print("sector 1")
        display_checklist(1, fingers, frame)
        
    # sector 2
    if (xcom >= x1 and xcom <= x2) and (ycom >= 0 and ycom <= y1):
        #print("sector 2")
        display_checklist(2, fingers, frame)
        
    # sector 3
    if (xcom >= x2 and xcom <= x3) and (ycom >= 0 and ycom <= y1):
        #print("sector 3")
        display_checklist(3, fingers, frame)
        
    # sector 4
    if (xcom >= 0 and xcom <= x1) and (ycom >= y1 and ycom <= y2):
        #print("sector 4")
        display_checklist(4, fingers, frame)
        
    # sector 5
    if (xcom >= x1 and xcom <= x2) and (ycom >= y1 and ycom <= y2):
        #print("sector 5")
        display_checklist(5, fingers, frame)
        
    # sector 6
    if (xcom >= x2 and xcom <= x3) and (ycom >= y1 and ycom <= y2):
        #print("sector 6")
        display_checklist(6, fingers, frame)
        
    # sector 7
    if (xcom >= 0 and xcom <= x1) and (ycom >= y2 and ycom <= y3):
        #print("sector 7")
        display_checklist(7, fingers, frame)
        
    # sector 8
    if (xcom >= x1 and xcom <= x2) and (ycom >= y2 and ycom <= y3):
        #print("sector 8")
        display_checklist(8,fingers, frame)
        
    # sector 9 
    if (xcom >= x2 and xcom <= x3) and (ycom >= y2 and ycom <= y3):
        #print("sector 9")
        display_checklist(9, fingers, frame)


if __name__ == '__main__':
    main(sys.argv)