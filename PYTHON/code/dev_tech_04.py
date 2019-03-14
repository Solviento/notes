# hw 5
import numpy as np

m = np.array(range(9))
m
# array([0, 1, 2, 3, 4, 5, 6, 7, 8])

[m.shape, m.sum()]
# [(9,), 36]

m2 = m.reshape((3,3))
m2
# array([[0, 1, 2],
#        [3, 4, 5],
#        [6, 7, 8]])

m2.shape
# (3, 3)

# column sums

m2.sum(axis=0)
# array([ 9, 12, 15])

# row sums

m2.sum(axis=1)
# array([ 3, 12, 21])

# can "rotate" a matrix by 90 degrees CCW
np.rot90(m2, 5)
# array([[2, 5, 8],
#        [1, 4, 7],
#        [0, 3, 6]])

# b is a 'boolean array'
# the array elements are type boolean

b = m %2 == 0
b
# array([ True, False,  True, False,  True, False,  True, False,  True], dtype=bool)

# when a boolean array is used as an index,
# the array elements at the same position 
# as True elements are placed in a 1D array

m[b]
# array([0, 2, 4, 6, 8])

# all ANDs together all the array elements

b.all()
# False

# all elements True

b2 = m > -1
b2
# array([ True,  True,  True,  True,  True,  True,  True,  True,  True], dtype=bool)

b2.all()
# True

# another way to get a boolean array

np.identity(3, dtype=bool)
# array([[ True, False, False],
    #    [False,  True, False],
    #    [False, False,  True]], dtype=bool)

# give Exception a string and you get a reasonable error message
raise Exception('Reasonable message')

# ---------------------------------------------------------------------------
# Exception                                 Traceback (most recent call last)
# <ipython-input-14-add5bfc66b9e> in <module>()
#       1 # give Exception a string and you get a reasonable error message
#       2 
# ----> 3 raise Exception('Reasonable message')

# Exception: Reasonable message

# - a magic square is a 2D square array where all the rows, columns, and both diagonals sum to the same number
# - write function 'magic' 
#     - if arg is not a magic square, return false
#     - if arg is a magic square, return the sum
#     - ideally, your function should not have any 'for' loops

import numpy as np

def magic(array):
    
    if not isinstance(array, np.ndarray):
        raise Exception('Not an array')
        
    s = array.shape
    if not 2 == len(s):
        raise Exception('Not a 2D array')
            
    if not s[0] == s[1]:
        raise Exception('Not a square')
        
    col = array.sum(axis=0) #Column sums
    row = array.sum(axis=1) #Row sums
    
    diag = sum([ array[i][i] for i in range(len(array))])
    diag2 = sum([ array[len(array)-i-1][i] for i in range(len(array))])
    
    if diag != diag2:
        return False
    elif col.all() == row.all():
        #print (diag)
        return diag
    else:
        return False
    
# check data type
magic([2,3,4])
# Exception: Not an array

# check for 2D
magic(np.array([4,5]))
# Exception: Not a 2D array

m = np.array([[3, 11,  6],
             [ 9,  7,  5],
             [ 8,  3, 10]])

magic(m)
#  False

# fix it
m[0,0] = 4
m
# array([[ 4, 11,  6],
#        [ 9,  7,  5],
#        [ 8,  3, 10]])

magic(m)
# 21

m = np.array([22,12,18,87,88,17,9,25,10,24,89,16,19,86,23,11])
m = m.reshape((4,4))
m
# array([[22, 12, 18, 87],
#        [88, 17,  9, 25],
#        [10, 24, 89, 16],
#        [19, 86, 23, 11]])

magic(m)
# 139

# - suppose we want to convert between C(Celsius) and F(Fahrenheit), using the equation 9*C = 5*(F-32)
# - could write functions 'c2f' and 'f2c'
# - do all computation in floating point for this problem

def c2f(c):
    return( (9.*c + 5. * 32.)/5. )

def f2c(f):
    return( 5.*(f  - 32)/9. )

[c2f(0), c2f(100), f2c(32), f2c(212)]
# [32.0, 212.0, 0.0, 100.0]

# - to write f2c, we solved the equation for C, and made a function out of the other side of the equation
# - to write c2f, we solved for F, ...
# - there is another way to think about this 
# - rearrange the equation into a symmetric form

