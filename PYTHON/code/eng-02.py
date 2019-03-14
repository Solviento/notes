#Jason Perez
#UNI: jp3476
#Assignment 4 part b
#Created with Python 3.4

import effects as eff
from io import SEEK_CURa

def apply_effects():

    kind = kind_file()
    if kind == "bmp":
        input_filname = input_file()
        output_filname = output_file()
        effects_bmp = menu_bmp()
        effects_bp(input_filname, output_filname, effects_bmp)
    elif kind == "ppm":
        input_filename = input_file()
        output_filename = output_file()
        effects_ppm = menu_ppm()
        effects_pp(input_filename, output_filename, effects_ppm)
    
def kind_file():
    
    #Will ask for file extension
    user = input("Please type in a desired file extension to modify (ppm/bmp): ")
    return user

#Helper function to keep track of input file
def input_file(): 
    
    #Ask the user for the name of the input file. 
    input_f = input("Enter an input file name:")
    return input_f
    
#Function to name output file
def output_file():
    
    #Ask the user for the name of the output file. 
    output_f = input("Enter name of output file:")
    return output_f

#Array function for choices
def menu_ppm():
    
    #Creates choice as empty list
    choice = []
    #Using choices by user, we append the list to be used later
    red_input = input("Apply negate red? (y/n) ")
    if red_input == "y": 
        choice.append(1)
        
    green_input = input("Apply negate green? (y/n) ")
    if green_input == "y": 
        choice.append(2)
        
    blue_input = input("Apply negate blue? (y/n) ")
    if blue_input == "y": 
        choice.append(3)
        
    gray_input = input("Apply a shade of grey? (y/n) ")
    if gray_input == "y": 
        choice.append(4)
    
    
    hor_input = input("Apply horizontal flip? (y/n) ")
    if hor_input == "y": 
        choice.append(5)
    return choice
    
def menu_bmp():
    
    #Creates choice as empty list
    choice = []
    #Using choices by user, we append the list to be used later
    red_input = input("Apply negate red? (y/n) ")
    if red_input == "y": 
        choice.append(1)
        
    green_input = input("Apply negate green? (y/n) ")
    if green_input == "y": 
        choice.append(2)
        
    blue_input = input("Apply negate blue? (y/n) ")
    if blue_input == "y": 
        choice.append(3)
        
    gray_input = input("Apply a shade of grey? (y/n) ")
    if gray_input == "y": 
        choice.append(4)
    return choice

    
def effects_pp(x, y, array):
    #Open the infile and outfile. 
    inputfile = open(x, 'r')
    outputfile = open(y, 'w')
    
    #Reads the header/1st 3 lines and exports them. 
    first = inputfile.readline()
    outputfile.write(first)
    
    second = inputfile.readline()
    outputfile.write(second) 
    third = inputfile.readline()
    
    #Get the length of a row from the image 
    list = third.split()
    color = list[0]
    new_color = int(color)
    outputfile.write(third) 
    list = second.split()
    length = list[0]
    total = int(list[list.index(length)])
    
    #This is a limiter that limits the row to being only 1024 pixels
    #Or else the file wont be able to read it
    if total < 1024: 
        limit = []
        for line in inputfile:   
            new_list = line.split()
            differences = [int(item) for item in new_list] 
            for item in differences: 
                limit.append(item)
                if len(limit) == total*3: 
                    #The function will apply a certain effect 
                    #depending on which file 
                    if 1 in array:
                        limit = eff.negate_red(limit, outputfile, new_color)
                    if 2 in array: 
                        limit = eff.negate_green(limit, outputfile, new_color)
                    if 3 in array: 
                        limit = eff.negate_blue(limit, outputfile, new_color) 
                    if 4 in array: 
                        limit = eff.shades_of_grey(limit, outputfile)
                    if 5 in array: 
                        limit = eff.horizontal_flip(limit, outputfile)
                    #Writes buff to the outputfile and closes it. 
                    for i in range(len(limit)): 
                        outputfile.write(str(limit[i]) + ' ')
                    outputfile.write('\n')
                    limit = [] 
        print("Outputfile is" + " created.")

    else:  

        print ("File is too large.")
#Close files       
    inputfile.close()
    outputfile.close()

def effects_bp(x, y, array):
    
    #x=input_filename y=output_filename array=menu_bmp
    if 1 in array:
        imgFile = open(x, "rb+")
        #outputfile = open(y, 'wb')
        fileSize = eff.readInt(imgFile, 2)
        start = eff.readInt(imgFile, 10)
        width = eff.readInt(imgFile, 18)
        height = eff.readInt(imgFile, 22)
        # Scan lines must occupy multiples of four bytes.
        scanlineSize = width * 3
        if scanlineSize % 4 == 0 :
            padding = 0
        else :
            padding = 4 - scanlineSize % 4
        imgFile.seek(start)
        for row in range(height) :  # For each scan line
            for col in range(width) :  # For each pixel in the line
                #negate red(imgFile)         
                eff.negate_red_bmp(imgFile)
                # Skip the padding at the end
                imgFile.seek(padding, SEEK_CUR)
        imgFile.close()
        #outputfile.close()
    if 2 in array:
        imgFile = open(x, "rb+") 
        fileSize = eff.readInt(imgFile, 2)
        start = eff.readInt(imgFile, 10)
        width = eff.readInt(imgFile, 18)
        height = eff.readInt(imgFile, 22)
        # Scan lines must occupy multiples of four bytes.
        scanlineSize = width * 3
        if scanlineSize % 4 == 0 :
            padding = 0
        else :
            padding = 4 - scanlineSize % 4
        imgFile.seek(start)
        for row in range(height) :  # For each scan line
            for col in range(width) :  # For each pixel in the line
                #negate green(imgFile)         
                eff.negate_green_bmp(imgFile)
                # Skip the padding at the end
                imgFile.seek(padding, SEEK_CUR)
        imgFile.close()
    if 3 in array:
        imgFile = open(x, "rb+") 
        fileSize = eff.readInt(imgFile, 2)
        start = eff.readInt(imgFile, 10)
        width = eff.readInt(imgFile, 18)
        height = eff.readInt(imgFile, 22)
        # Scan lines must occupy multiples of four bytes.
        scanlineSize = width * 3
        if scanlineSize % 4 == 0 :
            padding = 0
        else :
            padding = 4 - scanlineSize % 4
        imgFile.seek(start)
        for row in range(height) : 
            for col in range(width) : 
                #negate blue(imgFile)         
                eff.negate_blue_bmp(imgFile)
                # Skip the padding at the end
                imgFile.seek(padding, SEEK_CUR)
        imgFile.close()
    if 4 in array:
        imgFile = open(x, "rb+") 
        fileSize = eff.readInt(imgFile, 2)
        start = eff.readInt(imgFile, 10)
        width = eff.readInt(imgFile, 18)
        height = eff.readInt(imgFile, 22)
        scanlineSize = width * 3
        if scanlineSize % 4 == 0 :
            padding = 0
        else :
            padding = 4 - scanlineSize % 4
        imgFile.seek(start)
        for row in range(height) : 
            for col in range(width):  
            #greyscale(imgFile)         
                eff.shades_of_grey_bmp(imgFile)
            # Skip the padding at the end
                imgFile.seek(padding, SEEK_CUR)
        imgFile.close()
        
    print("Outputfile is created")

apply_effects()      