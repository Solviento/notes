# sorting-algorithms
**Insertion sort**
Insertion sort compares and swaps elements by iteration. We start by selecting the first element, append it to the sorted subarray and then compare the next available unsorted element with all preceding sorted elements.


Best case: O(n) ie. a presorted list
Average/Worst case: O(n^2)


An unsorted array:

```java
23 42 4 16 8 15
```
To begin, the leftmost number is considered sorted

```java
sorted    unsorted
23 | 42 4 16 8 15
```
Next the leftmost number of the unsorted pile is compared to the already sorted number to its left
compare 42 and 23
23 < 42 
If the already sorted number is larger we swap the two numbers 
This repeats until a smaller number appears or the number reaches the left edge
Since 23 is less than 42, we insert 42 to the right

```java
sorted    unsorted
23 42 | 4 16 8 15
```
compare 4 and 42
4 < 42 so we insert it and shift 42 to the right
compare 4 and 23
4 < 23 so we insert it and shift 23 to the right

```java
sorted    unsorted
4 23 42 | 16 8 15
```
compare 16 and 42 ... 16 < 42
compare 16 and 23 ... 16 < 23
compare 16 and 4
16 > 4 so we insert it and shift 23 to the right

```java
sorted    unsorted
4 16 23 42 | 8 15
```
compare 8 and 42 ...
compare 8 and 23 ...
compare 8 and 16 ...
compare 8 and 4
8 > 4 so we insert it and shift 23 to the right

```java
sorted        unsorted
4 8 16 23 42 | 15
```
compare 15 and 42 ...
compare 15 and 23 ...
compare 15 and 16 ...
compare 15 and 8
15 > 8, insert and shift 16 to the right

```java
sorted
4 8 15 16 23 42
```
Insertion sort pseudo code:

```java
insertionSort(Array a):
    for i in 0 -> (a.length - 2):
       tmp = a[i]
       j = i+1
        while (j > 0 and a[j-1] < tmp)
                array[j] = array[j-1]
                j = j-1 // or j--;
       a[j] = tmp
```


**Quick Sort**
Worst case: O(n^2)
Average case: O(n logn)
Unsorted array:

```java
3 5 8 1 2 9 4 7 6
```
Create markers on leftmost and rightmost element

```java
3 5 8 1 2 9 4 7 6
L             R P
```
Keep moving left marker until L reaches a number greater than or equal to the pivot number and then stop

```java
3 5 8 1 2 9 4 7 6
    L         R P
```
Now move right marker until R reaches a number less than the pivot number and then stop

```java
3 5 8 1 2 9 4 7 6
    L       R   P
```
Now that both markers are stopped
Swap the numbers in L and R

```java
3 5 4 1 2 9 8 7 6
    L       R   P
```
Keep moving left marker until L ...

```java
3 5 4 1 2 9 8 7 6
          L R   P
```
Keep moving right marker until R ...

```java
3 5 4 1 2 9  8 7 6
          LR     P
```
*** Right marker stops moving when it runs into left marker
When both markers stop and in the same position, swap the number with the pivot number

```java
3 5 4 1 2 6  8 7 9
          LR     P
```
Numbers to the leftand right of LR are considered sorted
Now we recursively do the same procedure to the subarrays to the left and right of LR

```java
3 5 4 1 2 || 6  || 8 7 9
L     R P       
```
Keep moving L until it reaches a number greater or equal to P
Keep moving R until it reaches a number less than P

```java
3 5 4 1 2 || 6  || 8 7 9
L     R P   
```
Swap L and R elements

```java
1 5 4 3 2 || 6  || 8 7 9
L     R P  
```
Keep moving L until it ...

```java
1 5 4 3 2 || 6  || 8 7 9
  L   R P  
```
Keep moving R until it ...
Stop R when it reaches the same position as L

```java
1 5  4 3 2 || 6  || 8 7 9
  LR     P  
```
Swap LR and P elements

