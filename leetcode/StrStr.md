# How preprocessing string works

pattern: abcdabca

Initialization of pointers and lps array
i = 1
j = 0
int[] lps = new lps[pattern.length]
lps[0] = 0

First iteration
j i             (pointers)
a b c d a b c a (pattern string)
0 1 2 3 4 5 6 7 (pattern indices)

0 ' ' ' ' ' ' ' (lps array, first item is initialized to 0)

- Here j is set to the begining of the pattern string 
- while i is set to the second char of the pattern string
- the lps array will store the length of the longest prefix suffix
- since pattern[i] != pattern[j]
- Keep in mind two cases
  - if j != 0, meaning length of pre-suffix is there
    - then make j = lps[j-1], aka the element to the left of lps[j]
  - if j == 0, meaning length of prefix-suffix is zero
    - then make lps[i] = j
    - then increment i
- Since j = 0, we go with second case

lps[i=1] = j = 0
i = 1+1=2

Second iteration
j   i           
a b c d a b c a 
0 1 2 3 4 5 6 7 

0 0 ' ' ' ' ' ' 
- Here we do same as first iteration since pattern[i] != pattern[j] and j == 0

lps[i=2] = j = 0
i = 2+1 = 3

Third iteration
j     i           
a b c d a b c a 
0 1 2 3 4 5 6 7 

0 0 0 ' ' ' ' ' 

- Same as before since pattern [i] != pattern[j]

lps[i=3] = j = 0
i = 3+1= 4

Fourth iteration
j       i         
a b c d a b c a 
0 1 2 3 4 5 6 7 

0 0 0 0 ' ' ' ' 
- Now! pattern[i] == pattern[j]
- First increment j, aka the length of pre-suffix
- Then set lps[i] = j
- Increment i, aka the pattern pointer
  
j = 0+1 = 0
lps[i=4] = j = 1
i = 4+1 = 5

Fifth iteration
  j       i         
a b c d a b c a 
0 1 2 3 4 5 6 7 

0 0 0 0 1 ' ' ' 
- pattern[i] == pattern[j]
- increment j, then set lps[i] = j, increment i

j = 1+1 = 2
lps[i=5] = j = 2
i = 5+1 = 6

Sixth iteration
    j       i         
a b c d a b c a 
0 1 2 3 4 5 6 7 

0 0 0 0 1 1 ' ' 
- pattern[i] == pattern[j]
- increment j, set lps[i] = j, increment i

j = 2+1 = 3
lps[i=6] = j = 3
i = 6+1 = 7

Seventh iteration
      j       i
a b c d a b c a 
0 1 2 3 4 5 6 7 

0 0 0 0 1 2 3 '
- Now pattern[i] != pattern[j]
- Keep in mind two cases
  - if j != 0, meaning length of prefix-suffix is zero
    - then make j = lps[j-1], aka the element to the left of lps[j]
  - if j == 0, meaning length of pre-suffix is there
    - then make lps[i] = j
    - then increment i
  - since j = 3, we do the first case

j = lps[j-1] = lps[3-1] = lps[2] = 0

Eigth iteration
j             i
a b c d a b c a [pattern]
0 1 2 3 4 5 6 7 [index]

0 0 0 0 1 2 3 ' [lps]
- pattern[i] == pattern[j]
- increment j, lps[i] = j, increment i

j = j+1 = 0+1 = 1
lps[i=7] = j = 1
i = i+1 = 7+1 = 8

Since i is now greater than length of pattern string, we stop
lps[] result: 0 0 0 0 1 2 3 1