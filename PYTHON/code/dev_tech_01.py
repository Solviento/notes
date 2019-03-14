# python hw #2

# sum
# sum is a builtin function that will sum a list
sum([35.3, 3,2])
# 40.3

# type - can always find the type of an object with the 'type' function
[type(234), type(range(2,44)), type(zip())]
# [int, range, zip]

# isinstance
# 'isinstance' is a little more concise then 'type' in some situations

type(234) == int
isinstance(234, int)
# True

# zip
# zip stops after shortest list is exhausted
list(zip(range(10), range(4, 7)))
# [(0, 4), (1, 5), (2, 6)]

# a common way to recurse thru a nested list is to split the list into the 0th element, and the rest of the list
# count the number of elements in a nested list
def rcount(x):
    if x == []:
        return(0)
    if isinstance(x, list):
        return rcount(x[0]) + rcount(x[1:])
    else:
        # not a list
        return(x)

rcount([3,4,[3,4,[4,2,3],3],3])
# 23

# - write a function(dot) that computes the standard 'dot products' 
# between two lists 
# - example: dot([1,2,3], [4,5,6]) = $$1 * 4 + 2 * 5 + 3 * 6 = 32$$
# - if one vector is longer than the other, the extra elements are ignored
def dot(list1, list2):

    list1 = list1[:3]
    list2 = list2[:3]
    'list1[:3] will list up until the first 3 elements'
    'list1[:2] likewise, will list up until the first 2 elements'
    
    xcord1, ycord1, zcord1 = list1
    xcord2, ycord2, zcord2 = list2

    xprod = xcord1 * xcord2
    yprod = ycord1 * ycord2
    zprod = zcord1 * zcord2

    dotproduct = xprod + yprod + zprod

    return dotproduct
# test vectors for Problem 1 a,b,c,d

tv0 = [1,2,3]
tv1 = [4,5,6,7,8,9]
dot(tv0, tv1)
# 32

# write 'shortlong', a function that takes two vectors, and returns in a list the shorter vector, the short vector length, the long vector, and the long vector length
def shortlong(list1, list2):
    
    length1 = len(list1)
    length2 = len(list2)
    
    if (length1 < length2):
        
        outputList = [list1, length1, list2, length2]
        return outputList
        
    if (length2 < length1):
        
        outputList = [list2, length2, list1, length2]
        return outputList

shortlong(tv0, tv1)
# [[1, 2, 3], 3, [4, 5, 6, 7, 8, 9], 6]

# - write a more flexible version of dot, 'dotmv'
# - dotmv takes an extra 'offset' arg, which moves the shorter vector to the right
# - use 'shortlong'
# - dotmv(tv0, tv1, 2) = $$1 * 6 + 2 * 7 + 3 * 8$$

def dotmv(list1, list2, offset):
    
    length1 = len(list1)
    length2 = len(list2)
    
    if (length1 > length2):
        list1 = list1[offset:]
    if (length2 > length1):
        list2 = list2[offset:]
        
    return dot(list1, list2)

# another version of dot, 'dotpad'
# dotpad takes a pad arg
# if one vector is shorter, it is padded on the right with the pad value
# don't modify the input vectors
# dotpad(tv0, tv1,1) = dot([1,2,3,1,1,1], [4,5,6,7,8,9])
def dotpad(list1, list2, padNumber):
    
    length1 = len(list1)
    length2 = len(list2)
    dotProduct = 0
    
    if (length1 > length2):
        iteration = length1 - length2
        for i in range(0, iteration):
            list2.append(padNumber)
            
        for i in range(0, length1):
            cordProduct = list1[i] * list2[i]
            dotProduct = dotProduct + cordProduct
            
    if (length2 > length1):
        iteration = length2 - length1
        for i in range(0, iteration):
            list1.append(padNumber)
            
        for i in range(0, length2):
            cordProduct = list1[i] * list2[i]
            dotProduct = dotProduct + cordProduct
         
    return dotProduct

[dotmv(tv0, tv1, 0), dotmv(tv0, tv1, 1), dotmv(tv0, tv1, 2)]
# [32, 38, 44]