# ```
# 9*C - 5*F = -32*5
# ```

# - you can think of the equation above as a "constraint" between F and C. if you specify one variable, 
# the other's value is determined by the equation. in general, if we have

# ```
# c0*x0 + c1*x1 + ... cN*xN = total
# ```

# - cI are fixed coefficients
# - specifying any N of the (N+1) x's will determine the remaining x variable
# - define the 'setvar' method on the 'Constaint' class
#     - does 'constraint satisfaction'
#     - you may find 'dotnone' to be helpful

# regular dot product, except that if or both values in a pair is 'None',
# that term is defined to contribute 0 to the sum

def dotnone(l1, l2):
    '''another dot product variant'''
    sum = 0
    for e1,e2 in zip(l1,l2):
        if not (e1 is None or e2 is None):            
            sum += e1 * e2
    return(sum)

# [dotnone([1,2,3], [4,5,6]), dotnone([1,None,3], [4,5,6]), dotnone([None,1], [2,None])]
# [32, 22, 0]

import copy

class Constraint:
    def __init__(self, varnames, coes, total):
        self.varnames = varnames
        self.coes= [float(c) for c in coes]
        self.total = float(total)
        self.varvals = [None] * len(coes)
        
    def __str__(self):
        return self.__repr__()
    
    def __repr__(self):
        # display the status of the constraint
        # show which vars have values
        x = ' + '.join(['{}*{}(={})'.format(coe, var, val) 
                        for coe,var,val in zip(self.coes, self.varnames, self.varvals)])
        return 'Constraint({}={})'.format(self.total, x)
    
    def setvar(self, arg1, arg2):
        # 1st arg - variable index or name (0 || C for Celsius, 1 || F for Farenh)
        # 2nd arg - variable value (C, 100 -> Cels = 100, Faren = 212)
        
        if (type(arg1) == int):
            self.varvals[arg1] = arg2
            
        if (arg1 in self.varnames): #For strings only
            ind = self.varnames.index(arg1)
            self.varvals[ind] = arg2 #C,F variables set
            
        noneSentinal = 0
        
        for i in self.varvals:
            if i is None:
                noneSentinal += 1
            else:
                continue
                
        totalSum = self.total
        totalSum -= dotnone(self.coes, self.varvals)
        
        if noneSentinal == 1:
            for j in self.varvals:
                if type(j) is type(None):
                    ind = self.varvals.index(j)
                    self.varvals[ind] = totalSum / self.coes[ind]

                    rt = copy.copy(self.varvals)
                    for k in self.varvals:
                        self.varvals[self.varvals.index(k)] = None
                    
                    return rt

# setup constraint btw C and F
# 1st arg is var names, 
# 2nd arg is coefficients
# 3rd arg is total

c = Constraint(['C', 'F'], [9,-5], -5*32)
c
# Constraint(-160.0=9.0*C(=None) + -5.0*F(=None))

# 1st arg - variable index or name
# 2nd arg - variable value
# setvar will fire when there is only one unset variable remaining
# when it fires, setvar will print the variable values, 
# return them in a list, and clear all the variable values

c.setvar(0, 100)
# [100, 212.0]

# can set var by index or name

c.setvar('C', 0)
c
# Constraint(-160.0=9.0*C(=None) + -5.0*F(=None))

c.setvar('F', 212)
# [100.0, 212]

# more complex example
# 5 vars

c2 = Constraint(['x0', 'x1', 'x2', 'x3', 'x4'], range(5), 1)
c2
# Constraint(1.0=0.0*x0(=None) + 1.0*x1(=None) + 2.0*x2(=None) + 3.0*x3(=None) + 4.0*x4(=None))

c2.setvar('x1', 10)
c2
# Constraint(1.0=0.0*x0(=None) + 1.0*x1(=10) + 2.0*x2(=None) + 3.0*x3(=None) + 4.0*x4(=None))

c2.setvar('x0', 0)
c2
# Constraint(1.0=0.0*x0(=0) + 1.0*x1(=10) + 2.0*x2(=None) + 3.0*x3(=None) + 4.0*x4(=None))

# x2

c2.setvar(2,20)
c2
# Constraint(1.0=0.0*x0(=0) + 1.0*x1(=10) + 2.0*x2(=20) + 3.0*x3(=None) + 4.0*x4(=None))

