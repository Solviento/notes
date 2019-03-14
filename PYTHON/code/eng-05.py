#################################################
# Jason Perez
# UNI: jp3476
# Nearest Neighbor
# HW 6 Part A
# contains pre set values for class 1 and class2
#################################################

import numpy as np

def read(filename):
    """
    Opens, reads and strips while returning data on filename
    """
    file = open(filename, 'r')
    class_m = [] #Will be append with arrays classified  as 'M'
    class_b = [] #Will be append with arrays classified as 'B'
    row = file.readline() #Takes data line by line 
    counter_m = 0 #Counters for 'M' and 'B'
    counter_b = 0
    
    while row:
        structure_line = row.rstrip('\n').split(',') #Strips the \n from datafile
        structure_line.pop(0) #Strips first element of data structure
        #Will go overand strip id number
        if (structure_line[0] == 'M'):
            structure_line.pop(0) 
            class_m.append(structure_line)
            counter_m += 1 #adds 1 overall
        elif (structure_line[0] == 'B'):
            structure_line.pop(0)
            class_b.append(structure_line)
            counter_b += 1
        row = file.readline() #continues for real data
    
    array_m = np.vstack(class_m) #stacks arrays vertically
    array_b = np.vstack(class_b)
    file.close()
    return (array_m, array_b, counter_m, counter_b) #data file arrays

def synthetic(samp1, samp2):
    """
    Synthesizes data to classify, Hardcoded pre defined values for 2 classes
    """
    meanc1 = [2,5.5] #class 1
    covalence1 = [[1,1],[1,2.5]] #covariance for class 1
    var1 = np.random.multivariate_normal(meanc1, covalence1, samp1)
    meanc2 = [.5,1] #class 2
    covalence2 = [[1,0],[0,1]] #covariance class 2
    var2 = np.random.multivariate_normal(meanc2, covalence2, samp2)

    return (var1, var2) #Multivariate data synthesized

def labels(x, y, f, g):
    """ 
    Testing labels for '0' and '1'
    """
    zero_l = np.zeros((x, 1)) #Label for zeroes
    z_class = np.hstack((f, zero_l)) #'0' label for class1
    one_l = np.ones((y, 1)) #label for ones
    o_class = np.hstack((g, one_l)) #'1' label for class2
    datalines = np.concatenate((z_class, o_class), axis=0)
    #print (datalines)
    
    return datalines

def NNclassifier(training, test):
    """
    Classifies NN and returns label
    """
    normal_s = training.shape[1] #normal size of training set
    newlabel = np.zeros(shape=((test.shape[0]))) #size of test set
    z = np.zeros(shape=((training.shape[0]),1)) #size variable for training
    r = np.delete(training, normal_s-1, 1) #regresses element
    #print x
    s = np.delete(test, normal_s-1, 1)
    #print y
    #print a
    for i in range(test.shape[0]):
        for j in range(training.shape[0]):
            euclid = np.linalg.norm(r[j]-s[i]) #computs Euclid dist.
            z[j] = euclid #given nearest value
        NN = np.sort(z, axis = 0) #sorts nearest values

        for k in range(z.shape[0]): #inner elements of numpy array
            if (NN[0] == z[k]): 
                index = k #important
        setlabel = training[index]
        setlabel= setlabel[(setlabel.shape[0]) - 1]
        newlabel[i] = setlabel #setting label to already set training label
    print (newlabel)
    
    return newlabel
    

def n_validator(test_data, p, classifier, *args):
    """
    Validates accuracy of NNclassifier
    """
    chart = 0
    np.random.shuffle(test_data) #shuffles random data
    norm_s = test_data.shape[1] #a size for parameter value
    p_partions = test_data.shape[0] / p #Mistakenly took for str' type
    testdata = np.array_split(test_data, p) #splits array 
    testdata = np.asarray(testdata) #new as array for tdata

    for j in range(testdata.shape[0]): #will run through entire data set
        test = testdata[j]
        left = np.delete(testdata, j, axis=0) #eradicates to new labels
        left = np.vstack(left) #stacks vertically left over
        classtest = np.zeros(shape=(p_partions, norm_s)) #more test set
        
        for k in range(classtest.shape[0]):
            classtest[k] = test[k] #must take second nested element
        classtrain = np.zeros(shape=((p_partions * (p-1)), norm_s))
        
        for i in range(classtrain.shape[0]): #do not indent
            classtrain[i] = left[i]
        labels = classifier(classtrain, classtest, *args) #unsure to classify
        original_labels = classtest[:,(norm_s - 1)]

        for l in range(labels.shape[0]): #comparator
            if (labels[l] == original_labels[l]): 
                chart += 1 #counter for return value

    return (chart / float(test_data.shape[0])) #precision value returned
