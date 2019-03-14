###########################################################################
# Jason Perez
# UNI: jp3476
# ENGI 1006
# Assignment 5 Part 1
# Percolation module
###########################################################################

import numpy as num

def read_grid(filename):
    #Create a site vacancy matrix from a text file.
    #filename is a String that is the name of the text file to be read. 
    #The method should return the corresponding site vacancy matrix represented
    #as a numpy array

    # Uses loadtxt to read grid 
    return num.loadtxt(filename, dtype = 'int', skiprows = 1)


def write_grid(filename, sites):
    #Writes site vacancy matrix to a file. Filename is a string that is the
    #name of the text file to write to. sites is a numpy array representing 
    #the site vacany matrix to write.
    #Uses savetxt to save and write on sites

    num.savetxt(filename, sites, '%d', header = str(len(sites)), comments = '')
      
def flow(sites):
    #Returns a matrix of vacant/full sites (1=full, 0=vacant)
    #sites is a numpy array representing a site vacancy matrix. This 
    #function should return the corresponding flow matrix generated 
    #through undirected percolation.

    #Copies the array into a new variable
    flow = num.copy(sites)
    
    #Loop to create the vacant and full sites
    for i in range(1, len(sites)):
        for j in range(0, len(sites)):
            flow[i,j] = 0
            
            # Combines flow and sites positions
            flow[i,j] = flow[i - 1, j] and sites[i,j]
            
    return flow


def percolates(flomatrix):
    #Returns a boolean if the flow_matrix exhibits percolation
    #flow_matrix is a numpy array representing a flow matrix
  
    return sum(flomatrix[len(flomatrix)-1]) > 0


def make_sites(n,p):
    #Returns an nxn site vacancy matrix
    #Generates a numpy array representing an nxn site vacancy 
    #matrix with site vaccancy probability p

    # Creates random array of 0 and 1
    x = num.random.rand(n, n)
    y = x < p
    y = y.astype(int)
    return y