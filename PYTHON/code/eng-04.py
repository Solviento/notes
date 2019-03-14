###########################################################################
# Jason Perez
# UNI: jp3476
# ENGI 1006
# Assignment 5 Part 1
# Percolation module
###########################################################################

import numpy as np
import matplotlib.pyplot as plt

def read_grid(filename):
    #Create a site vacancy matrix from a text file.
    #filename is a String that is the name of the text file to be read. 
    #The method should return the corresponding site vacancy matrix represented
    #as a numpy array

    # Uses loadtxt to read grid 
    return np.loadtxt(filename, dtype = 'int', skiprows = 1)


def write_grid(filename, sites):
    #Writes site vacancy matrix to a file. Filename is a string that is the
    #name of the text file to write to. sites is a numpy array representing 
    #the site vacany matrix to write.
    #Uses savetxt to save and write on sites

    np.savetxt(filename, sites, '%d', header = str(len(sites)), comments = '')
      
def directed_flow(sites): #directed flow = flow
    #Returns a matrix of vacant/full sites (1=full, 0=vacant)
    #sites is a numpy array representing a site vacancy matrix. This 
    #function should return the corresponding flow matrix generated 
    #through undirected percolation.

    #Copies the array into a new variable
    flow = np.copy(sites)
    
    #Loop to create the vacant and full sites
    for i in range(1, len(sites)):
        for j in range(0, len(sites)):
            flow[i,j] = 0
            
            # Combines flow and sites positions
            flow[i,j] = flow[i - 1, j] and sites[i,j]
            
    return flow
    
def flow_from(sites,full,i,j):
    """Adjusts the full array for flow from a single site

    This method does not return anything. It changes the array full
    Notice it is not side effect free
    """
    
    x = sites.shape[0] #assigns variable to number of rows in sites using shape
    if i < 0 or i >= x: #checks if number of rows is between 0 and row #'s
        return
    if j < 0 or j >= x: #starts at 0, checks if #rows is between 0 and #rows
        return
    if sites[i, j] == 0: #if a point i,j is empty
        return
    if full[i, j] == 1: #if a point i,j is full
        return
    full[i, j] = 1 #element i,j in the array of zeros converted to a 1
    flow_from(sites, full, i-1, j)
    flow_from(sites, full, i+1, j)
    flow_from(sites, full, i, j-1)
    flow_from(sites, full, i, j+1)

def percolates(flow_matrix):
    #Returns a boolean if the flow_matrix exhibits percolation
    #flow_matrix is a numpy array representing a flow matrix
  
    return sum(flow_matrix[len(flow_matrix)-1]) > 0

def make_sites(n,p):
    #Returns an nxn site vacancy matrix
    #Generates a numpy array representing an nxn site vacancy 
    #matrix with site vaccancy probability p

    x = np.random.rand(n, n) # Creates random array of 0 and 1
    y = x < p
    y = y.astype(int) #converts as integer vals
    return y

def show_perc(sites):
    """Displays a matrix using three colors for vacant, blocked, full
    
    Used to visualize undirected flow on the matrix sites.
    """
    full = directed_flow(sites)
    flow = full + sites
    plt.matshow(flow) #flow is utilized
    plt.show()  #visualizes the percolation plot as rgb tiles

def make_plot(trials,n):
    """generates and displays a graph of percolation p vs. vacancy p

    estimates percolation probability on an nxn grid for directed 
    percolation by running a Monte Carlo simulation using the variable trials number
    of trials for each point. 
    """
    x1 = np.linspace(0, .28, num=3)
    x2 = np.linspace(.28, .87, num=25)
    x3 = np.linspace(.87, 1, num=2)
    y = []  #list for y values set to empty
    
    for p in x1: #takes vacancy probabilities equivalent to each value in x1
        print ('Testing at ' + str(p*100) + '% vacancy probability')
        i = 0
        success = 0
        while i < trials:
            sites = make_sites(n,p) #creates a variable for the matrix
            full = directed_flow(sites)   #assigns variable to flow matrix
            success += int(percolates(full)) 
            i += 1
        y.append((float(success)/float(trials))) #appends to list
     
    for p in x2:
        print ('Testing at ' +str(p*100)+ '% vacancy probability')
        i = 0
        success = 0
        while i < trials: 
            sites = make_sites(n, p)
            full = directed_flow(sites)
            success += int(percolates(full))
            i += 1
        y.append((float(success)/float(trials)))
        
    for p in x3:
        print ('Testing at' +str(p*100)+ '% vacancy probability')
        i = 0
        success = 0
        while i < trials:     
            sites = make_sites(n, p)
            full = directed_flow(sites)
            success += int(percolates(full))
            i += 1
        y.append((float(success)/float(trials)))

    x = np.concatenate((x1, x2, x3)) #values added for x axis 
    plt.plot(x,y)
    
    plt.xlabel('Vacancy Probability')
    plt.ylabel('Percolation Probability')
    plt.title('Percolation Probability v Vacancy Probability')
    plt.show()