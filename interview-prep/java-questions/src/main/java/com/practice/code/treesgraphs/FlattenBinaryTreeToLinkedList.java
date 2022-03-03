/*Given the root of a binary tree, flatten the tree into a "linked list":
The "linked list" should use the same TreeNode class where
the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

Example 1:
Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [0]
Output: [0]*/
package com.practice.code.treesgraphs;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

public class FlattenBinaryTreeToLinkedList implements CodeRunner {
    @Override
    public void run() {

    }

    // Recursive
    private TreeNode flattenTree(TreeNode node) {

        // Handle the null scenario
        if (node == null) {
            return null;
        }

        // For a leaf node, we simply return the
        // node as is.
        if (node.left == null && node.right == null) {
            return node;
        }

        //Recursively flatten the left subtree
        TreeNode leftTail = this.flattenTree(node.left);

        // Recursively flatten the right subtree
        TreeNode rightTail = this.flattenTree(node.right);

        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side
        // anymore.
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        // We need to return the "rightmost" node after we are
        // done wiring the new connections.
        return rightTail == null ? leftTail : rightTail;
    }

    public void flatten(TreeNode root) {

        this.flattenTree(root);
    }

    // iterative using stack
    public void flattenUsingStack(TreeNode root) {

        // Handle the null scenario
        if (root == null) {
            return;
        }

        TreeNode node = root;

        while (node != null) {

            // If the node has a left child
            if (node.left != null) {

                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // move on to the right side of the tree
            node = node.right;
        }
    }
}
