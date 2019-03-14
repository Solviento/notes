# hw3

# you can terminate a generator by using 'return',
# or falling off the end of the generator

def g1():
    yield(1)
    yield(2)
    return
    yield(3)

def g2():
    yield(1)
    yield(2)

# can get the elements from a 
# FINITE length generator with 'list'

list(g1())
# [1, 2]

list(g2())
# [1, 2]

# if a generator calls a 2nd generator for elements, 
# and the 2nd generator finishes, the 1st one will finish as well

def g3(g):
    while True:
        yield(next(g))

for i in g3(g2()):
    print(i)

# 1
# 2

# string replace method

'324234213foo1234324'.replace('foo','XYZ')
# '324234213XYZ1234324'

# divmod gives integer quotient and remainder
divmod(13, 5)
# (2, 3)

# 'bin' function yields the binary representation of a int
#  returns a string of 0/1's, plus a '0b' prefix

[bin(11), bin(31), bin(32)]
# ['0b1011', '0b11111', '0b100000']

# 1 and 0 function as True and False in an 'if' statement

[True if 1 else False, True if 0 else False]
# [True, False]

# the set constructor function can take a list
set([1,2,3])
# {1, 2, 3}

# an empty set prints as 'set()'
set([])
# set()

# can check for presense of a key in a dictionary with 'in' operator
d = {'foo':234}
['foo' in d, 'bar' in d]
[True, False]

# - define a 'decimals' generator function, that 'generates' the decimal digits of 1/n, where n is an integer greater than 1
# - if the decimal expansion terminates, like 1/8 = .125, the generator should terminate. otherwise, like for 1/3=.333..., the generator should never stop
# - use long division to compute the expansion - it is very simple
def decimals(divisor):
    
    r = 10
    
    #Might want to add divmod here to check for repeating q, r
    
    while(r > 0):
        
        q, r = divmod(r, divisor)
        
        yield(q)
        
        r *= 10

# example: 1/8 = .125
# the digits of the expansion are the quotients

r = 10
q,r = divmod(r,8)
print(q,r)
r *= 10
q,r = divmod(r,8)
print(q,r)
r *= 10
q,r = divmod(r,8)
print(q,r)
# r == 0, so done

# 1 2
# 2 4
# 5 0

# 1/3 = .333...

r = 10
q,r = divmod(r,3)
print(q,r)
r *= 10
q,r = divmod(r,3)
print(q, r)

# (q,r) pairs have repeated, will never terminate

# 3 1
# 3 1

# finite generator

list(decimals(8))
# [1, 2, 5]

# infinite generator
# can't call 'list' on it
g = decimals(3)
[next(g), next(g), next(g), next(g)]
# [3, 3, 3, 3]

# - define 'genlimit(g, limit)', which generates at most 'limit' number of values from a generator 'g'
def genlimit(g, limit):
    
    counter = 0
    
    #g is decimals(divisor)
    
    while (counter < limit):
        
        x = next(g)
        
        yield(x)
        
        counter = counter + 1

list(genlimit(decimals(8), 5))
# [1, 2, 5]

list(genlimit(decimals(3), 5))
# [3, 3, 3, 3, 3]

# works with any interator
list(genlimit(iter(range(30)), 3))
# [0, 1, 2]

# - genlimit is useful, but never sure what we're missing with an arbitrary limit
# - since 1/n is a rational number, its decimal expansion must eventually repeat(unlike irrational numbers like PI)
# - write 'decimals2', a variant of 'decimals' 
# - if the decimal expansion is finite, it should just return the finite set of digits
# - if the decimal expansion repeats, it should return the digits up to the point it starts repeating. then the final yield should be a list of the repeating sequence of digits
# - hint - keep a list, 'seen', of the [quotient, remainder] pairs as you generate digits. if you generate a new pair that is already in 'seen', you know you have started to repeat. 
def decimals2(divisor):
    
    #Yields (or generates) the decimals of 1/divisor
    #Halts and yields pattern if one exists
    #i.e. decimals2(3) -> [3, [3]] "1/3 = .33..."
    
    r = 10
    
    seen = []
    repeatedDigits = []
    
    while(r > 0):
        
        q, r = divmod(r, divisor)
        
        if ([q, r] in seen):
            
            break
            #Based on hint from hw
        
        seen.append([q, r])
          
        repeatedDigits.append(q)
                
        yield(q)

        r *= 10
    
    yield (repeatedDigits)

import textwrap

for j in range(3,30,2): 
    d = list(decimals2(j))
    print('   Expansion of 1/' + str(j) + ':')
    # hack needed because lines don't wrap in pdf version
    print( textwrap.fill(str(d), 80))

