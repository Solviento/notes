# Logarithmic time complexities and their role in computer science

- Merge sort, quick sort, binary search use logarithmic time complexities
- What does it mean to be log?

What is a log?
```
(base 2)  log(8) --> 2^? = 8
```
- What do I power 2 by in order to get 8? It would be 3
- A more graphical approach is the division of 8 until we reach 1
  - 8 -> 4 -> 2 -> 1
  - Notice how we divided 8 a total of 3 times
- Hence why we consider base 2 as dividing by half
- IN COMPUTER SCIENCE, WE USE BASE 2
```
(base 10) log(100) --> 10^? = 100
```
- What do I power 10 by in order to get 100? It would be 2
- More graphical approach:
  - 100 -> 10 -> 1
  - Notice we divided 100 a total of 2 times
- Base 10 is considered as dividing by 10

In relation to binary trees:
```
      o
     / \
    o   o
   / \  /\
  o   o o o
 / \
o   o
```
- In this binary tree we have 9 nodes (n=9)
- To calculate the number of levels we use:
  - levels = 1 + floor(log(n))
  - This gets evaluated to:
    - levels = 1 + floor(log(9))
    - levels = 1 + ~3.1
    - levels = 4
- This is assuming we have a balanced structure, any skew will result in linear time to calculate

Array division by half
```
log(8) = 3 (always using base 2)
n length = 8
            [1, 3, 2, 5, 7, 8, 2, 9]
                  |      |
        [1, 3, 2, 5]     [7, 8, 2, 9]
        |     |             |      |
      [1, 3] [2, 5]       [7, 8]  [2, 9]
      |   |   |   |        |   |   |   |
    [1]  [3] [2] [5]      [7] [8]  [2] [9]
```
- Notice how we were able to divide the array into halves until we had only one element arrays
- There are three total generated levels to this process
  - This is where the logarithm comes into play
- So when we talk about merge sort and quick sort, we run in nlog(n)
  - In the recursion for merge sort, we're going to be dividing the arrays by half by log(n) levels
  - For each level we do about n work
  - Hence why merge sort is n*log(n)