# only two unset vars left, so setting x3 or x4 
# will fire the constraints

c2.setvar('x4', 30)
# [0, 10, 20, -56.333333333333336, 30]

# - Python is very popular in 'digital humanities'
# - MIT has the complete works of Shakespeare in a simple [html](http://shakespeare.mit.edu) format
# - You will do a simple analysis of Hamlet by reading the html file, one line at 
# a time(usual iteration scheme) and doing pattern matching
# - The goal is to return a list of the linecnt, total number of 'speeches'(look at the file format), 
# and a dict showing the number of 'speeches' each character gives
# - Your program should read directly from the url given, but you may want to download
# a copy to examine the structure of the file. 
# - remember that urllib.request returns 'byte arrays', not strings
# - there are at least three ways to do this - your choice
#     - use string methods like 'find'
#     - use regular expressions
#     - use Beautiful Soup
# - here's a short sample of the file

# ```

# <A NAME=speech25><b>HORATIO</b></a>
# <blockquote>
# <A NAME=1.1.37>Tush, tush, 'twill not appear.</A><br>
# </blockquote>

# <A NAME=speech26><b>BERNARDO</b></a>
# <blockquote>
# <A NAME=1.1.38>Sit down awhile;</A><br>
# <A NAME=1.1.39>And let us once again assail your ears,</A><br>
# <A NAME=1.1.40>That are so fortified against our story</A><br>
# <A NAME=1.1.41>What we have two nights seen.</A><br>
# </blockquote>

# <A NAME=speech27><b>HORATIO</b></a>
# <blockquote>
# <A NAME=1.1.42>Well, sit we down,</A><br>
# <A NAME=1.1.43>And let us hear Bernardo speak of this.</A><br>
# </blockquote>

# <A NAME=speech28><b>BERNARDO</b></a>
# <blockquote>
# <A NAME=1.1.44>Last night of all,</A><br>
# <A NAME=1.1.45>When yond same star that's westward from the pole</A><br>
# <A NAME=1.1.46>Had made his course to illume that part of heaven</A><br>
# <A NAME=1.1.47>Where now it burns, Marcellus and myself,</A><br>
# <A NAME=1.1.48>The bell then beating one,--</A><br>
# <p><i>Enter Ghost</i></p>
# </blockquote>

# <A NAME=speech29><b>MARCELLUS</b></a>
# <blockquote>
# <A NAME=1.1.49>Peace, break thee off; look, where it comes again!</A><br>
# </blockquote>

# <A NAME=speech30><b>BERNARDO</b></a>
# <blockquote>
# <A NAME=1.1.50>In the same figure, like the king that's dead.</A><br>
# </blockquote>
# ```

# use this url for hamlet - do not hit MIT directly
# break up long line

import urllib.request
import requests
from collections import defaultdict
import re
import lxml
from bs4 import BeautifulSoup

url = 'https://courseworks.columbia.edu/access/content/group/'
url += 'COMSW3101_002_2015_3/data/hamlet.html'

def hamlet(link):
    
    data = urllib.request.urlopen(link) # it's a file like object and works just like a file
    #for line in data: # files are iterable
        #print (type(line))
    count = 0
    sp = 0
    
    returnMe = []
    default = defaultdict(int)
    stringList = []
    
    for line in data.readlines():
        decoded = line.decode("utf-8")
        
        if '=speech' in decoded:
            
            end = "</b></a>"
            spliced = decoded[19: (len(decoded) - 9)]
            step1 = spliced.replace("b", "")
            step2 = step1.replace(">", "")
            stringList.append(step2)
                       
            sp += 1
            
        count += 1
        
    for i in stringList:
        default[i] += 1
        
    returnMe.append(count)
    returnMe.append(sp)
    returnMe.append(default)
    
    return returnMe

hamlet(url)

