import cv2
import os
import csv
import numpy as np
import math

cwd = os.getcwd()
campus_jpg_ = cwd + "\\new-campus.jpg"
campus_pgm_ = cwd + "\\new-campus.pgm"
labeled_pgm_ = cwd + "\\new-labeled.pgm"
new_table = cwd + "\\new-table.txt"
min_area = float("inf")
max_area = -float("inf")
global img_width, img_height

def describe_shape():
    global min_area, max_area, img_width, img_height

    building_table = processTable()
    # binary img file of campus
    campus_jpg = cv2.imread(campus_jpg_)
    # binary img, large number represents buildings, zero represents space
    campus_pgm = cv2.imread(campus_pgm_)
    # each building given encoded integer (bunches of pixels), zero = space
    labeled_pgm = cv2.imread(labeled_pgm_)

    pixel_dict = {}

    height, width, _ = campus_pgm.shape
    img_height, img_width, _ = campus_pgm.shape
    counter = 0
    for i in range(0, height):
        for j in range(0, width):
            #print(labeled_pgm_img[i, j, 0])
            building_no = labeled_pgm[i, j, 0]
            if (building_no == 9):
                counter += 1

            if building_no not in pixel_dict:
                pixel_dict[building_no] = 1
            else:
                pixel_dict[building_no] += 1

    pixel_dict.pop(0)
    #print(pixel_dict)
    # find smallest, biggest building
    for key in pixel_dict:
        area = pixel_dict[key]
        if area < min_area:
            min_area = area
        if area > max_area:
            max_area = area

    # process jpg file / or pgm file?
    #campus = cv2.cvtColor(campus_jpg, cv2.COLOR_BGR2GRAY)
    campus = cv2.cvtColor(labeled_pgm, cv2.COLOR_BGR2GRAY)

    # dict format: building_name | contour properties...
    building_info = {}
    # building_no | shape descriptions...
    what_info = {}
    # cv2.imshow("campus", campus)
    ret, thresh = cv2.threshold(campus, 8, 255, 0)
    img, contours, hierarchy = cv2.findContours(thresh, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    imgcopy = img.copy()

    for contour in contours:
        # first contour point (x, y) coordinates
        coordinates = (contour[0, 0])
        cnt_x = (coordinates[0])
        cnt_y = (coordinates[1])

        Moment = cv2.moments(contour)
        # center of mass (cx, cy)
        if (Moment["m00"] == 0):
            continue
        # center of mass
        cx = int(Moment["m10"]/Moment["m00"])
        cy = int(Moment["m01"]/Moment["m00"])
        # contour area
        area = cv2.contourArea(contour)
        # bounding rectangle properties
        x, y, w, h = cv2.boundingRect(contour)
        # aspect ratio is ratio of image width to image height (sq vs rect)
        aspect_ratio = w / float(h)
        # exent is the ratio of the contour area to the bounding box area
        extent = area / float(w * h)
        # think of convex hull as an elastic rubber band over the contour
        hull = cv2.convexHull(contour)
        hullArea = cv2.contourArea(hull)
        # solidity can never be greater than 1
        solidity = area / float(hullArea)
        # bounding rectangle lower right coordinates
        lrx = x + w
        lry = y + h
        # com, major/minor axis lengths, and orientation of object
        #(ell_x, ell_y),(MA,ma),angle = cv2.fitEllipse(contour)

        if (len(contour) < 5):
            #print(aspect_ratio)
            #cv2.circle(img, (cnt_x, cnt_y), 2, (100, 255, 8), 5)
            if (aspect_ratio < 1.0):
                #print("rectangle, pointing up-down, 0'")
                angle = (0)
            if (aspect_ratio > 1.0):
                #print("rectangle, pointing left-right, 90'")
                angle = (90)

            building_number_array = (labeled_pgm[cnt_y][cnt_x])
            building_number = building_number_array[0]
            building_name = building_table[str(building_number)]
            # building_number, aspect ratio, extent, solidity, angle
            building_info[building_name] = \
                building_number, \
                float(format(area, '.2f')), \
                (cx, cy), \
                (x, y),\
                (lrx, lry),\
                float(format(aspect_ratio, '.2f')), \
                float(format(extent, '.2f')), \
                float(format(solidity, '.2f')), \
                float(format(angle, '.2f'))
            #print(building_name)
            #print(building_info[building_name])
            shapeDescription = returnShape(building_info[building_name])
            #dictionary format: building_no | shape descriptions
            what_info[building_name] = shapeDescription

            x2 = cx + math.cos(angle*math.pi)*w/2
            y2 = cy + math.sin(angle*math.pi)*h/2

            font = cv2.FONT_HERSHEY_SIMPLEX
            #cv2.line(img, (cx, cy), (int(x2), int(y2)), (100,255,8), 2)
            cv2.putText(img,building_name,(cx,cy), font, 0.4,(100,255,8),1,cv2.LINE_AA)
            # CONTINUE TO ADD MORE INFORMATION HERE AS WELL
            #print(building_name)
            continue

        # ellipse angles work as North=0', East=90', etc.
        #ellipse = cv2.fitEllipse(contour)
        (ell_x, ell_y),(MA,ma),angle = cv2.fitEllipse(contour)

        font = cv2.FONT_HERSHEY_SIMPLEX
        #angle_str = str(angle)
        #cv2.putText((img),angle_str[:3],(cx,cy), font, 0.5,(100,255,8),2,cv2.LINE_AA)

        building_number_array = (labeled_pgm[cnt_y][cnt_x])
        building_number = building_number_array[0]
        building_name = building_table[str(building_number)]
        # building_number, area, COM, aspect ratio, extent, solidity, angle
        building_info[building_name] = \
            building_number, \
            float(format(area, '.2f')), \
            (cx, cy), \
            (x, y), \
            (lrx, lry),\
            float(format(aspect_ratio, '.2f')), \
            float(format(extent, '.2f')), \
            float(format(solidity, '.2f')), \
            float(format(angle, '.2f'))

        # orientation of angle not working for finding symmetry
        # maybe do: grab contourpoints, split contour in half
        # then check if subset rectangles contain ~equal amount of pixels
        x2 = cx + math.sin(angle*(math.pi/180))*w/2
        y2 = cy + math.cos(angle*(math.pi/180))*h/2

        #cv2.line(img, (cx, cy), (int(x2), int(y2)), (100,255,8), 2)
        cv2.putText(img,building_name,(cx,cy), font, 0.4,(100,255,8),1,cv2.LINE_AA)

        #print(building_name)
        shapeDescription = returnShape(building_info[building_name])
        # dictionary format: building_no | shape descriptions
        what_info[building_name] = shapeDescription

    # write numerical values to debug csv file
    with open('Building Shape Properties (Numerical).csv', 'wb') as csvfile:
        reader = csv.writer(csvfile, delimiter=',')
        reader.writerow(['Name', 'Building #', 'Area', 'COM', 'Upper left', 'Lower right', 'AspectRatio', 'Extent', 'Solidity', 'Angle'])
        for key in building_info:
            tuple_value = building_info[key]
            reader.writerow([key, tuple_value[0], tuple_value[1], tuple_value[2],\
                             tuple_value[3], tuple_value[4], tuple_value[5],\
                             tuple_value[6], tuple_value[7], tuple_value[8]])

    #print(what_info)
    writeToCSV("Building Shape Descriptions (Alphabetic).csv", what_info)
    #imgcopy = img.copy()
    #cv2.drawContours(img, contours, 3, (0,255,0), 3)
    cv2.imshow("contours", img)
    cv2.waitKey(0)

def writeToCSV(filename, dictionary):
    with open(filename, 'wb') as csvfile:
        reader = csv.writer(csvfile, delimiter=',')
        reader.writerow(['Name', 'Descriptions'])
        for key in dictionary:
            tuple_value = dictionary[key]
            reader.writerow([key, tuple_value])

# describing shape using numerical values
def returnShape(tuple_value):
    global img_width, img_height
    # list of descriptions
    descriptions = []
    # NO, AREA, COM, UL, LR, ASPECTRATIO, EXTENT, SOLID, ANGLE
    number = tuple_value[0]
    area = tuple_value[1]
    COM = tuple_value[2]
    UL = tuple_value[3]
    LR = tuple_value[4]
    aspect_ratio = tuple_value[5]
    extent = tuple_value[6]
    solidity = tuple_value[7]
    angle = tuple_value[8]
    # AREA: compute if small, medium, or large
    global min_area, max_area
    full_range = max_area - min_area
    small = min_area + full_range*(1/3.0)
    medium = small + full_range*(1/3.0)
    large = max_area
    # if less than min_area, or from min_area to small - it is small
    if (area <= min_area) or (min_area < area and small >= area):
        #print("small")
        descriptions.append("small")
    # if more than small and less than medium - it is medium
    if (area > small) and (area <= medium):
        #print("medium")
        descriptions.append("medium")
    # if area more than medium and less than large and more than large - large
    if ((area > medium) and (area <= large)) or (area >= large):
        #print("large")
        descriptions.append("large")
    # long (cant do thin) - if aspect ratio is < 0.5 or > 3
    if (aspect_ratio <= 0.5) or (aspect_ratio >= 3):
        descriptions.append("long")
    # square/ish
    if (.9 <= aspect_ratio and aspect_ratio <= 1):
        if (.9 <= solidity and solidity <= 1):
            descriptions.append("squareish")
            descriptions.append("symmetricEastWest")
            descriptions.append("symmetricNorthSouth")
    # rectangularish
    if (aspect_ratio > 1.6):
        descriptions.append("rectangularish")
        descriptions.append("symmetricEastWest")
        descriptions.append("symmetricNorthSouth")
    # non rectangular (based on empirical data on building shapes)
    if (.4 <= extent and extent <= .6):
        if (.4 <= solidity and solidity <= .6):
            if (1.0 <= aspect_ratio and aspect_ratio <= 1.1):
                descriptions.append("non rectangular")
            if (.5 <= aspect_ratio and aspect_ratio <= .8):
                #print("non rectangular")
                descriptions.append("non rectangular")
    if (1.15 <= aspect_ratio and aspect_ratio <= 1.2):
        if (.85 <= extent and extent <= .9):
            if (.9 <= solidity and solidity <= 1.1):
                descriptions.append("non rectangular")
        if (.6 <= extent and extent <= .68):
            if (.7 <= solidity and solidity <= .75):
                descriptions.append("non rectangular")
    if (1.3 <= aspect_ratio and aspect_ratio <= 1.4):
        if (.5 <= extent and extent <= .6):
            if (.65 <= solidity and solidity <= .7):
                descriptions.append("non rectangular")
    # curved shape
    if (.6 <= aspect_ratio and aspect_ratio <= .7):
        if (.8 <= extent and extent <= .9):
            if (.95 <= solidity and solidity <= 1.0):
                descriptions.append("has curves")
    # plus shaped
    if (1.55 <= aspect_ratio and aspect_ratio <= 1.7 ):
        if (.75 <= extent and extent <= .85):
            if (.9 <= solidity and solidity <= .95):
                descriptions.append("plus shaped +")
    # wide plus shaped
    if ((.5 <= aspect_ratio and aspect_ratio <= .55) or (1.6 <= aspect_ratio and aspect_ratio <= 1.7)):
        if (.8 <= extent and extent <= 8.5):
            if (.9 <= solidity and solidity <= .95):
                descriptions.append("flattened plus shaped +")
    # simple boundary
    if (.9 <= extent):
        descriptions.append("simple boundary")
    # jagged
    if (extent <= .5):
        if (solidity):
            descriptions.append("jagged")
    # I shaped
    if (.54 <= aspect_ratio and aspect_ratio <= .6):
        if (.75 <= extent and extent <= .8):
            if (.8 <= solidity and solidity <= .84):
                #print("I shaped")
                descriptions.append("I-shaped")
    # L-shaped
    if (.4 <= extent and extent <= .42):
        if (.56 <= solidity and solidity <= .59):
            descriptions.append("L-shaped")
    # C-shaped
    if (.44 < extent and extent <= .46):
        if (.46 <= solidity and solidity <= .47):
            descriptions.append("C-shaped")
    # Where description (xN, yN represent grid markers on img)
    x0, x1, x2, x3 = 0, img_width/3.0, (2/3.0)*img_width, img_width
    y0, y1, y2, y3, y4 = 0, img_height/4.0, img_height/2.0, (3/4.0)*img_height, img_height
    # orientation
    if (135 <= angle and angle <= 225) or (315 < angle or angle < 45):
        descriptions.append("orientedNorthsouth")
    if (45 <= angle and angle <= 135) or (225 <= angle and angle <= 315):
        descriptions.append("orientedEastWest")
    # central location
    com_x, com_y = COM
    if True:
        if (x1 <= com_x and com_x <= x2) and (y1 <= com_y and com_y <= y3):
            descriptions.append("centrallyLocated")
        else:
            descriptions.append("onBorder")
    # north, south, west, East
    if True:
        if (y2 >= com_y):
            descriptions.append("northern")
        if (y2 <= com_y):
            descriptions.append("southern")
        if (com_x < img_width/2.0):
            descriptions.append("western")
        if (com_x > img_width/2.0):
            descriptions.append("eastern")
        if (com_x == img_width/2.0):
            descriptions.append("betweenEastWest")

    # where descriptions (absolute space)
    #print (descriptions)

    #print(descriptions)
    return descriptions

# boolean functions for building pairs, takes in COM, remember Source Target
# ex: North of Source is Target
def North(com01, com02):
    # get respective x, y com values of the two buildings
    x1, y1 = com01
    x2, y2 = com02
    if (y2 > y1):
        return False
    elif (y2 <= y1):
        return True

def South(com01, com02):
    # get respective x, y com values of the two buildings
    x1, y1 = com01
    x2, y2 = com02
    if (y2 > y1):
        return True
    elif (y2 <= y1):
        return False

def East(com01, com02):
    # get respective x, y com values of the two buildings
    x1, y1 = com01
    x2, y2 = com02
    if (x2 > x1):
        return True
    elif (x2 <= x1):
        return False

def West(com01, com02):
    # get respective x, y com values of the two buildings
    x1, y1 = com01
    x2, y2 = com02
    if (x2 < x1):
        return True
    elif (x2 >= x1):
        return False

# will return the building no. using (x,y) coordinates based on the pgm file
def crossReference(x, y):
    # binary img, large number represents buildings, zero represents space
    labeled_pgm = cv2.imread(labeled_pgm_)
    building_number = labeled_pgm[y][x]

    return building_number[0]

# process new-table txt into dictionary for matching building no & building name
def processTable():

    building_table = {}
    with open(new_table) as f:
        for line in f:
            number, building = line.split()
            building_table[number] = building

    return building_table

# need function for extracting individual buildings from the 4 multipleBuildings

if __name__ == "__main__":
    describe_shape()
