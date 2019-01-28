# Big O

Explaining big O notation to a nontechnical user:
- You have a file stored on a hard drive, how would you send it?
  - If it’s a small file, you send through email or FTP in seconds
    - In this respect, it would run in O(s) time where s is size of file
  - For a large file, it would take several hours or even days
    - In this case, you could simply physically take the drive to the destination which would be faster
    - Would run in O(1) time, the time it takes to deliver the file physically

Time complexity
- O, in academia big O describes Upper bound
  - If used, the algorithm is at least fast as this bound
  - Less than or equal to
- Omega, lower bound
  - Algorithm will not be faster than this bound
- Theta, tight bound
- In industry, typically tight bound notation is used

Space complexity
- Roughly equivalent to time
- Recursive programs can easily take polynomial space
- Sequential calls usually take O(1) space or similar

Multi part algorithms: add v. multiply
- If algorithm is in the form: do this, then once that’s done, do that - add the runtimes
- If algorithm is in the form: do this for each time you do that - multiply the runtimes

Amortized time
- This allows us to describe that the worst case happens every once in a while but once it happens it wont happen again for so long that the cost is amortized

Log n time
- Binary search typically works as you expect it to: looking for a single element, you first compare it to the midpoint of an array or list (that is sorted). If not the element, continue searching in either the left of right of the midpoint (depending if greater or less than to midpoint element).
  - This will divide number of to be searched elements by 2 in each successive step
  - Typically when you see a problem where the number of elements in the problem space gets halved each time, it will likely be a O(log N) time ***
- Same concept applies to balanced binary search tree, with each comparison you can go either left or right
  - Half of to be searched nodes lie on each side, so you cut the problem space in half each time

Recursive runtime
- Important! Say you are given a simple recursive algorithm

``` Java
int f(int n){
    if (n <= 1){
        return 1;
    }
    return f(n - 1) + f(n -1);
    }
}
```
  - Understand that for a function call of f(4), this will call f(3) twice, each f(3) call will then call f(2) twice and so forth.
  - This can be drawn as a tree, where each level will have twice as many calls as the preceding one
  - To complete this algorithm, it will have a big O(2N) runtime.
  - Space complexity will be O(N) since only N elements exist at any time (sequential calls)

``` Java
void printUnorderedPairs(int[] array){
    for (int i = 0; i < array.length; i++){
        for (int j = i + 1; j < array.length; j++){
            System.out.println(array[i] + "," + array[j]);
        }
    }
}
```
  - Not intuitive at first, must look at and count the iterations
  - First iteration, j runs for N-1 steps, second iteration it runs for N-2 steps, until final reaches the last single step iteration
  - *The sum of 1 through N-1 is 𝑵(𝑵−𝟏)/2*
  - *This run time amounts to O(N2)*

Sample algorithms
- Design algorithm that takes in an array of strings, sorts each string, then sorts the full array. What is the runtime?
  - Let s be length of longest string
  - Let a be length of the array
    - Sorting each string is O(s log s)
      - Quicksort, mergesort, heapsort
    - Doing this iteratively for every string in the array is O(a * s log s)
    - To sort the strings in the array
      - To do so we must compare the strings to each other, each comparison is O(s)
        - Why? Must go through entire strings to check for comparison
          - Master > Matter
      - There are a strings, so there O(a log a) comparisons
      - Total time will take O(a*s log a)
    - Adding both parts will take O(a*s log a + a*s log s)

- Estimate run time of this algorithm on a balanced binary search tree
``` Java
int sum(Node node) {
    if (node == null) {
       return a;
    }
    return sum(node . left) + node. value + sum(node.right);
}
```
- The code will touch each node in the tree once (at most), therefore the runtime is O(N)
- Looking at the recursive pattern
  - Runtime of a recursive function is usually O(branches^depth)
  - At each call there are two branches, so it becomes O(2^depth)
  - The tree is a balanced binary search tree!! ***
    - If there are N nodes, depth is roughly log N ***
  - Therefore runtime becomes O(2log N)
  - Let P = 2log N
    - log2P = log2N
    - P = N
    - Therefore, runtime becomes O(N)

``` Java
boolean isPrime(int n) {
    for (int x = 2; x*x <= n; x++){
        if (n % x == 0) {
            return false;
        }
    }
    return true;
}
```

- Careful!
- The work inside the loop is constant
- How many iterations does the for loop go through?
  - Starts when x = 2, ends with x*x = n
  - In other words, it stops when x = sqrt(n)
  - Therefore, this code runs at O(sqrt(n)) time