```java
1 2  4 3 5 || 6  || 8 7 9
  LR     P 
```
Recursively quicksort to left and right subarrays of LR

```java
1 || 2 || 4 3 5 || 6  || 8 7 9
P
```
When subarray contains only one number it is considered fully sorted

```java
|| 1 || 2 || 4 3 5 || 6  || 8 7 9
             L R P
```
Keep moving L until it reaches ..
** Left marker L may move past R

```java
|| 1 || 2 || 4 3 5 || 6  || 8 7 9
               R LP
```
When L reaches right most edge of subarray, it stops
This means pivot number is the largest number in the subarray

```java
|| 1 || 2 || 4 3 5 || 6  || 8 7 9
               R LP
```
Keep moving R until it reaches...
Since L has moved past R, R finishes without moving
Since L has reached P, we consider it fully sorted
Recursively quicksort to left subarray of LP

```java
|| 1 || 2 || 4  3 || 5 || 6  || 8 7 9
             LR P 
```
Keep moving L until it ...
Keep moving R until it ...

```java
|| 1 || 2 || 4  3 || 5 || 6  || 8 7 9
             LR P 
```
Both markers are stopped
Swap LR and P elements

```java
|| 1 || 2 || 3  4 || 5 || 6 || 8 7 9
             LR P 
```
The swapped element is considered fully sorted
Now quicksort subarray left and right of the swapped element
Note that left subarray is fully sorted

```java
|| 1 || 2 || 3 || 4 || 5 || 6 || 8 7 9
                  P               
```
Since subarray contains only 1 element, it is considered fully sorted

```java
|| 1 || 2 || 3 || 4 || 5 || 6 || 8 7 9
                                 L R P
```
Recursively quicksort again ...


**Merge sort**
Divide and conquer algorithm.


