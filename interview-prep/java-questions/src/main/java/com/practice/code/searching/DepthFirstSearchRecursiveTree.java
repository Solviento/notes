package com.practice.code.searching;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

public class DepthFirstSearchRecursiveTree implements CodeRunner {
    @Override
    public void run() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder();
    }

    class BinaryTree {
        // Root of Binary Tree
        TreeNode root;

        BinaryTree() {
            root = null;
        }

        /* Given a binary tree, print its nodes in preorder*/
        void printPreorder(TreeNode node) {
            if (node == null)
                return;

            /* first print data of node */
            System.out.print(node.val + " ");

            /* then recur on left subtree */
            printPreorder(node.left);

            /* now recur on right subtree */
            printPreorder(node.right);
        }

        void printPostorder(TreeNode node) {
            if (node == null)
                return;

            // first recur on left subtree
            printPostorder(node.left);

            // then recur on right subtree
            printPostorder(node.right);

            // now deal with the node
            System.out.print(node.val + " ");
        }

        /* Given a binary tree, print its nodes in inorder*/
        void printInorder(TreeNode node) {
            if (node == null)
                return;

            /* first recur on left child */
            printInorder(node.left);

            /* then print the data of node */
            System.out.print(node.val + " ");

            /* now recur on right child */
            printInorder(node.right);
        }

        // Wrappers over above recursive functions
        void printPreorder() {
            printPreorder(root);
        }
    }
}
