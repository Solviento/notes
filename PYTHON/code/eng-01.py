#Name: Jason Perez
#UNI: jp3476
#Date: 4/6/2015
#Module: effects.py
#Module for functions used in effects_tester
#Created with Python 3.4

from io import SEEK_CUR

# Effect 1
def shades_of_grey(x, y):
    
    #Go through pixels in pairs of 3 to set color equal to average of the three
    #color values. Will make it grey.
    for i in range(0, len(x), 3): 
        average = (x[i] + x[i + 1] + x[i + 2]) / 3
        x[i] = average
        x[i + 1] = average
        x[i + 2] = average
        
    return x
# Effect 2
def negate_red(x, y, z): 
    
    #Go through pixels and takes r values to equal opposite extreme value
    #Color is then negated
    for i in range(len(x)):
        
        if (i % 3 == 0):
            x[i] = z - x[i]
            
    return x

# Effect 3
def negate_green(x, y, z): 
    
    #Go through pixels and takes g values to equal opposite extreme value
    #Color is then negated
    for i in range(len(x)):
        if ((i + 2) % 3 == 0):           
            x[i] = z - x[i]
            
    return x

# Effect 4
def negate_blue(x, y, z):
    
    #Go through pixels and takes b values to equal opposite extreme value
    #Color is then negated
    for i in range(len(x)):
        if ((i + 1) % 3 == 0): 
            x[i] = z - x[i]
            
    return x

# Effect 5
def horizontal_flip(x, y): 
    
    #Takes each row and reverses its values so that the file flips
    #horizontally. Goes through each in sets of 3 and then makes each
    #rgb value a variable to go into new and return x
    new = [] 
    for i in range(len(x)-1, 0, -3): 
        r = x[i-2]
        g = x[i-1]
        b = x[i]
        new.append(r)
        new.append(g)
        new.append(b)
        
    x = new 
    return x
    
    
############# BMP effects ###############

def negate_red_bmp(imgFile) :
    
   # Read the pixel as individual bytes.
    theBytes = imgFile.read(3)
    blue = theBytes[0]
    green = theBytes[1]
    red = theBytes[2]
    # Process the pixel.
    newBlue = blue
    newGreen = green
    newRed = 255 - red
    # Write the pixel.
    imgFile.seek(-3, SEEK_CUR)   # Go back 3 bytes to the start of the pixel
    imgFile.write(bytes([newBlue, newGreen, newRed])) 
    #output.write(bytes([newBlue, newGreen, newRed]))

def negate_blue_bmp(imgFile) :
    
    # Read the pixel as individual bytes.
    theBytes = imgFile.read(3)
    blue = theBytes[0]
    green = theBytes[1]
    red = theBytes[2]

    # Process the pixel.
    newBlue = 255 - blue
    newGreen = green
    newRed = red
    # Write the pixel.
    imgFile.seek(-3, SEEK_CUR)   # Go back 3 bytes to the start of the pixel
    imgFile.write(bytes([newBlue, newGreen, newRed])) 

def negate_green_bmp(imgFile):
    
    # Read the pixel as individual bytes.
    theBytes = imgFile.read(3)
    blue = theBytes[0]
    green = theBytes[1]
    red = theBytes[2]
    # Process the pixel.
    newBlue = blue
    newGreen = 255 - green
    newRed = red
    # Write the pixel.
    imgFile.seek(-3, SEEK_CUR)   # Go back 3 bytes to the start of the pixel
    imgFile.write(bytes([newBlue, newGreen, newRed])) 
   
def shades_of_grey_bmp(imgFile):
    
    #takes grey values from changing rgb average values
    theBytes = imgFile.read(3)
    blue = theBytes[0]
    green = theBytes[1]
    red = theBytes[2]
    
    newBlue = (blue*114)//1000
    newGreen = (green*587)//1000
    newRed = (red*299)//1000
    grey = newBlue + newGreen + newRed
    # Write the pixel.
    imgFile.seek(-3, SEEK_CUR)   # Go back 3 bytes to the start of the pixel
    imgFile.write(bytes([grey, grey, grey]))   
   
## Gets an integer from a binary file.
#  @param imgFile the file
#  @param offset the offset at which to read the integer
#  @return the integer starting at the given offset
def readInt(imgFile, offset):
    
    # Move the file pointer to the given byte within the file.
    imgFile.seek(offset)
    # Read the 4 individual bytes and build an integer.
    theBytes = imgFile.read(4)   
    result = 0
    base = 1
    for i in range(4) :
        result = result + theBytes[i] * base 
        base = base * 256
      
    return result