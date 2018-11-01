# Trees and Graphs

https://onedrive.live.com/redir?resid=44BA9AE29F00ABD4%212332&page=Edit&wd=target%28Cracking%20Coding%20Interview.one%7Ce8247875-3b77-4a29-a6e4-0982911a5f05%2FTrees%20and%20Graphs%7C7c229bec-6712-47f1-823e-173d16702b2f%2F%29

Types of trees

Remember, trees are a type of graph

 A tree is a data structure composed of nodes

Each tree has a root node

Root node has zero or more child nodes, each child has zero or more child nodes



class Node {

public String name;

public Node[] children;

}



Binary tree

A binary tree is a tree in which each node has up to two children

Not all trees are binary trees

You can also have ternary trees



Binary search tree

A binary search tree is a binary tree in which every node fits a specific ordering property

i.e. all left descendants <= n < all right descendants

some cannot have duplicate values, etc.



Balanced vs. Unbalanced

Not all trees are balanced, ask whether a tree is a "perfect binary tree" or similar

Balanced trees usually have O(log n) for insert and find

Balanced examples include red-black tree and AVL trees



Complete binary tree

A complete binary tree is a binary tree in which every level is filled, EXCEPT perhaps the last level

If the last level is not filled, it must be filled left to right (meaning it can be empty on the right side)



Full binary tree

A binary tree in which every node has either zero or two children

No nodes have only one child



Perfect binary tree

A tree that is both full and complete, all leaf nodes will be at same level and this level has the maximum number of nodes

Rare in interviews and real life

A perfect tree must have exactly 2k - 1 nodes



In-order traversal

To visit often the left branch, then the current node, then finally the right node

When performed on binary search tree, it visits nodes in ASCending order (hence in-order)



void inOrderTraversal(TreeNode node){

if (node != null) {

inOrderTraversal(node.left);

visit(node);

inOrderTraversal(node.right);

}

}



Pre-order traversal

To visit the current node, before its child node (hence pre-order)

The root is always the first node visited



void preOrderTraversal(TreeNode node){

if (node != null){

visit(node);

preOrderTraversal(node.left);

preOrderTraversal(node.right);

}

}



Post-order traversal

Visits the current node after its child nodes (hence post order)

The root is always the last node visited



Binary heaps (min-heap and max-heap)

A min-heap is a complete binary tree where each node is smaller than its children, the root is the minimum element in the tree + other complete binary tree conditions

minheap: insert

When inserting to a min heap, we start by inserting the element at the bottom at the rightmost spot to maintain the complete tree property

We fix the tree by swapping the new element with its parent until its finds an appropriate spot

Takes O(log n) time, where n is the number of nodes in the heap

minheap: extract_min

To find the minimum element of a min-heap, simply take the root element on top

Once removed, replace the root element with the last element in the heap (bottommost, rightmost element), then bubble it down until min-heap property is restored
