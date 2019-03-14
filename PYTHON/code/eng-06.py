###################################
# Jason Perez
# UNI: jp3476
# Nearest Neighbor
# HW 6 Part A
# Main Function for hw6a
###################################

import hw6a as hw

"""
Synthetic Data Sets:
Class 1: mean = [2, 5.5], covariance = [[1, 1], [1, 2.5] ], 250 samples. 
Class 2: mean = [.5, 1], covariance = [[1, 0], [0, 1]], 250 samples
"""

def main ():
    
    inFile = input("Enter file name: ")
    p = int(input("Enter p: "))
    """
    Synthetic Set
    """
    sample = 250
    sample2 = 250
    i, j = hw.synthetic(sample, sample2) #selected values
    syn_data = hw.labels(sample, sample2, i, j) #new set
    print("\n")
    print ("Synthetic Data of Class1/Class2 -labels (Generally .93+):")
    syn = hw.n_validator(syn_data, p, hw.NNclassifier) #uses synthesized set
    print ("Accuracy: ", syn)
    print("\n")
    
    """
    Real Set
    """
    #Uses the input filename'wdbc.txt'
    malig, benign, sizem, sizeb = hw.read(inFile) 
    real = hw.labels(sizem, sizeb, malig, benign)
    print ("Real Data -labels (Generally ~.89): ")
    rset = hw.n_validator(real, p, hw.NNclassifier) #wdbc set used
    print ("Accuracy: ", rset)

main()