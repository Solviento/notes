""" python code for computing color distance """
import cv2
import sys
import os
import numpy as np

cwd = os.getcwd()   # save images to filepath

def main(argv):
    color = ('b','g','r')
    L1_DISTALL = []     # save all L1 distance arrays here
    crowdfile = cwd + "\\Crowd.txt"
    personalfile = cwd + "\\jp3476.txt"
    crowd_array = []
    personal_array = []
    # convert crowd file into 2d array
    with open(crowdfile) as f:
        for line in f:
            #line_list = line.split()
            line_list = map(int, line.split())
            crowd_array.append(line_list)
            
    # convert personal file to 2d array
    with open(personalfile) as f:
        for line in f:
            #line_list = line.split()
            line_list = map(int, line.split())
            personal_array.append(line_list)
    
    personal_grand_total = 0
    
    # compute personal score against crowd
    for line in personal_array:
        
        t1 = crowd_array[line[0] - 1][line[1] - 1]
        t2 = crowd_array[line[0] - 1][line[2] - 1]
        t3 = crowd_array[line[0] - 1][line[3] - 1]
        sumtotal = t1 + t2 + t3
        personal_grand_total = personal_grand_total + sumtotal

    # compile list of images to be processed
    images_list = []
    for i in range(1, 41):
        # images found in \images\iXX.jpg
        img = cwd + "\\images\\" + "i" + str(i) + ".jpg"
        images_list.append(img)
    
    # compute color histograms and find l1 distances
    for image_a in images_list:
        L1_DISTANCES = []           # save L1 distances here
        for image_b in images_list:
            total = 0               # total is the sum of matches in histogram
            for i,col in enumerate(color):
                # read in images
                img_a = cv2.imread(image_a)
                img_b = cv2.imread(image_b)
                # compute histograms using X number of bins: B G R
                # THESE VALUES WERE CHOSEN AFTER EXPERIMENTING WITH DIFFERENT BIN #
                if (i == 0):
                    hist_a = cv2.calcHist([img_a], [i], None, [15], [0,256])
                    hist_b = cv2.calcHist([img_b], [i], None, [15], [0,256])
                if (i == 1):
                    hist_a = cv2.calcHist([img_a], [i], None, [30], [0,256])
                    hist_b = cv2.calcHist([img_b], [i], None, [30], [0,256])
                if (i == 2):
                    hist_a = cv2.calcHist([img_a], [i], None, [15], [0,256])
                    hist_b = cv2.calcHist([img_b], [i], None, [15], [0,256])
                # flatten ndarray in order to use as a vector array
                a = hist_a.flatten()
                b = hist_b.flatten()
                # subtract vectors to get histogram differences
                sub = np.subtract(a, b)
                # compute sum of differences
                for element in sub:
                    total = total + abs(element)
            # L1 distance formula, we use 3*2*60*89 to account for the 3 color channels
            L1 = total/(3*2*60*89)
            L1_DISTANCES.append(round(L1, 3))
            # try? format(L1, '.3f')
        L1_DISTALL.append(L1_DISTANCES)
        
    # parse through arrays and return dictionary of top three match results + number
    sorted_array = [] # [img_num, t1, img_num, t2, img_num, t3]
    for arr in L1_DISTALL:
        third = first = second = sys.maxsize
        # extract top three values + respective image numbers
        for i in range(0, len(arr)):
            if (arr[i] == 0):
                continue
            if (arr[i] < first):
                third = second
                second = first
                first = arr[i]
            elif (arr[i] < second):
                third = second
                second = arr[i]
            elif (arr[i] < third):
                third = arr[i]
        
        img_1 = arr.index(first) + 1
        img_2 = arr.index(second) + 1
        img_3 = arr.index(third) + 1 
        #d = {img_1: val_1, img_2: val_2, img_3: val_3}
        d = [img_1, first, img_2, second, img_3, third]
        sorted_array.append(d)
    
    vertical = None
    first_pass = False
    # query image number
    image_iteration = 1
    # total crowd score
    grand_total = 0
    # generate images
    for image_list in sorted_array:
        # extract images based on top matches + query image
        file0 = cwd + "\\images\\" + "i" + str(image_iteration) + ".jpg"
        file1 = cwd + "\\images\\" + "i" + str(image_list[0]) + ".jpg"
        file2 = cwd + "\\images\\" + "i" + str(image_list[2]) + ".jpg"
        file3 = cwd + "\\images\\" + "i" + str(image_list[4]) + ".jpg"
        black = cwd + "\\images\\black.jpg"
        
        img0 = cv2.imread(file0)
        img1 = cv2.imread(file1)
        img2 = cv2.imread(file2)
        img3 = cv2.imread(file3)
        imgblack = cv2.imread(black)
        # calculating crowd score from crowd.txt
        t1 = crowd_array[image_iteration - 1][image_list[0] - 1]
        t2 = crowd_array[image_iteration - 1][image_list[2] - 1]
        t3 = crowd_array[image_iteration - 1][image_list[4] - 1]
        total = t1+t2+t3
        grand_total = grand_total + total
        # write query image number on top of image
        font = cv2.FONT_HERSHEY_SIMPLEX
        cv2.putText(img0,str(image_iteration),(10,50), font, 1,(255,255,255),2,cv2.LINE_AA)
        cv2.putText(img1,str(t1),(10,50), font, 1,(255,255,255),2,cv2.LINE_AA)
        cv2.putText(img2,str(t2),(10,50), font, 1,(255,255,255),2,cv2.LINE_AA)
        cv2.putText(img3,str(t3),(10,50), font, 1,(255,255,255),2,cv2.LINE_AA)
        cv2.putText(imgblack,str(total),(10,50), font, 1,(255,255,255),2,cv2.LINE_AA)
        # horizontal concat of images
        horizontal = np.concatenate((img0, img1, img2, img3, imgblack), axis=1)
        image_iteration = image_iteration + 1
        if (first_pass == False):
            vertical = horizontal
            first_pass = True
        else:
            vertical = np.concatenate((vertical, horizontal), axis=0)
    
    # last row of images will display grand crowd total score
    black = cwd + "\\images\\black.jpg"
    imgblack = cv2.imread(black)
    imgfinal = cv2.imread(black)
    cv2.putText(imgfinal,str(grand_total),(10,50), font, 1,(255,255,255),2,cv2.LINE_AA)
    horizontal = np.concatenate((imgblack, imgblack, imgblack, imgblack, imgfinal), axis=1)
    vertical = np.concatenate((vertical, horizontal), axis=0)
    # final image output
    cv2.imwrite('topimages_color.jpg', vertical)
    
    # calculate system versus personal ground truth
    matches = 0
    for i in range(0, 40):
        #system = sorted_array[i]
        system = [sorted_array[i][0], sorted_array[i][2], sorted_array[i][4]]
        personal = personal_array[i]
        intersection = list(set(system) & set(personal))
        matches = matches + len(intersection)
    
    # write out personal, crowd, and happinesss scores    
    f = open("output.txt","w")
    f.write("system versus crowd: " + str(grand_total) + "\n")
    f.write("personal ground truth versus crowd: " + str(personal_grand_total))
    f.write("\nsystem versus personal ground truth: " + str(matches))
    f.write("\nhappiness: "  + str(matches/120.0))
    f.close()
    
if __name__ == "__main__":
    main(sys.argv)