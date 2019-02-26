Traveling salesman is an example of
- greedy algorithmm
- Greedy algorithms tries to find localized optimum solution which may eventualy land in globally optimized solutions

Worst case complexity of bubble sort algorithm?
- O(n^2)
- Worst case occurs if array is already sorted but in descending order
- First iteration is n elements, then n-1 elements, and so on
- Total n = n + n-1 + .. + 1 = (n(n+1)/2)

After each teration in bubble sort
- at least one element is at ts sorted position
- one less comparison is made in the next iteration

What is the time complexity of fun()?
``` java
void fun(int n,int arr[])
{
  int i=0,j=0;
  for(; i<n; ++i)
    while(j<n && arr[i] < arr[j])
      j++;
}
```
- O(n)
- Appears to be n^2 but note that the variable j is not initalized for each value of i
- inner loop runs at most n times

If the number of elements to be sorted is small (<100), which sorting is more efficient?
- Insertion
- Insertion sort is good only for sorting small arrays (<100)
  - The faster insertion sort is compared to any other sorting algorithm
  - Being an O(n^2) algorithm gets slow very quick as array increases
  - Insertion sort works the way many people sort a hand of playing hands
    - Start with an empty left hand and cards face down
    - Remove one card from the pile and insert to the correct position in the left hand
    - To find correct position for a card, we compare it with each of the cards already in hand from right to left
    - At all times, cards in the left are sorted

Dynamic programming is a paradigm for solving what kind of algorithmic problems?
- Optimization problems
- Consists of finding solutions for intermediate subproblems which is then stored and reused for solving the actual problem

What is the type of the algorithm used in solving the 8 Queens problem? 
 

Here is detail about 8 queen problem -- 

The eight queens puzzle is the problem of placing eight chess queens on an 8×8 chessboard so that no two queens threaten each other. Thus, a solution requires that no two queens share the same row, column, or diagonal. The eight queens puzzle is an example of the more general n queens problem of placing n non-attacking queens on an n×n chessboard, for which solutions exist for all natural numbers n with the exception of n=2 and n=3.

- Backtracking algorithm provides most efficient solution to this problem
- Dijstrka is good for finding shortest paths between nodes in a graph
- KNP is a string searching algorithm
- Strassen algorithm is used for matrix multiplication

The complexity of the average case of an algorithm is
- More complicted to analyze than worst case
- Average case analysis requires notion of average input to an algorthm which leads to the problem of devising a probability distribution over inputs 

In quick sort, when will the worst case situation arise?
- when array is alread sorted

Which graph algorithm is not greedy?
- Floyd Warshall algorithm uses dynamic programming approach
- Prim, Kruskal, Dijstrka uses greedy programming













