/*Given the root of a binary tree,
return the length of the diameter of the tree.
The diameter of a binary tree is the length of the
longest path between any two nodes in a tree. This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.

Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
Input: root = [1,2]
Output: 1

Algorithm
Initalize an integer variable diameter to keep track of the longest path we find from the DFS.
Implement a recursive function longestPath which takes a TreeNode as input.
It should recursively explore the entire tree rooted at the given node.
Once it's finished, it should return the longest path out of its left and right branches:
    if node is None, we have reached the end of the tree, hence we should return 0;
    we want to recursively explore node's children, so we call longestPath again with node's left and right children.
    In return, we get the longest path of its left and right children leftPath and rightPath;
    if leftPath plus rightPath is longer than the current longest diameter found, then we need to update diameter;
    finally, we return the longer one of leftPath and rightPath. Remember to add 11 as the edge connecting it with its parent.
Call longestPath with root.
*/
package com.practice.code.trees;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

public class DiameterBinaryTree implements CodeRunner {

    int diameter = 0;

    public int diameterBinaryTree(TreeNode root) {
        depthDiameter(root);
        return diameter;
    }

    // approach
    // use dfs to find each path to every child node on both left and right sides respectively
    // once left child path and right child path is found, compare it to the existing maximum diameter value
    // continue doing for each child left/right path until maximum diameter value is finalized
    public int depthDiameter(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // initial dry run, left sub tree will return 0, likewise with right sub tree will return 0. diameter is not updated
        int diameterLeftSubTree = depthDiameter(node.left);
        int diameterRightSubTree = depthDiameter(node.right);
        diameter = Math.max(diameter, diameterLeftSubTree + diameterRightSubTree);

        // once base case is returned, add 1
        // we go up from the child leaf node and find the max sub tree diameter to pass along + 1 (to count the connecting edge)
        return Math.max(diameterLeftSubTree, diameterRightSubTree) + 1;
    }

    public void run() {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        a1.left = a2;
        a1.right = a3;
        a2.left = a4;
        a2.right = a5;
        int diam = diameterBinaryTree(a1);
        System.out.println("Diamater of binary tree : " + diam);
    }
}
