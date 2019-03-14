# hw4

# - def walkfs(startdir, findfile):
# - 'startdir' is an absolute pathname(starts with a '/', like '/foo/bar/zap.txt')
# - 'findfile' is a relative pathname(no '/', like 'name.ext')
# - walkfs recursively searches the file system, starting at 'startdir'
# - it counts the number of dirs and files it sees
# - it looks for 'findfile' - if it finds it, it stores its absolute pathname
# - it returns what it finds in a dict
# - walkfs can throw errors
#     - if 'startdir' does not exist, raise a FileNotFoundError
#     - if 'startdir' is a file, not a directory, raise a NotADirectoryError
# - your loop should be:
#     - for curdir,dirs,files in os.walk(startdir):
#     - curdir will be an absolute pathname of a directory
#     - dirs and files are lists of relative paths - the dirs and files in curdir
# - see 07-files, 11-errors notebooks

import os 
import sys

def walkfs(startdir, findfile):
    
    if not os.path.exists(startdir): 
        raise FileNotFoundError
        return
    
    if not os.path.isdir(startdir):
        raise NotADirectoryError
        return
        
    if os.path.isdir(startdir):
        
        dircount = 0
        filecount = 0
        
        dictionary = {}
        
        dictionary['findfile'] = findfile
        dictionary['stardir'] = startdir
        
        for curDir, dirs, files in os.walk(startdir):
            
            for file in files:
                
                filecount += len(files)
                dircount += len(dirs)
                
                if file.endswith(findfile):
                    dictionary['findfilepath'] = os.path.join(curDir, file)
                
        dictionary['dircnt'] = dircount
        dictionary['filecnt'] = filecount
           
    return(dictionary)

# the sample runs will use this filetree here are the directories and files under:

# /Users/lstead/me/classes/columbia/python/code/filetree/

# there are 7 directories and 4 files:

# /Users/lstead/me/classes/columbia/python/code/filetree 
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/anotherfile.txt
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/.DS_Store
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/dir111
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/dir111/dir112
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/dir111/dir112/.DS_Store
# /Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/dir111/dir112/findtest.txt
# /Users/lstead/me/classes/columbia/python/code/filetree/dir2
# /Users/lstead/me/classes/columbia/python/code/filetree/dir2/dir21

walkfs('\\Users\\Jason\\Anaconda3', 'bye.txt')
# {'dircnt': 113530,
#  'filecnt': 4367310,
#  'findfile': 'bye.txt',
#  'findfilepath': '\\Users\\Jason\\Anaconda3\\info\\bye.txt',
#  'stardir': '\\Users\\Jason\\Anaconda3'}

walkfs('\\Users\\Jason\\Anaconda3', 'fake.txt')
# {'dircnt': 113530,
#  'filecnt': 4367310,
#  'findfile': 'fake.txt',
#  'stardir': '\\Users\\Jason\\Anaconda3'}

walkfs('/Users/lstead/me/classes/columbia/python/code/filetree', 
       'somefile.txt')

# defaultdict(int,
#             {'dircnt': 7,
#              'filecnt': 4,
#              'findfile': 'somefile.txt',
#              'startdir': '/Users/lstead/me/classes/columbia/python/code/filetree'})

# startdir is ok, 'findtest.txt' does exist, 
# so the value of key 'findfilepath' 
# is set to the absolute path of 'findtest.txt'

walkfs('/Users/lstead/me/classes/columbia/python/code/filetree', 
       'findtest.txt')
# defaultdict(int,
#             {'dircnt': 7,
#              'filecnt': 4,
#              'findfile': 'findtest.txt',
#              'findfilepath': '/Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/dir111/dir112/findtest.txt',
#              'startdir': '/Users/lstead/me/classes/columbia/python/code/filetree'})

# - def report(startdir, findfile, reportpath):
# - report calls walkfs, and writes the information found by walkfs into file reportpath
# - additionally, if findfile is found, the contents of the file are printed at the end of the report
# - report should NOT have any 'try' clauses
#     - if walkfs raises an error, report will let it go to the caller of report
# - use 'with' block for file IO