#    Expansion of 1/3:
# [3, [3]]
#    Expansion of 1/5:
# [2, [2]]
#    Expansion of 1/7:
# [1, 4, 2, 8, 5, 7, [1, 4, 2, 8, 5, 7]]
#    Expansion of 1/9:
# [1, [1]]
#    Expansion of 1/11:
# [0, 9, [0, 9]]
#    Expansion of 1/13:
# [0, 7, 6, 9, 2, 3, [0, 7, 6, 9, 2, 3]]
#    Expansion of 1/15:
# [0, 6, [0, 6]]
#    Expansion of 1/17:
# [0, 5, 8, 8, 2, 3, 5, 2, 9, 4, 1, 1, 7, 6, 4, 7, [0, 5, 8, 8, 2, 3, 5, 2, 9, 4,
# 1, 1, 7, 6, 4, 7]]
#    Expansion of 1/19:
# [0, 5, 2, 6, 3, 1, 5, 7, 8, 9, 4, 7, 3, 6, 8, 4, 2, 1, [0, 5, 2, 6, 3, 1, 5, 7,
# 8, 9, 4, 7, 3, 6, 8, 4, 2, 1]]
#    Expansion of 1/21:
# [0, 4, 7, 6, 1, 9, [0, 4, 7, 6, 1, 9]]
#    Expansion of 1/23:
# [0, 4, 3, 4, 7, 8, 2, 6, 0, 8, 6, 9, 5, 6, 5, 2, 1, 7, 3, 9, 1, 3, [0, 4, 3, 4,
# 7, 8, 2, 6, 0, 8, 6, 9, 5, 6, 5, 2, 1, 7, 3, 9, 1, 3]]
#    Expansion of 1/25:
# [0, 4, [0, 4]]
#    Expansion of 1/27:
# [0, 3, 7, [0, 3, 7]]
#    Expansion of 1/29:
# [0, 3, 4, 4, 8, 2, 7, 5, 8, 6, 2, 0, 6, 8, 9, 6, 5, 5, 1, 7, 2, 4, 1, 3, 7, 9,
# 3, 1, [0, 3, 4, 4, 8, 2, 7, 5, 8, 6, 2, 0, 6, 8, 9, 6, 5, 5, 1, 7, 2, 4, 1, 3,
# 7, 9, 3, 1]]

# - define a function 'select(input, selectors)', where 'input' and 'selectors' lists are the same length
# - 'select' returns a new list which consists of the elements of input that have a True value in the corresponding selectors element
# - remember 'generalized booleans'
def select(inputList, selectors):
    #Note: I changed input to inputList for syntax reasons
    #Will return a list representation like so:
    #abcdef (input)
    #010100 (selector)
    #-b-d-- (Result)

    length = len(inputList)
    
    outputList = []
    
    for i in range(0, length):

        if (type(selectors[i]) == list):
            
            if (len(selectors[i]) != 0):
                
                outputList.append(inputList[i])
        
        if (type(selectors[i]) == str):
            
            if (len(selectors[i]) != 0):
                
                outputList.append(inputList[i])
                
        if (type(selectors[i]) == bool):
            
            if selectors[i] != False:
                
                outputList.append(inputList[i])
                
        if (type(selectors[i]) == int):
            
            if selectors[i] != 0:
                
                outputList.append(inputList[i])
            
    return outputList

select(range(7), [0, 1, '', 'foo', True, [], [1,2]])
# [1, 3, 4, 6]

select([x*3 for x in [4,2,1]] , [0,1,0])
# [6]

# define a function 'intToNDigits(x, n)'
# returns a list of the digits(int 0 and 1, not strings) in a base 2 representation of 'x'
# list must have n digits, pad with 0 on the left if needed
def intToNDigits(x, n):
    
    #Converts int x to binary representation
    
    bits = []
    
    division = x // 2
    
    while(division > 0):
        
        division = x // 2
        
        bit = x % 2
        
        bits.insert(0, bit)
        
        x = division
        #Divide by 2, mod remainder by 2, get bit
        #Rinse and repeat
        
    if (x == 1):
        bits.insert(0, 1)
        
    length = len(bits)
    
    difference = n - length
    
    for i in range(0, difference):
        
        bits.insert(0, 0)
    
    #print(bits)
    return (bits)

[intToNDigits(3, 2), intToNDigits(3, 6), intToNDigits(11, 4)]
# [[1, 1], [0, 0, 0, 0, 1, 1], [1, 0, 1, 1]]

# - using 'select' and'intToNDigits', define a function 'powerSet(x)' that returns a list
# of all possible subsets of the elements of input list x, including the empty set and the set of all elements
# - if a set has N elements, the power set will have 2**N elements
def powerSet(x):
    #Uses select and intToNDigits functions
    #x is a list

    length = len(x)
    
    power = 2 ** length
    
    selectorList = []
    
    for i in range(1, power): 
        
        selectorList.append(intToNDigits(i, length))
        
    subset = []
    subset.append(set())
    
        
    for j in selectorList:

        sub = set()        

        selectList = select(x, j)     
        
        for m in selectList:
            sub.add(m)
        
        subset.append(sub)
     
    return (subset)

