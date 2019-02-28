# Backtracking

- Backtracking is a method to solve problems by making a series of choices that we can return or backtrack to
- Think of losing your pair of keys, you backtrack to the last known places of where you passed by and might have left them
- This is crucial for the N Queen placement problem (distinct placements of n queens on a nxn board)
- When to realize you need to use backtracking:
    - In cases where you need to know several past & future decision points, you'll likely need to use backtracking

Keys to backtracking
- Our choice
    - What choice do we make at each call of the function?
    - Recursion expresses decision
- Our constraints, very important!!
    - When do we stop following a certain path?
    - When do we not even go one way?
- Our goal
    - Whats our target?
    - What are we trying to find?
- Backtrack
  - Once a change has been made, look for a way to reverse that change or simply do another
    call on the same input
    - void permute(String str){permute(str+"first"; ... permute(str+"second"))}

Write a program to print all permutations of a given string
- A permutation is a rearrangement of elements in an ordered list S
- A string of length n has n! permutation

ABC -> ABC, ACB, BAC, BCA, CBA, CAB