#Report.txt is written in the filepath
# \\tmp when run

def report(startdir, findfile, reportpath):
    
    diction = walkfs(startdir, findfile)
    
    dcount = 0
    fcount = 0
    
    for key, value in diction.items():
        
        dcount = diction.get('dircnt')
        fcount = diction.get('filecnt')
            
        if key == 'findfilepath':
            
            fpath = diction.get(key)
            
            with open(reportpath, 'w') as report:
                
                report.write("recursively searched from startdir:\n%s\nfound %d dirs \nfound %d files \n\nfound file: \n%s \nfilecontents: \n\n" % (startdir, dcount, fcount, fpath))
                
            foundpath = diction.get('findfilepath')
                
            with open(foundpath, "r") as p:
                    with open(reportpath, "a") as f:
                        for line in p:
                            f.write(line)
                
            break
        else:
            with open(reportpath, 'w') as report:
                
                report.write("recursively searched from startdir:\n%s\nfound %d dirs \nfound %d files \n\ndid not find file: \n%s " % (startdir, dcount, fcount, findfile))
                break
    return(diction)

report('\\Users\\Jason\\Anaconda3', 'bye.txt', '\\tmp\\report.txt')
# {'dircnt': 113530,
#  'filecnt': 4367310,
#  'findfile': 'bye.txt',
#  'findfilepath': '\\Users\\Jason\\Anaconda3\\info\\bye.txt',
#  'stardir': '\\Users\\Jason\\Anaconda3'}

report('\\Users\\Jason\\Anaconda3', 'notReal.txt', '\\tmp\\report.txt')
# {'dircnt': 113530,
#  'filecnt': 4367310,
#  'findfile': 'notReal.txt',
#  'stardir': '\\Users\\Jason\\Anaconda3'}

report('/Users/lstead/me/classes/columbia/python/code/filetree', 
       'NoSuch.txt', '/tmp/report.txt')

# defaultdict(int,
#             {'dircnt': 7,
#              'filecnt': 4,
#              'findfile': 'NoSuch.txt',
#              'startdir': '/Users/lstead/me/classes/columbia/python/code/filetree'})
            
# writes '/tmp/report.txt' file...

report('/Users/lstead/me/classes/columbia/python/code/filetree', 
       'findtest.txt', '/tmp/report.txt')
# defaultdict(int,
#             {'dircnt': 7,
#              'filecnt': 4,
#              'findfile': 'findtest.txt',
#              'findfilepath': '/Users/lstead/me/classes/columbia/python/code/filetree/dir1/dir11/dir111/dir112/findtest.txt',
#              'startdir': '/Users/lstead/me/classes/columbia/python/code/filetree'})

# - place walkfs and report in a script file, report.py
# - you must place a try block around the call to report,
# and catch FileNotFoundError and NotADirectoryError
#     - if walkfs raises FileNotFoundError, the script should call sys.exit(1)
#     - if walkfs raises NotADirectoryError, the script should call sys.exit(2)
#     - if walkfs runs without error, the script should call sys.exit(0)
# - to parse the command line, you can use the argparse module, or 
# since the args are simple, you can interface with sys.argv directly. your choice
# - see 13-sys notebook
# - when writing a non trivial script, it is often easier to develop functions in your favorite environment(spyder, notebook, etc), then
# just call the functions based on what command line args are supplied

# here is the command line invocation:

# python report.py reportpath startdir findpath

# which would be equivalent to this python call(note arg order is different)

# report(startdir, findpath, reportpath)

#!/usr/bin/python

#When run on personal computer, jupyter did not read filepath 
#correctly when calling through the report.py script
#walkfs and report work normally for my PC (windows - 64bit)
#but with the way jupyter and anacadonda seem to be set up
#the relative root filepath changes between
#C:\Users\Jason and C:\User\Jason\Documents\Python
#This technicality made it extrememly difficult for me to debug
#my code, therefore, my script most likely does not work
#as jupyter did not let me run command line arguments as
#instructed in the assignment "python report.py reportpath ..."
#However, the try block is included with both exceptions
#and sys.argv arguments, so there should be some exception
#pulled if filenotfound or notadirectoryerror is raised