[dotpad(tv0, tv1, 0), dotpad(tv0, tv1, 1), dotpad(tv0, tv1,2)]
# [32, 0, 0]

# - argument: a non-nested list of objects
# - returns: a dictionary, where there is 
# a key for each type found. the value of each
# key is a list of the objects of that type found.
# - prints: 
#   - the number of each type found
#   - the sum, if any, of the ints, and floats found
#   - the strings, if any, sorted alphabetically, and concatenated
#   togther, separated by '|'
def removeCharacters(string):
    removeClass = "<class '"
    removeRest = "'>"

    halfString = string.replace(removeClass, "")
    editedString = halfString.replace(removeRest, "")

    return editedString

def cbt(list1):
    
    'Need to implement better subfunctions'
    
    length1 = len(list1)
    
    masterDictionary = {} 
    foundDictionary = {}
    
    stringList = []
    stringConcatAlpha = ""
    floatSum = 0
    intSum = 0
    
    for i in range(0, length1):
        
        key = removeCharacters(str(type(list1[i])))
        
        if (key in masterDictionary):
            
            List = masterDictionary.get(key) #Get list from key
            List.append(list1[i]) #Add element to list
            masterDictionary[key] = List #Updates value in list
            
            #print(List)
            
        if (key not in masterDictionary):
            masterDictionary[key] = [list1[i]] #Create key & list    
        
        if type(list1[i]) in foundDictionary:
            value = foundDictionary.get(type(list1[i]))
            value = value + 1
            foundDictionary[type(list1[i])] = value
            'Looks for key in dictionary, adds +1 to counter'
            
            if (type(list1[i]) == type("Yay")):
                stringList.append(list1[i])
                
            if (type(list1[i]) == type(1)):
                intSum += list1[i]
                
            if (type(list1[i]) == type(1.0)):
                floatSum += list1[i]
                
            'Checks for string, floats, and ints already in dictionary'
                
        else:
            foundDictionary.update({type(list1[i]) : 1})
            'Adds key to dictionary'
            if (type(list1[i]) == type("Yay")):
                stringList.append(list1[i])
                
            if (type(list1[i]) == type(1)):
                intSum += list1[i]
                
            if (type(list1[i]) == type(1.0)):
                floatSum += list1[i]
               
            'Checks for string, floats, and ints'
                
    for key in foundDictionary:
        print("Found " + str(foundDictionary[key]) + " of " + str(key))
    
    if (intSum != 0):
        print("sum of <class 'int'> is " + str(intSum))
        
    if (floatSum != 0):
        print("sum of <class 'float'> is " + str(floatSum))
    'Prints found types and float, int sums'
    
    stringList.sort()
    
    for i in stringList:
        stringConcatAlpha += (i + "|")
    'Alpha sorted concatenation of all string types'
    
    print("alpha sorted concat of strings: " + stringConcatAlpha)
    
    return masterDictionary
    'Returns specified result'

def partition(l, n, overlap):
    
    list1 = [l[i: i + n] for i in range(0, len(l), n-overlap)]
    
    if (overlap > 0):
        #del list1[len(list1) - 1]
        #list1.pop(-1)
        x = 1
    'Need to remove non n segments in return list1'
        
    print(list1)
    return list1

x = [23, 2**20, 3.14,'shapiro', 2**10+7, sorted,2.34, 'science', len, 43, 'butler', 'unicode']
cbt(x)

# Found 2 of <class 'float'>
# Found 4 of <class 'str'>
# Found 4 of <class 'int'>
# Found 2 of <class 'builtin_function_or_method'>
# sum of <class 'int'> is 1049673
# sum of <class 'float'> is 5.48
# alpha sorted concat of strings: butler|science|shapiro|unicode|

# {'builtin_function_or_method': [<function sorted>, <function len>],
#  'float': [3.14, 2.34],
#  'int': [23, 1048576, 1031, 43],
#  'str': ['shapiro', 'science', 'butler', 'unicode']}

