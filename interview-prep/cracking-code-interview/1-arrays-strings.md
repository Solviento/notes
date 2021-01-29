# Arrays and Strings

Hash tables
- A data structure that maps keys to values for highly efficient lookups
- Simple implementations of this consist of:
  - Use an array of linked lists and a hash code function
  - To insert key, which can be a string, do the following
    - compute the key's hash code, which is usually an int
    - map the hash code to an index to the array, this can be using: hash(key) % array_length
      - There is possibility of collision
    - At index there is linked list of keys and values, linked list is used to avoid collisions
  - To retrieve pair by its key, you repeat above process
    - Compute hash code from key, then compute index from the hash code, search through linked list for the value
  - If number of collisions is HIGH, then runtime is O(N) 
    - N is number of keys
  - Good implementation results in minimum collision which means lookup is O(1)
  - An alternative way is to use a balanced binary search tree to give a search time of O(logN), note that this uses less memory space and iterates keys in order

ArrayList, Resizable arrays
- ArrayList provides O(1) access
- One the arraylist is full, doubling it will take O(n)
  - This happens rarely so amortized insertion is still O(1)
    - Why is it O(1)?
      - Think of it backwards, everytime it expands it does so like this
        - 1, 2, 4, 8, 16, 32 (assume 32 is our final n)
        - n/32, n/16, n/4, n/2, n
        - Summing the above, we get (n/32+n/16+n/4+n/2) [do not include n]
        - The sum of the series is less than n, which we amortize to 1
    - In worst cases, its O(n)
  

StringBuilder
- useful for concatenating list of strings
- Reminder: 1 + 2 + 3 + ... + n = n(n+1)/2 = O(n^2)
- Below code is inefficient as it results in time O(n^2), do the math on a simple case
``` java
String joinWords(String[] words){
  String s = "";
  for (String w: words){
    s = s + w;
  }
  return s;
}
```
- Stringbuilder avoids this, it creates a resizable array of all the strings and copies them back to a string when necessary
``` java
String joinWords(String[] words){
  StringBuilder s = new StringBuilder();
  for (String w : words){
    s.append(w);
  }
  return s.toString();
}

```