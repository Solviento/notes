# Idea behind binary search and square root

Take int x = 8, square root of 8 is approximately 2.8, for SquareRoot() we take only integer part which is 2

From 1 to 8

1*1 = 1
2*2 = 4
3*3 = 9
4*4 = 16
5*5 = 25
6*6 = 36
7*7 = 49
8*8 = 64

Notice that out of all these values, the upper left half of the squared values contain anywhere close to the target int value of 8
Therefore we use binary search to find square root of x

Using 4 as mid, we determine which half to search
Since 4*4 > x, we traverse the first half
IFF mid*mid < x, we would then traverse second half
We now perform binary search on first half:

1*1 = 1
2*2 = 4
3*3 = 9
4*4 = 16

Mid is now 2, since 2*2 < x we traverse the second half
Also add the new mid value to the return value
Return value is now 2

3*3 = 9
4*4 = 16

Mid is now 3, since 3*3 > x we traverse the first half, however at this point we end the binary search
Return the return value, 2
Notice that when mid*mid < x, we add up the mid value to the return value