# [8881,
#  1150,
#  defaultdict(int,
#              {'All': 4,
#               'BERNARDO': 23,
#               'CORNELIUS': 1,
#               'Captain': 7,
#               'Danes': 3,
#               'FRANCISCO': 8,
#               'First Amassador': 1,
#               'First Clown': 33,
#               'First Player': 8,
#               'First Priest': 2,
#               'First Sailor': 2,
#               'GUILDENSTERN': 33,
#               'Gentleman': 3,
#               'Ghost': 14,
#               'HAMLET': 359,
#               'HORATIO': 112,
#               'KING CLAUDIUS': 102,
#               'LAERTES': 62,
#               'LORD POLONIUS': 86,
#               'LUCIANUS': 1,
#               'Lord': 3,
#               'MARCELLUS': 36,
#               'Messenger': 2,
#               'OPHELIA': 58,
#               'OSRIC': 25,
#               'PRINCE FORTINBRAS': 6,
#               'Player King': 4,
#               'Player Queen': 5,
#               'Prologue': 1,
#               'QUEEN GERTRUDE': 69,
#               'REYNALDO': 13,
#               'ROSENCRANTZ': 49,
#               'Second Clown': 12,
#               'Servant': 1,
#               'VOLTIMAND': 2})]

hamlet(url)
# 2645

# - in class, we discussed two different ways to represent a polynomial
#     - polylist, a 'dense' represenation, that hold the coefficients in a list
#     - polydict, a 'sparse' representation, that holds (exponent, coefficent) pairs in a dict
# - add a method, 'topolydict()' to class 'polylist', that converts the polylist into a polydict
# - add a method, 'topolylist()' to class 'polydict', that converts the polydict into a polylist
# - add a method, ```__mul__``` to class 'polydict', which implements the multiply '*' operator
#     - see examples below
# - note that polylist->polydict will always work, but polydict->polylist can fail, because a polylist cannot represent negative exponents. in this case, raise a ValueError
# - just to tell them apart, polylist prints with a leading '+'
import functools

class polylist: 
    ''' 
    list poly representation
    coe[n] is the coefficient of the X**n term
        
    [1,2,3] <=> + 3*X**2 + 2*X + 1
    '''
    def __init__(self, coe):
        self.coe = coe

    def termString(self, c , e):
        cs = str(c)
        if c > 0:
            cs = '+ ' + cs
        if (e == 0):
            return(cs)
        if (e == 1):
            return('%s*X' % cs)    
        return('%s*X**%d' % (cs,e))
        
    def __str__(self):
        # print math style
        if self.coe == []:
            return('0')
        terms = [self.termString(c,e) 
            for e,c in enumerate(self.coe) 
            if c != 0]
        terms.reverse()
        s = (' '.join(terms))
        # get rid of leading + 
        return(s)
        
    def __repr__(self):
        return(self.__str__())

    def __len__(self):
        # number of non zero terms
        # 0 len => bool false
        return(len(self.coe) - self.coe.count(0))

    def __add__(self, p2):
        p1len = len(self.coe)
        p2len = len(p2.coe)
        pad = p2len - p1len
        c1 = self.coe
        c2 = p2.coe
        
        if pad < 0:
            c1, c2 = c2, c1
            pad = -pad
    
        c1 = c1[:]
        
        c1.extend([0]*pad)
    
        return(polylist([t1+t2 for t1,t2 in zip(c1,c2)]))
    
    # don't allow a hash
    __hash__ = None    
    
    def evaluate(self, n):
        sum = 0
        for e,c in enumerate(self.coe):
            sum += c*n**e
        return(sum)

    def differentiate(self):
        return(polylist([e*c for e,c in enumerate(self.coe)][1:]))
    
    def integrate(self):
        pi = [c/(e+1.) for e,c in enumerate(self.coe)]
        pi.insert(0,0)
        return(polylist(pi))
    
    def __mul__(self, p2):
        sums = []
        for e1,c1 in enumerate(self.coe):
            prod = [c1 * c2 for c2 in p2.coe]
            for rpt in range(e1):
                prod.insert(0, 0)
            sums.append(polylist(prod))
        return(functools.reduce(polylist.__add__, sums))
    
    def topolydict(self):
        
        co = self.coe #[0, 10, 5]
        d = {}
        lis = list(range(1000))

        for i in co:
            if i == 0:
                continue
                
            else:
                ind = self.coe.index(i)
                d[lis[ind]] = i
                
        pd = polydict(d)
        
        return(pd)
        
