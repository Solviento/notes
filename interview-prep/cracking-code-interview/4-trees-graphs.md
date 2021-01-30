# Trees and Graphs

Types of trees
- Remember, trees are a type of graph
- A tree is a data structure composed of nodes
- Each tree has a root node
- Root node has zero or more child nodes, each child has zero or more child nodes
``` Java
class Node {

public String name;
public Node[] children;

}
class Tree{
  public Node root;
}
```

**Binary tree**

- A binary tree is a tree in which each node has up to two children
- Not all trees are binary trees
- You can also have ternary trees

**Binary search tree**

- A binary search tree is a binary tree in which every node fits a specific ordering property
- i.e. all left descendants <= n < all right descendants
- some cannot have duplicate values, etc.

**Balanced vs. Unbalanced**
- Not all trees are balanced, ask whether a tree is a "perfect binary tree" or similar
- Balanced trees usually have O(log n) for insert and find
- Balanced examples include red-black tree and AVL trees

**Complete binary tree**

- A complete binary tree is a binary tree in which every level is filled, EXCEPT perhaps the last level
- If the last level is not filled, it must be filled left to right (meaning it can be empty on the right side)

**Full binary tree**

- A binary tree in which every node has either zero or two children
- No nodes have only one child

**Perfect binary tree**

- A tree that is both full and complete, all leaf nodes will be at same level and this level has the maximum number of nodes
- Rare in interviews and in real life
- A perfect tree must have exactly 2k - 1 nodes

**In-order Traversal**

- LEFTMOST-MIDDLE-RIGHTMOST
- To visit often the left branch, then the current node, then finally the right node
- When performed on binary search tree, it visits nodes in ASCending order (hence in-order)
``` Java
void inOrderTraversal(TreeNode node){
  if (node != null) {
    inOrderTraversal(node.left);
    visit(node);
    inOrderTraversal(node.right);
  }
}
```

**Pre-order Traversal**

- MIDDLE-LEFTMOST-RIGHTMOST
- To visit the current node, before its child node (hence pre-order)
- The root is always the first node visited
``` Java
void preOrderTraversal(TreeNode node){
  if (node != null){
    visit(node);
    preOrderTraversal(node.left);
    preOrderTraversal(node.right);
  }
}
```

**Post-order Traversal**

- LEFTMOST-RIGHTMOST-MIDDLE
- Visits the current node after its child nodes (hence post order)
- The root is always the last node visited
``` Java
void postOrderTraversal(TreeNode node) { 2
  if (node !; null) {
    postOrderTraversal(node.left);
    postOrderTraversal(node.right);
    visit(node);
  }
}
```
**Binary heaps (min-heap and max-heap)**

- A min-heap is a complete binary tree where each node is smaller than its children, the root is the minimum element in the tree + other complete binary tree conditions
  - minheap: insert
    - When inserting to a min heap, we start by inserting the element at the bottommost, rightmost spot to maintain the complete tree property
    - We fix the tree by swapping the new element with its parent until its finds an appropriate spot
    - Takes O(log n) time, where n is the number of nodes in the heap
  - minheap: extract_min
    - To find the minimum element of a min-heap, simply take the root element on top
    - Once removed, replace the root element with the last element in the heap (bottommost, rightmost element), then bubble it down until min-heap property is restored

**Tries (Prefix trees)**

- Funny data structure, good for interviews
- A trie is a variant of an n-ary tree, where characters are stored at each node
  - Each path down may represent a word
- The * nodes indicate a complete word
- A trie is used to store the entire English language for quick prefix lookups
- Hash tables can quickly look up if a string is a valid word but it cannot tell if a string is a prefix any valid words
  - Tries do this quickly
- A trie can check if a string is a valid prefix in O(K) time
  - K is the length of the string
  - Same runtime of a hash table lookup
- Problems involving **lists of valid words** will utilize a trie as an optimization

**Graphs**

- A tree is a type of graph but not all graphs are trees
- Graphs are a collection of nodes with edges between them
- Graphs can have cycles or not (acyclic)

**Adjacency List**

- Common way to represent a graph
- Every vertex or node stores a list of adjacent vertices
- In undirected graphs, (a,b) would be stored twice, once in a and again in b
- A class isn't necessary to represent a graph, an array of lists or hash table to store the adjacency table
- Example of a graph and node
``` Java
class Graph {
  public Node[] nodes;
}
class Node {
  public String name;
  public Node[] children;
}
```
- Example of a graph
Nodes : Adjacent Nodes
8 : 1 
1 : 2
2 : 0, 3 
3 : 2 
4 : 6 
5 : 4 
6 : 5

**Adjacency Matrices**

- An adjacency matrix is an NxN boolean matrix where N is the number of nodes where a true value at matrix, [i][j] represents an edge between i and j
- In an undirected graph, the adjacency matrix will be symmetric
- The graph algorithms used for adjacency lists can also be used on matrixes with less efficiency
  - It's easy to iterate through the neighbors of a node
  - It's more work to iterate through all nodes to identify a node's neighbors

**Graph Search**
- DFS
  - In DFS we start the root or some selected node and explore each branch completely before moving to the next branch
  - DFS is preferable if you want to visit every node in the graph (simpler to do)
  - Pre order tree traversal is a form of DFS, key difference being that implementing DFS will need to check if node has already been visited
``` Java
void search(Node root){
  if (root == null) return;
  visit(root);
  root.visited = true;
  for each (Node n in root.adjacent){
    if (n.visited == false){
      search(n);
    }
  }
}
```

- BFS
  - In BFS we start at the root or selected node and explore each neighbor before going on to any of their children
  - If we want to find the shortest path (or any path) between two nodes then BFS is better
  - BFS is not recursive! It uses a *queue*
  - Must visit all of a's neighbors before visiting their neighbors, iterative solution works best
``` Java
void search(Node root) { 
  Queue queue = new Queue(); 
  root.marked = true;
  queue.enqueue(root); //Add to the end of queue 
  while (!queue.isEmpty()) {
    Node r = queue.dequeue(); // Remove from the front of the queue 
    visit(r);
    foreach (Node n in r.adjacent) { 
      if (n.marked == false) {
        n.marked = true; 
        queue.enqueue(n);
      } 
    }
  }
}
```

**Bidirectional Search**

- bidirectional search is used to find the shortest path between a source and destination node
- Operates by running two simultaneous BFS searches, one from each node
- We find a path when their searches collide
- This search is faster is many cases
  - BFS search takes up to O(k^d)
  - Bidirectional takes up to O(k^d/2)
    - this is a significant time boost (d/2 * d/2 = d)