# - write a function(partition), that divides a list into segments
# - arg 'l' is the input list
# - arg 'n' is the length of each segment. if there are not enough list elements to make a final segment of length n, they are discarded
# - arg 'overlap' is how many list elements should overlap btw adjacent segments
# - remember range is range(inclusive, exclusive), range[0,2] => [0,1]
# - might want to use 'while' instead of 'for'

def partition(l, n, overlap):
    
    list1 = [l[i: i + n] for i in range(0, len(l), n-overlap)]
    
    if (overlap > 0):
        #del list1[len(list1) - 1]
        #list1.pop(-1)
        x = 1
    'Need to remove non n segments in return list1'
        
    #print(list1)
    return list1

partition(list(range(10)), 2, 0)
# [[0, 1], [2, 3], [4, 5], [6, 7], [8, 9]]

# only want len 3 partitions, so 9 was discarded

partition(list(range(10)), 3, 0)
#[[0, 1, 2], [3, 4, 5], [6, 7, 8], [9]]

partition(list(range(10)), 4, 3)
# [[0, 1, 2, 3],
#  [1, 2, 3, 4],
#  [2, 3, 4, 5],
#  [3, 4, 5, 6],
#  [4, 5, 6, 7],
#  [5, 6, 7, 8],
#  [6, 7, 8, 9],
#  [7, 8, 9],
#  [8, 9],
#  [9]]

# - write 'expandlazy' - if given a 'lazy' range, zip, or enumerate, expand it into a list
def expandlazy(anyType):
 
    'Checks each type, acts accordingly for return value'
    
    if (isinstance(anyType, int)):
        return (anyType)
        
    if (isinstance(anyType, str)):
        return anyType
    
    if (isinstance(anyType, range)):
        
        rangeList = list(anyType)
        
        return (rangeList)
    
    if (isinstance(anyType, enumerate)):
        
        enumList = []
        for i in anyType:
            enumList.append(i)
        
        return enumList
        
    if (isinstance(anyType, zip)):
        
        ziplist = []
        
        for i in anyType:
            ziplist.append(i)
        
        return ziplist
    
[expandlazy(234), expandlazy(range(3)), expandlazy('asdf'), expandlazy(enumerate(['a','b','c']))]
# [234, [0, 1, 2], 'asdf', [(0, 'a'), (1, 'b'), (2, 'c')]]

# - write 'expandlazylist' - expand any lazy elements of a non nested list
def expandlazylist(anyType):
    
    'Uses expand lazy to evaluate each element in input list'
    
    anyList = []
    
    for i in anyType:
        
        if (isinstance(i, int)):
    
            anyList.append(expandlazy(i))
        
        if (isinstance(i, str)):
            
            anyList.append(expandlazy(i))
            
        if (isinstance(i, range)):
            
            anyList.append(expandlazy(i))
            
        if (isinstance(i, enumerate)):
            
            anyList.append(expandlazy(i))
            
        if (isinstance(i, zip)):
            
            anyList.append(expandlazy(i))
            
    return anyList

x = [1,2,3, range(4), 5, zip([1,2,3], [4,5]), 'asdf', enumerate(['a', 'b', 'c'])]
expandlazylist(x)

# [1,
#  2,
#  3,
#  [0, 1, 2, 3],
#  5,
#  [(1, 4), (2, 5)],
#  'asdf',
#  [(0, 'a'), (1, 'b'), (2, 'c')]]

# - 'flatten' turns a nested list into a non-nested linear one
# - use recursion
def flatten(nestedList):
    
    flatten = []
    
    for i in nestedList:
        
        if type(i) == list:
            for j in i:
                if type(j) == list:
                    for k in j:
                        flatten.append(k)
                        #if type(k) == list:
                            #for u in k:
                                #flatten.append(u)
                else:
                    flatten.append(j)
                'Will iterate through an append each element in the list'
        else:
            flatten.append(i)
            'Append each element as normal'
            
    return flatten
    
flatten([1,[2,3,4,[5,6,[7,8],9],11]])
# [1, 2, 3, 4, 5, 6, [7, 8], 9, 11]