class polydict:
    '''sparse poly representation using a dict
        sparse is {exponent:coefficient, ...}
        only non-zero terms appear in the dict
        
        {2:3, 1:2, 0:1} <=> 3*X**2 + 2*X + 1
    '''
    def __init__(self, d={}):

        # why the copy??
        self.sparse = d.copy()

    def printTerm(self, c ,e):
        cs = str(c)
        if c > 0:
            cs = '+ ' + cs
        if (e == 0):
            return(cs)
        if (e == 1):
            return('%s*X' % cs)    
        return('%s*X**%d' % (cs,e))   
        
    def __str__(self):
        if len(self.sparse) == 0:
            return('0')
        terms = [self.printTerm(self.sparse[e],e) 
                for e in sorted(self.sparse.keys(), 
                                reverse=True) 
                    if self.sparse[e] != 0]
        s = ' '.join(terms)
        if '+ ' == s[0:2]:
            s = s[2:]
        return (s)
    
    def __repr__(self):
        return(self.__str__())

    # don't allow a hash
    __hash__ = None  
    
    def __len__(self):
        return(len(self.sparse))

    # can explicity define bool
    def __bool__(self):
        return(False if len(self.sparse)==0 else True)
        
    def __iter__(self):
        # return a generator function that will
        # iterate thru (exp, coe) pairs
        return( (i for i in self.sparse.items() ))

    # should check types
    def __eq__(self, other):
        return(self.sparse == other.sparse)
        
    def __ne__(self, other):
        return(self.sparse != other.sparse)
        
    # define comparsion to be value of poly at 1
    def __lt__(self, other):
        return(self.evaluate(1) < other.evaluate(1))
        
    def __le__(self, other):
        return(self.evaluate(1) <= other.evaluate(1))
        
    # does poly 'contain' an exponent?
    def __contains__(self, e):
        return(e in self.sparse)
                
    def evaluate(self, n):
        '''eval poly at x=n'''
        sum = 0
        for e in self.sparse.keys():
            sum += self.sparse[e]*n**e
        return(sum)
            
    def __add__(self, p2):
        '''add two polys'''
        n = self.sparse.copy()
        for k,v in p2.sparse.items():
            if None == n.get(k):
                n[k] = v
            else:
                n[k] += v
        return(polydict(n))
        
    def __getitem__(self, index):
        '''pull out terms of the poly
           p[2], p[2:5]
           '''
        keys = sorted(self.sparse.keys(), reverse=True)
        if isinstance(index, int):
            # if asked for a single term, p[n], index will
            # be an int
            inds = [index]
        if isinstance(index, slice):
            # if asked for a slice, p[n:m], index will be
            # a 'slice' object
            inds = range(*index.indices(len(keys)))
        d = {}
        for i in inds:
            e = keys[i]
            d[e] = self.sparse[e]
        return(polydict(d))

    def differentiate(self):
        d = {}
        for e,c in self.sparse.items():
            if e != 0:
                d[e-1] = c * e
        return(polydict(d))
    
    def integrate(self):
        d = {}
        for e,c in self.sparse.items():
            d[e+1] = c /(e+1.)
        return(polydict(d))
    
    def __mul__(self):
        d = {}

    def topolylist(self):
        
        co = self.sparse #dictionary {1:10, 2:5}
        lis = [0] * 100
        
        for key in sorted(co.keys()):
            #key will be index number
            if key < 0:
                raise ValueError("Negative exponent = {}".format(key))
            lis[key] = co[key]
                        
        pl = polylist(lis)
        
        val = list(co.keys())
        last = val[-1]
        
        lis = [x for x in lis if (x != 0 and lis.index(x) > last)]
        
        return(pl)

