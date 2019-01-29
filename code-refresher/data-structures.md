# All Data Structures and Implementation

Arrays
- Java
  - In Java, arrays are dynamically allocated
``` Java
int[] arr;        // declares an Array of integers
arr = new int[5]; // allocating memory for 5 integers
arr[0] = 10;
arr[1] = 20;
```
  - Multi dimensional arrays or Jagged Arrays
``` Java
int[][] intArray = new int[10][20];       // a 2d array or matrix
int[][][] intArray = new int[10][20][10]; // a 3d array
```
  - Cloning arrays using .clone();
    - When cloning single dimension arrays, a deep copy is performed and a new array is created for each copied item
    - For multi dimension arrays, a shallow copy is made and a new array is created for references to the original item
- Python
- Basic Operations:
``` Java
insert()  // insert element at given idx
get()     // return element at given idx
delete()  // deletes element at given idx
size()    // get total number of elements
```
- Common questions
  - Find second minimum element of array
  - First non repeating integers in array
  - Merge two sorted arrays
  - Rearrange positive and negative values in an array
List

Tuple 

Set

Linked List
- Representation:
- (head) [data|pointer] -> [data|pointer] -> [data|pointer] -> null
- Single (unidirectional) and doubly linked list (bi directional)
- Basic Operations
``` Java
insertAtEnd(x)   // inserts given element at end of linked list
insertAtHead(x)  // insert element at the start/head of linked list
delete(x)        // delete given element from linked list
deleteAtHead()   // delete first element of the linked list
search(x)        // returns the given element from linked list
isEmpty()        // return true if empty
```
- Common questions
  - Reverse a linked list
  - Detect loop in a linked list
  - Return nth node from the end in a linked list
  - Remove duplicates from a linked list
Stack
- Basic Operations
``` Java
push()    // inserts element at top
pop()     // returns the top element after removing it from stack
isEmpty() // returns true if stack is empty
top()     // returns top element without removing from stack
```
- Common questions
  - Evaluate postfix expression using stack
  - Sort values in stack
  - Check balanced parentheses in an expression

Queue
- Basic Operations
``` Java
Enqueue() // inserts element to the end of the queue
dequeue() // removes element from start of queue
isEmpty() // returns true if queue is empty
top()     // returns first element of the queue
```
- Common questions
  - Implement stack using queue
  - Reverse first k elements of a queue
  - Generate binary numbers from 1 to n using a queue
  
Double-ended Queue

Priority Queue

Graph
- Nodes and edges
- Undirected and directed graphs
- Can be representated using adj matrix and adj lists
- Common questions
  - Implement breadth and depth first search
  - Check if a graph is a tree or not
  - Count number of edges in a graph
  - Find the shortest path between two vertices
  - 
Adjacency List

Adjacency Matrix

Tree
- Trees are graphs but trees CANNOT have cycles
- User in AI and complex algorithms to provide efficient storage mechanism
- Root, parent, child, leaf, sibling
- Common questions
  - Find the height of a binary tree
  - Find kth maximum value in a binary search tree
  - Find nodes at 'k' distance from the root
  - Find ancestors of a given node in a binary tree

Tries
- Known as prefix trees
- Efficient for solving problems with STRINGS
  - Good for searching words in a dictionary, providing auto suggestions and IP routing
- Common questions
  - Count total number of words in trie
  - Print all words stored in trie
  - Sort elements of an array using trie
  - Form words from dictionary using trie
  - Build T9 dictionary
  
AVL Tree

Binary Search Tree

Binary Tree

Red black Tree

Heap
- 

Treap

ArrayList

Hash list
- Not really data structure, just a list of hashcodes calculated on various chunks of data
- Split a file into many parts, calculate hashcode for each part, store them into a list
  
Hash table
- Key, value pairs + also called dictionaries
- Usually implemented using arrays
- Perfomance is based on hash function, table size, collision handling method
- Common questions
  - Find symmetric pairs in an array
  - Trace complete path of a journey
  - Find if an array is a subset of another array
  - Check if given arrays are disjoint

HashMap

TreeMap

TreeSet