powerSet(['avery', 'math', 'butler'])
# [set(),
#  {'butler'},
#  {'math'},
#  {'butler', 'math'},
#  {'avery'},
#  {'avery', 'butler'},
#  {'avery', 'math'},
#  {'avery', 'butler', 'math'}]

powerSet(['avery', 'math', 'butler', 'dodge'])
# [set(),
#  {'dodge'},
#  {'butler'},
#  {'butler', 'dodge'},
#  {'math'},
#  {'dodge', 'math'},
#  {'butler', 'math'},
#  {'butler', 'dodge', 'math'},
#  {'avery'},
#  {'avery', 'dodge'},
#  {'avery', 'butler'},
#  {'avery', 'butler', 'dodge'},
#  {'avery', 'math'},
#  {'avery', 'dodge', 'math'},
#  {'avery', 'butler', 'math'},
#  {'avery', 'butler', 'dodge', 'math'}]

len(powerSet(['avery', 'math', 'butler', 'dodge']))
# 16

# - take the dot product of any number of lists and finite generators
# - hints - refering to 05-functions
#     - use the variable number of arguments format
#     - you might find it convenient to 'spread a list of args' to 'zip'
def dotn(*args):
    
    #Dot product of any number of generator and list inputs
    
    x = zip(*args)

    zipList = list(x)
    length = len(zipList) 
    
    dotProduct = 0
    
    for i in range(0, length):

        subZip = zipList[i]
        subLength = len(subZip)
        
        sumProduct = 1
        
        for i in range(0, subLength):
            sumProduct = sumProduct * subZip[i]
            #zip() allows for product of each sublength
        
        dotProduct = dotProduct + sumProduct
        
    return dotProduct 

def g(s, e):
    for j in range(s, e):
        yield j

dotn([5,3,9], g(10,12))
# 83

# above is 
# number of terms is length of shortest sequence

5*10 + 3*11 
# 83 

dotn([2,3,7], g(0,3), g(2,8))
# 65

# - define 'countBases(dna)' - returns the number of 'A', 'C', 'G', 'T' bases in a strand of DNA in a dict
def countBases(dna):
    
    bases = 'ACGT'
    hashDictionary = {}
    
    for i in range(0, 4):

        base = bases[i]
        
        hashDictionary[base] = 0
        
    for j in range(0, len(dna)):
        
        if (dna[j] in hashDictionary.keys()):
            
            count = hashDictionary[dna[j]]
            
            count = count +1
            
            hashDictionary[dna[j]] = count
            #Counter update for each dna strand found
            
    return (hashDictionary)

# dna strings use upper case letters
bases = 'ACGT'
dna = 'CATCGATATCTCTGAGTGCAC'

countBases('AACAT')
# {'A': 3, 'C': 1, 'G': 0, 'T': 1}

countBases(dna)
# {'A': 5, 'C': 6, 'G': 4, 'T': 6}

# - return the percentage of each base in a strand of DNA in a dict
def percentBases(string):
   
    #Gives percentage of keys in a hashmap
   
   dnaTable = countBases(string)
   
   total = 0
   
   percentDictionary = {}
   
   for key, value in dnaTable.items():
       
       total = total + value
       
   for i, j in dnaTable.items():
       
       freq = j / total
       insertSubset = {i: freq}
       percentDictionary.update(insertSubset)
       #Updates with alpha and percentage
       
   return (percentDictionary)
    
percentBases('ACG')
# {'A': 0.3333333333333333,
#  'C': 0.3333333333333333,
#  'G': 0.3333333333333333,
#  'T': 0.0}

percentBases(dna)
# {'A': 0.23809523809523808,
#  'C': 0.2857142857142857,
#  'G': 0.19047619047619047,
#  'T': 0.2857142857142857}

# - define 'reverseComplement(dna)' 
# - swaps A <-> T, C <-> G, and returns the new DNA in reverse order
def reverseComplement(dna):
    
    #Will exchange alphas and reverse a dna string
    newDna = ""
    reverseDna = ""
    
    for i in range(0, len(dna)):
        
        if (dna[i] == 'A'):
            newDna = newDna + 'T'
            
        if (dna[i] == 'T'):
            newDna = newDna + 'A'
            
        if (dna[i] == 'C'):
            newDna = newDna + 'G'
            
        if (dna[i] == 'G'):
            newDna = newDna + 'C'
    
    for j in newDna[::-1]: 
        #Reverse transversal of newDna
        
        reverseDna = reverseDna + j
    
    return(reverseDna)

reverseComplement('ACGT')
# 'ACGT'

reverseComplement(dna)
# 'GTGCACTCAGAGATATCGATG'

reverseComplement('ACGT')
# 'ACGT'