class polydict:
    '''sparse poly representation using a dict
        sparse is {exponent:coefficient, ...}
        only non-zero terms appear in the dict
        
        {2:3, 1:2, 0:1} <=> 3*X**2 + 2*X + 1
    '''
    def __init__(self, d={}):

        # why the copy??
        self.sparse = d.copy()

    def printTerm(self, c ,e):
        cs = str(c)
        if c > 0:
            cs = '+ ' + cs
        if (e == 0):
            return(cs)
        if (e == 1):
            return('%s*X' % cs)    
        return('%s*X**%d' % (cs,e))   
        
    def __str__(self):
        if len(self.sparse) == 0:
            return('0')
        terms = [self.printTerm(self.sparse[e],e) 
                for e in sorted(self.sparse.keys(), 
                                reverse=True) 
                    if self.sparse[e] != 0]
        s = ' '.join(terms)
        if '+ ' == s[0:2]:
            s = s[2:]
        return (s)
    
    def __repr__(self):
        return(self.__str__())

    # don't allow a hash
    __hash__ = None  
    
    def __len__(self):
        return(len(self.sparse))

    # can explicity define bool
    def __bool__(self):
        return(False if len(self.sparse)==0 else True)
        
    def __iter__(self):
        # return a generator function that will
        # iterate thru (exp, coe) pairs
        return( (i for i in self.sparse.items() ))

    # should check types
    def __eq__(self, other):
        return(self.sparse == other.sparse)
        
    def __ne__(self, other):
        return(self.sparse != other.sparse)
        
    # define comparsion to be value of poly at 1
    def __lt__(self, other):
        return(self.evaluate(1) < other.evaluate(1))
        
    def __le__(self, other):
        return(self.evaluate(1) <= other.evaluate(1))
        
    # does poly 'contain' an exponent?
    def __contains__(self, e):
        return(e in self.sparse)
                
    def evaluate(self, n):
        '''eval poly at x=n'''
        sum = 0
        for e in self.sparse.keys():
            sum += self.sparse[e]*n**e
        return(sum)
            
    def __add__(self, p2):
        '''add two polys'''
        n = self.sparse.copy()
        for k,v in p2.sparse.items():
            if None == n.get(k):
                n[k] = v
            else:
                n[k] += v
        return(polydict(n))
        
    def __getitem__(self, index):
        '''pull out terms of the poly
           p[2], p[2:5]
           '''
        keys = sorted(self.sparse.keys(), reverse=True)
        if isinstance(index, int):
            # if asked for a single term, p[n], index will
            # be an int
            inds = [index]
        if isinstance(index, slice):
            # if asked for a slice, p[n:m], index will be
            # a 'slice' object
            inds = range(*index.indices(len(keys)))
        d = {}
        for i in inds:
            e = keys[i]
            d[e] = self.sparse[e]
        return(polydict(d))

    def differentiate(self):
        d = {}
        for e,c in self.sparse.items():
            if e != 0:
                d[e-1] = c * e
        return(polydict(d))
    
    def integrate(self):
        d = {}
        for e,c in self.sparse.items():
            d[e+1] = c /(e+1.)
        return(polydict(d))
    
    def __mul__(self):
        d = {}

    def topolylist(self):
        
        co = self.sparse #dictionary {1:10, 2:5}
        lis = [0] * 100
        
        for key in sorted(co.keys()):
            #key will be index number
            if key < 0:
                raise ValueError("Negative exponent = {}".format(key))
            lis[key] = co[key]
                        
        pl = polylist(lis)
        
        val = list(co.keys())
        last = val[-1]
        
        lis = [x for x in lis if (x != 0 and lis.index(x) > last)]
        
        return(pl)

# test polys

pl1 = polylist([1, 2, 3])
pl2 = polylist([0, 10, 5])
pd1 = polydict({2:3, 1:2, 0:1})
pd2 = polydict({1:10, 2:5})
pd3 = polydict({-1:10, 2:5})

[pl1, pl2, pd1, pd2, pd3]
# [+ 3*X**2 + 2*X + 1,
#  + 5*X**2 + 10*X,
#  3*X**2 + 2*X + 1,
#  5*X**2 + 10*X,
#  5*X**2 + 10*X**-1]

[pl1.topolydict(), pl2.topolydict(), pd1.topolylist(), pd2.topolylist()]
# [3*X**2 + 2*X + 1, 5*X**2 + 10*X, + 3*X**2 + 2*X + 1, + 5*X**2 + 10*X]

# multiply method examples
[pd1, pd2, pd3, pd1 * pd2, pd1 * pd3, pd2 * pd3]
# [3*X**2 + 2*X + 1,
#  5*X**2 + 10*X,
#  5*X**2 + 10*X**-1,
#  15*X**4 + 40*X**3 + 25*X**2 + 10*X,
#  15*X**4 + 10*X**3 + 5*X**2 + 30*X + 20 + 10*X**-1,
#  25*X**4 + 50*X**3 + 50*X + 100]

