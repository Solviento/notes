# Python Cheat Sheet with Code Context
## Sys, OS, String, List, Datetime, Time, Formatting, Flle, Special Methods + Indexes/Slices

## System (sys) Variables
- argv
  - What is sys.argv?
  - argv is a list in python which contains the command line arguments passed to the script
  - When working with command line arguments, you'll need to work with sys.argv

``` Python
import sys
print("Name of script: ", sys.argv[0])
print("Number of args: ", len(sys.argv))
```

- builtin_module_names
  - A tuple of strings giving the names of all modules compiled into this python interpreter

``` Python
import sys
print("Module name: ", sys.builtin_module_names)
```

- byteorder
- check_interval
- exec_prefix
- executable
- exitfunc
- modules
- path
- platform
- stdin, stdout, stderr
- version_info
- winver

## Operating System (OS) Variables
- altsep
- curdir
- defpath
- devnull
- extsep
- linesep
- name
- pardir
- pathsep
- sep

## Class Special Methods
- __new__(cls)
- __init__(self, args)
- __del__(self)
- __repr__(self)
- __str__(self)
- __cmp__(self, other)
- __index__(self)
- __hash__(self)
- __getattr__(self, name)
- __getattribute__(self, name)
- __setattr__(self, name, attr)
- __delattr__(self, name)
- __call__(self, args, kwargs)

## String Methods
- capitalize()
- center(width)
- count(sub, start, end)
- decode()
- encode()
- endswith(sub)
- expandtabs()
- find(sub, start, end)
- index(sub, start, end)
- isalnum()
- isalpha()
- isdigit()
- islower()
- isspace()
- istitle()
- join()
- ljust(width)
- lower()
- lstrip()
- partition(sep)
- replace(old, new)
- rfind(sub, start, end)
- rindex(sub, start, end)
- rjust(width)
- rpartition(sep)
- rsplit(sep)
- rstrip()
- split(sep)
- splitlines()
- startswith(sub)
- strip()
- swapcase()
- title()
- translate(table)
- upper()
- zfill(width)

## List Methods
- append(item)
- count(item)
- extend(list)
- index(item)
- insert(position, item)
- pop(position)
- remove(item)
- reverse()
- sort()

## File Methods
- close()
- flush()
- fileno()
- isatty()
- next()
- read(size)
- readline(size)
- readlines(size)
- seek(offset)
- tell()
- truncate(size)
- write(string)
- writelines(list)

## Indexes and Slices

``` Python
a = [0, 1, 2, 3, 4, 5]

a[-1] # 5

a[-2] # 4

a[1:] # [1, 2, 3, 4, 5]

a[:5] # [0, 1, 2, 3, 4]

a[:-2] # [0, 1, 2, 3] THINK SLICING OFF AT LAST TWO ELEMENTS

a[1:3] # [1, 2]

a[1:-1] # [1, 2, 3, 4] EVALUATE THEM SEPARATELY TO UNDERSTAND THE RESULTING SLICE

b = a[:] # shallow copy of a
```

## Datetime Methods
- today()
- now(timezoneinfo)
- utcnow()
- fromtimestamp(timestamp)
- utcfromtimestamp(timestamp)
- fromordinal(ordinal)
- combine(date, time)
- strptime(date, format)

## Time Methods
- replace()
- isoformat()
- __str__()
- strftime(format)
- utcoffset()
- dst()
- tzname()

## Date Formatting
``` Python
%a
%A
%b
%B
%c
%d
%H
%I
%j
%m
%M
%p
%S
%U
%w
%W
%x
%X
%y
%Y
%Z
%%
```