#REPORT.PY SCRIPT

import sys
import os

def walkfs(startdir, findfile):
    
    
    if not os.path.exists(startdir):
        raise FileNotFoundError
        return
    
    if not os.path.isdir(startdir):
        raise NotADirectoryError
        return
        
    if os.path.isdir(startdir):
        
        dircount = 0
        filecount = 0
        
        dictionary = {}
        
        dictionary['findfile'] = findfile
        dictionary['stardir'] = startdir
        
        for curDir, dirs, files in os.walk(startdir):
            
            for file in files:
                
                filecount += len(files)
                dircount += len(dirs)
                
                if file.endswith(findfile):
                    dictionary['findfilepath'] = os.path.join(curDir, file)
                
        dictionary['dircnt'] = dircount
        dictionary['filecnt'] = filecount
           
    return(dictionary)
    
def report(startdir, findfile, reportpath):
    
    diction = walkfs(startdir, findfile)
    
    dcount = 0
    fcount = 0
    
    
    while True:
        
        dcount = diction.get('dircnt')
        fcount = diction.get('filecnt')
            
        if 'findfilepath' in diction:
            
            fpath = diction.get('findfilepath')
            
            with open(reportpath, 'w') as report:
                
                report.write("recursively searched from startdir:\n%s\nfound %d dirs \nfound %d files \n\nfound file: \n%s \nfilecontents: \n\n" % (startdir, dcount, fcount, fpath))
                
            foundpath = diction.get('findfilepath')
                
            with open(foundpath, "r") as p:
                    with open(reportpath, "a") as f:
                        for line in p:
                            f.write(line)
                
            break
        
        else:
            with open(reportpath, 'w') as report:
                
                report.write("recursively searched from startdir:\n%s\nfound %d dirs \nfound %d files \n\ndid not find file: \n%s " % (startdir, dcount, fcount, findfile))
                break


def mainTEST():
    
    if len(sys.argv) > 1:
        
        try:
            report(sys.argv[1], sys.argv[2], sys.argv[3])
              
        except FileNotFoundError:
            sys.exit(1)
    
        except NotADirectoryError:
            sys.exit(2)
    
        else:
            print ("Written content in the file successfully")
    
# - use objects to simulate a vending machine
# - money is in units of cents
# - remember
#     - 'self' must be the first arg to every method
#     - use the 'self.' prefix to refer to instance variables or other methods inside a method 

# class venditem represents a type of item for sale
# has three instance variables
# name, price, quantity
# define four methods
# method __init__ loads data into the instance variables
# def __init__(self, name, price, quantity):
# method __repr__(self)
# controls how venditem prints
# use string format method
# '{} {}'.format(arg, arg2)
# see examples below
# method __str__(self)
# just call __repr__ for string to return
# method sale(self)
# decrement the quantity

class venditem:
    
    def __init__(self, name, price, quantity):
        
        self.name = name
        self.price = price
        self.quantity = quantity
        
    def __repr__(self):
        
        return("venditem(name=%s, price=%d, quantity=%d)" %(self.name, self.price, self.quantity))

        
    def sale(self):
        self.quantity -= 1
        
    def __str__(self):        
        return self.__repr__()

vi = venditem('coke', 95, 3)
vi2 = venditem('pepsi', 110, 1)

[vi, vi2]

# [venditem(name=coke, price=95, quantity=3),
#  venditem(name=pepsi, price=110, quantity=1)]
vi.sale()
vi
# venditem(name=coke, price=95, quantity=2)

[vi.name, vi2.name, vi.price, vi.quantity, vi2.quantity]
# ['coke', 'pepsi', 95, 2, 1]

vi.quantity = 2
vi.quantity
# 2