Best/Average/Worst case: O((nlogn)


An unsorted array:

```java
9 2 6 5 3 10 1 7
```
separate all elements into subarrays of 1 element each

```java
9 | 2 | 6 | 5 | 3 | 10 | 1 | 7
```
then compare each sequential subarray pair and merge accordingly

```java
9 | 2 ...
```
2 < 9, shift 9 and merge

```java
2 9 | 6 | 5 | 3 | 10 | 1 | 7
```
next subarray pair

```java
... 6 | 5 ...
```
5 < 6, shift 6 and merge

```java
2 9 | 5 6 | 3 | 10 | 1 | 7
```
next subarray pair

```java
... 3 | 10 ...
```
10 > 3, merge

```java
2 9 | 5 6 | 3 10 | 1 | 7
```
...

```java
2 9 | 5 6 | 3 10 | 1 7
```
now compare each sequential subarray pairs and merge accordingly

```java
2 9 | 5 6 ...
```
2 < 5, merge 2 to itself

```java
2 | 9 | 5 6 ...
```
9 > 5,merge 5 to the right of 2

```java
2 5 | 9 | 6 ...
```
9 > 6, shift 9 and merge 6 to the right of 5

```java
2 5 6 | 9 ...
```
no more additional elements, merge 9 to the right of 6
move to next pair

```java
... 3 10 | 1 7
```
3 < 1, merge 1 to itself

```java
... 1 | 3 10 | 7
```
7 > 3, merge 3 to the right of 1

```java
... 1 3 | 7 | 10
```
7 < 10, merge 10 to the right of 3

```java
... 1 3 7 | 10
```
no more element to compare, merge 10 to the right of 7

```java
... 1 3 7 10
```
now we compare the subarray pairs and merge accordingly

```java
2 5 6 9 | 1 3 7 10
```
1 < 2, merge 1 to itself

```java
1 | 2 5 6 9 | 3 7 10
```
2 < 3, merge 2 to the right of 1

```java
1 2 | 5 6 9 | 3 7 10
```
3 < 5, merge 3 to the right of 2

```java
1 2 3 | 5 6 9 | 7 10
```
5 < 7, merge 5 to the right of 3

```java
1 2 3 5 | 6 9 | 7 10
```
7 > 6, merge 6 to right of 5

```java
1 2 3 5 6 | 9 | 7 10
```
...

```java
sorted
1 2 3 5 6 7 9 10
```
Pseudocode

```java
mergeSort(array, first, last):
    //sort array[first] to array[last-1]
    if last-first <= 1:
        return // single/non-element or already sorted
    mid = (first+last)/2
    mergeSort(array, first, mid)    // recursively
    mergeSort(array, mid, last)     // loops
    merge(array, first, mid, last)  // merges at sort
```
**Heapsort**
Create a max heap from an array. Use the max heap's properties to sort.


Best/Average/Worst case: O(nlogn)


unsorted array

```java
41 67 34 0 69 24 78 58 62 64 5 45 81 27 61
```
Easier way to heapify an unsorted array

```java
add 41
    41
add 67
    41
|
67
percolate
    67
|
41
add 34
    67
|        |
41       34
add 0
        67
    |       |
    41      34
|
0
add 69
        67
    |       |
    41      34
|    |
0    69
percolate
        67
    |       |
    69      34
|    |
0    41
percolate
        69
    |       |
    67      34
|    |
0    41
add 24
        69
    |            |
    67           34
|    |        |
0    41       24
add 78
        69
    |            |
    67           34
|    |        |    |
0    41       24   78
percolate 
        69
    |            |
    67           78    
|    |        |    |
0    41       24   34
percolate
        78
    |            |
    67           69
|    |        |    |
0    41       24   34
add 58
            78
        |            |
        67           69
    |    |        |    |
    0    41       24   34
  |
58
percolate
              78
        |            |
        67           69
    |    |        |    |
   58    41      24    34
  |
  0
add 62
            78
        |            |
        67           69
    |    |        |    |
   58    41      24    34
  |  |
 0   62
 percolate
             78
        |            |
        67           69
    |    |        |    |
   62    41      24    34
  |  |
 0   58
 add 64
             78
        |            |
        67           69
    |    |        |    |
   62    41      24    34
  |  |   |
 0   58  64
 percolate
              78
        |            |
        67           69
    |    |        |    |
   62    64      24    34
  |  |   |
 0   58  41
 add 5
              78
        |            |
        67           69
    |    |        |    |
   62    64      24    34
  |  |   |  |
 0   58 41  5
 add 45
              78
        |            |
        67           69
    |    |        |    |
   62    64      24    34
  |  |   |  |   |
 0   58 41  5   45
 percolate
               78
        |            |
        67           69
    |    |        |    |
   62    64      45    34
  |  |   |  |   |
 0   58 41  5   24
add 81
               78
        |            |
        67           69
    |    |        |    |
   62    64      45    34
  |  |   |  |   |   |
 0   58 41  5   24  81
percolate
               78
        |            |
        67           69
    |    |        |    |
   62    64      81    34
  |  |   |  |   |   |
 0   58 41  5   24  45
percolate
               78
        |            |
        67           81
    |    |        |    |
   62    64      69    34
  |  |   |  |   |   |
 0   58 41  5   24  45
 percolate
              81
        |            |
        67           79
    |    |        |    |
   62    64      69    34
  |  |   |  |   |   |
 0   58 41  5   24  45
 add 27
              81
        |            |
        67           79
    |    |        |    |
   62    64      69    34
  |  |   |  |   |   |  |
 0   58 41  5   24  45 27
 add 61
              81
        |            |
        67           79
    |    |        |    |
   62    64      69    34
  |  |   |  |   |   |  |  |
 0   58 41  5   24  45 27 61
 percolate
              81
        |            |
        67           79
    |    |        |    |
   62    64      69    61
  |  |   |  |   |   |  |  |
 0   58 41  5   24  45 27 34
= 81 67 78 62 64 69 61 0 58 41 24 45 27 34
done
```
...
The longer, less intuitive way of getting a max heap from unsorted array
preview of max heap array

```java
81 67 78 62 64 69 61 0 58 41 24 45 27 34
...
          81
          |   
    67          78
    |           |
 62    64     69    61
|  |  |  |   |  |  |  |
0 58  41 5  24  45 27 34
```
when told to sort in place, we percolate the elements as needed
start with first element of the array
construct the max heap in place
if max heap condition is violated, percolate elements until condition is met

```java
41 67 34 0 69 24 78 58 62 64 5 45 81 27 61
^ 
```
no percolation

```java
41 67 34 0 69 24 78 58 62 64 5 45 81 27 61
^  ^^ ^^
```
41 < 67, condition violated
percolate

```java
67 41 34 0 69 24 78 58 62 64 5 45 81 27 61
^  ^^ ^^
```
no percolation

```java
67 41 34 0  69 24 78 58 62 64 5 45 81 27 61
   ^     ^^ ^^
```
41 < 69, condition violated
percolate

```java
67 69 34 0 41 24 78 58 62 64 5 45 81 27 61
^  ^^ ^^
```
67 < 69, condition violated
percolate

```java
69 67 34 0  41 24 78 58 62 64 5 45 81 27 61
   ^     ^^ ^^
```
no condition violated, proceed

```java
69 67 34 0 41 24 78 58 62 64 5 45 81 27 61
      ^       ^^ ^^
```
34 < 78, condition violated
percolate

```java
69 67 78 0 41 24 34 58 62 64 5 45 81 27 61
^  ^^ ^^
```
69 < 78, condition violated
percolate

```java
78 67 69 0  41 24 34 58 62 64 5 45 81 27 61
   ^     ^^ ^^
      ^        ^^ ^^
```
no condition volated, proceed

```java
78 67 69 0  41 24 34 58 62 64 5 45 81 27 61
         ^           ^^ ^^
```
0 < 58, condition violated
percolate

```java
78 67 69 58 41 24 34 0 62 64 5 45 81 27 61
   ^     ^^ ^^
```
no condition violated

```java
78 67 69 58 41 24 34 0  62 64 5 45 81 27 61
         ^           ^^ ^^
```
58 < 62, condition violated
percolate

```java
78 67 69 62 41 24 34 0  58 64 5 45 81 27 61
   ^     ^^ ^^
      ^        ^^ ^^
         ^           ^^ ^^
```
no condition violated

```java
78 67 69 62 41 24 34 0 58 64 5 45 81 27 61
            ^             ^^ ^^
```
41 < 64, condition violated
percolate

```java
78 67 69 62 64 24 34 0  58 41 5  45 81 27 61
   ^     ^^ ^^
      ^        ^^ ^^
         ^           ^^ ^^
            ^              ^^ ^^
```
no condition violated, proceed

```java
78 67 69 62 64 24 34 0 58 41 5 45 81 27 61
               ^               ^^ ^^ 
```
24 < 45, condition violated
percolate

```java
78 67 69 62 64 45 34 0 58 41 5 24 81 27 61
               ^               ^^ ^^
```
45 < 81, condition violated
percolate

```java
78 67 69 62 64 81 34 0 58 41 5 24 45 27 61
      ^        ^^ ^^
```
69 < 81, condition violated
percolate

```java
78 67 81 62 64 69 34 0 58 41 5 24 45 27 61
^  ^^ ^^
```
78 < 81, condition violated
percolate

```java
81 67 78 62 64 69 34 0 58 41 5 24 45 27 61
...
                  ^                  ^^ ^^
```
34 < 61, condition violated
percolate

```java
81 67 78 62 64 69 61 0 58 41 5 24 45 27 34
```
All numbers have been stored in a heap
...
How to HeapSort:
The numbers stored in the heap are taken out one by one
Descending order heaps (max heaps) have their elements removed in order from largest values (root node/first number in the array)
Easier more intuitive way of HeapSort

```java
81 67 78 62 64 69 61 0 58 41 24 45 27 34
          81
          |   
    67          78
    |           |
 62    64     69    61
|  |  |  |   |  |  |  |
0 58  41 5  24  45 27 34
remove root (first array element/largest element)
This number gets swapped w the number that is rightmost in the heap array
34 67 78 62 64 69 61 0 58 41 24 45 27 81
...
          34
          |   
    67          78
    |           |
 62    64     69    61
|  |  |  |   |  |  |  
0 58  41 5  24  45 27 
percolate
          78
          |   
    67          34
    |           |
 62    64     69    61
|  |  |  |   |  |  |  
0 58  41 5  24  45 27
percolate
          78
          |   
    67          69
    |           |
 62    64     34    61
|  |  |  |   |  |  |  
0 58  41 5  24  45 27
percolate
          78
          |   
    67          69
    |           |
 62    64     45    61
|  |  |  |   |  |  |  
0 58  41 5  24  34 27
...
unsorted maxheap                          sorted
78 67 69 62 64 45 61 0 58 41 5 24 34 27 | 81
rmove root, replace with rightmost element in unsorted array
          27
          |   
    67          69
    |           |
 62    64     45    61
|  |  |  |   |  |    
0 58  41 5  24  34 
percolate
...
keep doing this until no more nodes are left in the unsorted array
```
...
we percolate as needed if a condition is violated

```java
unsorted                                 sorted
34 67 78 62 64 69 61 0 58 41 5 24 45 27 | 81
```
many conditions violated, percolate until max heap attained

```java
unsorted                                 sorted
78 67 69 62 64 45 61 0 58 41 5 24 34 27 | 81
```
swap root node with last unsorted element

```java
unsorted                               sorted
27 67 69 62 64 45 61 0 58 41 5 24 34 | 78 81
```
percolate, swap, percolate until all elements are in the sorted array


Pseudocode

```java
Heapsort(array A)
    BuildHeap(A)
    for i = n to 1
        swap(A[1], A[i])
        n = n - 1
        Heapify(A, 1)

BuildHeap(array A)
    n = elements_in(A)
    for i = floor(n/2) to 1
        Heapify(A,i,n)

Heapify(array A, int i, int n)
    left = 2i
    right = 2i+1

    if (left <= n) and (A[left] > A[i])
        max = left
    else 
        max = i

    if (right<=n) and (A[right] > A[max])
        max = right

    if (max != i)
        swap(A[i], A[max])
        Heapify(A, max)
```
**Selection Sort**
Using a linear search, the smallest value is located 
Once linear search is done, the smallest value swaps with leftmost element in the unsorted array
This element is considered fully sorted

```java
6 1 7 8 9 3 5 4 2
```
Linear search

```java
6 1 7 8 9 3 5 4 2
  S
```
Swap

```java
1 | 6 7 8 9 3 5 4 2
```
Linear search

```java
1 | 6 7 8 9 3 5 4 2
                  S
```
Swap

```java
1 2 | 7 8 9 3 5 4 6                  
```
Linear search

```java
1 2 | 7 8 9 3 5 4 6  
            S                
```
Swap

```java
1 2 3 | 7 8 9 37 5 4 6  
```
Repeat until all elements are sorted


**Counting Sort**
Best/Average/Worst case: O(k+n) aka linear time


Counting sort is based on keys in a specific range
It works by counting the number of objects having distinct key values
Then does arithmetic to calculate position of each object in the output sequence
Unsorted array:

```java
1 4 1 2 7 5 2
```
Create a count array to store the count of each
Initially the counts of all elements is 0

```java
1 4 1 2 7 5 2
...
index: 0 1 2 3 5 6 7 8 9
count: 0 0 0 0 0 0 0 0 0
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 2 0 1 1 0 1 0 0
```
Now we modify the count array by adding the previous counts

```java
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 2 0 1 1 0 1 0 0
2 + 2 = 4
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 0 1 1 0 1 0 0
4 + 0 = 4
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 1 1 0 1 0 0
4 + 1 = 5
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 1 0 1 0 0
5 + 1 = 6
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 6 0 1 0 0
6 + 0 = 6
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 6 6 1 0 0
6 + 1 = 7
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 6 6 7 0 0
7 + 0 = 7
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 6 6 7 7 7
7 + 0 = 7
...
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 6 6 7 7 7
```
Since we have a seven input unsorted array, we create an array with seven places

```java
1 4 1 2 7 5 2
index: 0 1 2 3 4 5 6 7 8 9
count: 0 2 4 4 5 6 6 7 7 7
places: 1 2 3 4 5 6 7 
corres: 
```
The corresponding values represent the places in the count array
We place the objects in their correct positions and decrease the count by one



```java
(1) 4 1 2 7 5 2
index: 0 (1) 2 3 4 5 6 7 8 9
count: 0 (2) 4 4 5 6 6 7 7 7
places: 1 2 3 4 5 6 7 
corres:   1
... decrease count
1 (4) 1 2 7 5 2
index: 0 1 2 3 (4) 5 6 7 8 9
count: 0 1 4 4 (5) 6 6 7 7 7
places: 1 2 3 4 5 6 7 
corres:   1     4
... decrease count
1 4 (1) 2 7 5 2
index: 0 (1) 2 3 4 5 6 7 8 9
count: 0 (1) 4 4 4 6 6 7 7 7
places: 1 2 3 4 5 6 7 
corres: 1 1    4
... decrease count
1 4 1 (2) 7 5 2
index: 0 1 (2) 3 4 5 6 7 8 9
count: 0 0 (4) 4 4 6 6 7 7 7
places: 1 2 3 4 5 6 7 
corres: 1 1   2 4
... decrease count
1 4 1 2 (7) 5 2
index: 0 1 2 3 4 5 6 (7) 8 9
count: 0 0 3 4 4 6 6 (7) 7 7
places: 1 2 3 4 5 6 7 
corres: 1 1   2 4   7
... decrease count
1 4 1 2 7 (5) 2
index: 0 1 2 3 4 (5) 6 7 8 9
count: 0 0 3 4 4 (6) 6 6 7 7
places: 1 2 3 4 5 6 7 
corres: 1 1   2 4 5 7
... decrease count
1 4 1 2 7 5 (2)
index: 0 1 (2) 3 4 5 6 7 8 9
count: 0 0 (3) 4 4 5 6 6 7 7
places: 1 2 3 4 5 6 7 
corres: 1 1 3 2 4 5 7
array is now sorted
```
Pseudocode

```java
countSort(Array A):
    
```


**Radix Sort**
Best/Average/Worst case: O(d(n+k)) aka linear time


This algorithm is used only to sort numbers
We sort the numbers from least significant digit to most significant digit
We use counting sort a subroutine to sort
Unsorted array:

```java
170 45 75 90 802 24 2 66
```
First consider the one's place and sort in increasing order

```java
170 45 75 90 802 24 2 66
  ^  ^  ^  ^   ^  ^ ^  ^
...
170 90 802 2 24 45 75 66
```
Observe that 170 has come before 90 because it appeared before in the original list
Now consider the ten's place and sort in increasing order

```java
170 90 802 _2 24 45 75 66
 ^  ^   ^  ^  ^  ^  ^
... _ is equivalent to 0, we could also write it as 02
802 2 24 45 66 170 75 90
```
Now consider the 100's place and sort in increasing order

```java
802 __2 _24 _45 _66 170 _75 _90
^   ^   ^   ^   ^   ^   ^   ^
...
2 24 45 66 75 90 170 802
```
Array is now fully sorted
Pseudocode

```java
radixSort(Array A):
    int max = getMax(A)    // finds max number to know
                           // number of digits
    for(int i = 1; m/i > 0; max *= 10){
        countSort(A, max);
    }
```




