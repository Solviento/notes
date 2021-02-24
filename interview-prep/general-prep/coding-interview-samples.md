# Coding Interview Samples

**A collection of numbers where the sum is the following:**

Sum = 8

[1, 2, 3, 9] -> No Pair

[1, 2, 4, 4] -> Pair Exists

We are looking for a pair of numbers in a collection where the end sum is 8.

Can assume there is always 4 numbers, all numbers are integers, and that numbers can repeat.

Brute force

- Compare all numbers one by one and check if a pair equals 8
  - two for loops (i, j) to compare pairs
  - Not optimal

If collection is sorted, we can try a more optimal approach

- Try Binary Search?
  - From left to right, if you have [1, 2, 3, 9]
    - Pick 1 first, the complement of 1 and 8 is 7
      - Would then search using binary search to find if there is a 7
        - log(n) time
        - In this case, there is no 7 so we move on to the next one
    - We then pick 2, the complement of 2 and 8 is 6
      - Would then search using binary search to find if there is 6
        - repeat like same above
    - After some time, we would iterate through all numbers in the collection and find that there is no pair which sum is 8
      - Time would nlog(n)
        - binary search * number of elements in array
        - binary search is only log(n) for a sorted collection
      - nlog(n) is still slow
  - Interviewer could advise to just start off using pairs instead of just starting off with one number
    - In this case, it would be the two pointer approach
  - If we use two pointers, with one at the start and another at the end we could iteratively compare the smallest and largest elements in the array at the same time
    - Make note that the smallest number and largest number provide bounds which will ultimately determine if the pair meets the sum of 8

**Example of pseudo algorithm**

``` 
1 2 3 9
i     j
i + j = 10
is i + j > 8? yes, move j to the left - why? j represents largest value, if sum is greater then we must decrease this value

1 2 3 9
i   j
i + j = 4, move i to the right

1 2 3 9
  i j
i + j = 5, move i to the right

1 2  3   9
    i,j
i = j, this collection does not have a pair that equals 8
```

- This algorithm is faster than nlog(n) search from before since it is using linear search using two pointers
  - in the end, it is essentially n + n = 2n -> n

**New challenge: the collection is no longer sorted!**

- If we sort using an algorithm, time cost will revert to what we had initially, nlog(n)

- Interview could recommend using the same linear method, of iterating one by one
  - However, we would need to think again about how to solve the issue of finding complement pairs that equal the sum
    - We can come up with a solution where we use a lookup table of whether we have seen a complement number in the past
      - A complement number would be for example 6, if the current iterated number is 2 and the sum is 8
      - If we come across a 2, we can store the complement number into a hash set and then determine if we see that number in the future
        - If we do, then we can say we have found a pair 
      - In this case, we need to compromise in space cost
        - A hash set uses n cost to store data
        - A linear search uses n cost
        - In total, space cost is n and time cost is also n
    - Another question is lookup time, does hash set provide optimal lookup? Yes, it is O(1) time cost so it is near constant
  - What to do if input is too large?
    - Use parallel computing, i.e. multiple machines to search the data in chunks


------

**Amazon: Run Length Encoding**

An older algorithm that encoded image data into shorter length like so:

aaaabbccca

4a2b3c1a

https://www.youtube.com/watch?v=mjZpZ_wcYFg

Basic algorithm for converting image to run length encoding

``` java
public String encode(String input) {
    // check for null/ empty input
    if (input == null || input.length() == 0)
        return "";
    
    char[] inputChars = input.toCharArray();
    char previousChar = inputChars[0];
    int counter = 0;
    StringBuilder output = new StringBuilder();
    
    for(char c: inputChars) {
        if (previousChar == c) {
            counter++;
        }
        else {
            output.append(counter).append(previousChar);
            previousChar = c;
            counter = 1;
        }
    }
    output.append(counter).append(previousChar);
    return output.toString();
}
```