# - vendmachine has two instance variables
#     - 'cash' - the amount of money the machine has collected from item sales
#     - 'items' - a dictionary, where keys are the name of an item, and the values are the venditem object
# - define three methods
#     - `__init__`(self, stock)
#         - stock arg is a list of venditems, which represents what is loaded in the machine
#         - items dictionary should be constructed from stock
#         - cash should be initialized to 0
#     - buy(self, name, money) 
#         - 'name' is 'coke', 'pepsi', etc
#         - money is how much money the customer deposited for the purchase
#         - four cases
#             - customer asks for an item not carried
#             - customer asks for an item whose quantity is 0 - out of stock
#             - customer doesn't put in enough money for the item
#             - everything ok, sell the item, decrement item quantity
#         - 'buy' return value should refund any money owed the customer 
#             - money not applied to an item sale
#             - excess money deposited for an item sale
#         - log each buy case, using 'log' function below
#         - see examples below
#     - status(self)
#         - prints the amount of cash collected, and each of the items in stock


import time

class vendmachine:
    
    items = {}
    cash = 0
    
    def __init__(self, stock):
        
        self.stock = stock
        
        for vend in self.stock:
            vendmachine.items[vend.name] = vend
        
    def buy(self, name, money):
        
        #items = {'someitem': someVendItem}
        if not name in vendmachine.items:
            t = time.strftime('%X %x %Z - ')
            msg = t + ': ' + 'not carried: ' + name
            print(msg)
            
            return(money)
        
        elif vendmachine.items[name].quantity == 0:
            t = time.strftime('%X %x %Z - ')
            msg = t + ': ' + 'out of stock ' + name
            print(msg)
            return(money)
        
        elif money < vendmachine.items[name].price:
            
            t = time.strftime('%X %x %Z - ')
            msg = t + ': ' + 'insufficient funds for: ' + name
            print(msg)
            
            return(money)
        else:
            vendmachine.cash = money
            vendmachine.items[name].sale()
            
            t = time.strftime('%X %x %Z - ')
            msg = t + ': ' + 'sold: ' + name
            print(msg)
            
            change = vendmachine.cash - vendmachine.items[name].price
            return(change)
        
        
    def status(self):
        print ("cash collected: %d" %vendmachine.cash)
        for it in self.stock:
            print(it)

# could log to a file by doing file append
# with open(logpath, 'a') as fd
#    write(msg + '\n')

import time
    
def log(msg, name):
    t = time.strftime('%X %x %Z - ')
    msg = t + msg + ': ' + name
    print(msg)

vi = venditem('coke', 95, 3)
vi2 = venditem('pepsi', 110, 1)
vi3 = venditem('peanut M&Ms', 100, 2)
stock = [vi, vi2, vi3]

vm = vendmachine(stock)
vm.status()

# cash collected: 0
# venditem(name=coke, price=95, quantity=3)
# venditem(name=pepsi, price=110, quantity=1)
# venditem(name=peanut M&Ms, price=100, quantity=2)

vm.buy('coke', 45)
# 12:13:05 10/07/16 Eastern Daylight Time - : insufficient funds for: coke
# 45

vm.buy('pepsi', 200)
# 12:13:05 10/07/16 Eastern Daylight Time - : sold: pepsi
# 90

vm.status()
# cash collected: 200
# venditem(name=coke, price=95, quantity=3)
# venditem(name=pepsi, price=110, quantity=0)
# venditem(name=peanut M&Ms, price=100, quantity=2)

vm.buy('mtndew', 200)
# 12:13:06 10/07/16 Eastern Daylight Time - : not carried: mtndew
# 200

vm.buy('coke', 100)
# 12:13:07 10/07/16 Eastern Daylight Time - : sold: coke
# 5

vm.status()
# cash collected: 100
# venditem(name=coke, price=95, quantity=2)
# venditem(name=pepsi, price=110, quantity=0)
# venditem(name=peanut M&Ms, price=100, quantity=2)