``` Java
int factorial(int n){
    if (n< 0){
        return -1;
    }
    else if (n == 0){
        return 1;
    }
    else{
         return n * factorial(n-1);
    }
}
```
- Look carefully at line 9
  - Give it an input of 9 or 12
  - Will do a straight recursion call of
    - 9 * (9-1) * (9-2) * … * 1
    - 12 * (12-1) * (12-2) * … * 1
    - Therefore, the runtime will be O(n)

``` Java
void permutation(String str){
     permutation(str, "");
}

void permutation(String str, String prefix){
    if (str.length() == 0){
        System.out.println(prefix);
    }
    else{
         for(int i = 0; i < str.length(); i++){
             String rem = str.substring(0, i) + str.substring(i + 1);
             permutation(rem, prefix + str.charAt(i));
         }
    }
}
```

- Tricky!
- Evaluate how many times permutation gets called && how long each call takes
- Think about permutations
  - Generally, to generate a permutation we would need to get the number of characters in a string, say 7
  - Thus the total number of possibilities becomes 7! (factorial) or n!
- Looking further we see that the for loop runs for O(n) with the permutation call nested inside, thus the runtime becomes O(n * n!)
- Line 6 takes O(n) time to execute (since each character needs to be printed)
  - Also know that str.charAt(i) will also be O(n) time
  - Each node in this call tree will correspond to O(n) work
  - This means that in a tree with n * n! nodes, each permutation will take O(n)
  - Therefore, total run time of this algorithm is O(n*n * n!)

``` Java
int fib(int n){
    if (n <= 0) return 0;
    else if (n == 1) return 1;
    return fib(n-1) + fib(n -2);
}
```

- Use earlier pattern established for recursive calls: O(branchesdepth)
- We see that there are 2 branches per call
- As or depth, we go as deep as N, therefore runtime is O(2N)
- Through complicated math, runtime is actually less than this (still technically correct)
- Tip: when algorithm uses multiple recursive calls, its usually an exponential runtime

``` Java
void allFib(int n){
    for (int i = 0; i < n; i++){
        System.out.println(i + ": " + fib(i));
    }
}

int fib(int n){
    if (n <= 0) return 0;
    else if (n == 1) return 1;
    return fib(n - 1) + fib(n - 2);
}
```

- At quick glance, it appears to run at O(n*2n) time
  - Fib() will run in 2n, branches = 2 and depth = n
- Looking closer we see that a allFib(n) call will do:
  - Fib(1) = 21 steps
  - Fib(2) = 22 steps
  - Fib(3) = 23 steps
  - …
- Fib(n) = 2N steps
- Thus total amount of work is 21 + 22 + 23 + … + 2n
- This expression sums to 2n+1, therefore runtime is O(2n)
- While the for loop does run O(n) times, it's very important to understand that in each iteration, the input to fib() changes

``` Java
void allFib(int n){
     int[] memo = new int[n+1];
     for(int i = 0; i < n; i++){
         System.out.println(i + ": " + fib(i, memo));
     }
}

int fib(int n, int[] memo){
    if(n <= 0) return 0;
    else if (n == 1) return 1;
    else if (memo[n] > 0) return memo[n];
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    return memo[n];
}
```

- Look at how the algorithm runs through the calls
  - Fib(1) -> return 1;
  - Fib(2) ->
    - Fib(1) -> return 1;
    - Fib(0) -> return 0;
    - Store 1 at memo[2]
  - Fib(3) ->
  - Fib(2) -> lookup memo[2] -> return 1
  - Fib(1) -> return 1
  - … and so forth
- This lookup takes constant amount of time
- Doing a constant amount of work by N times takes O(N) time

``` Java
int powersof2(int n){
     if (n<1){
         return 0;
     }
    else if (n == 1){
          System.out.println(1);
          return 1;
     }
    else{
        int prev = powersof2(n/2);
        int curr = prev*2;
        System.out.println(curr);
        return curr;
     }
}
```

- An approach would be to do a call like powersof2(50) and then draw it out as such:
  - Powersof2(50)
    - Powersof2(25)
      - Powersof2(12)
        - Powersof2(6)
          - …. Powersof2(1) -> print and return 1
        - Print and return 4
      - Print and return 8
    - Print and return 16
  - Print and return 32
- As seen above, this algorithm will halve n until reaching 1
  - The number of times we can halve n until getting 1 is O(log n) ***
