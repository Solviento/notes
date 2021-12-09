package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

// Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the
// length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
public class DiameterBinaryTree implements CodeRunner {

    private class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
    }

    int diameter = 0;
    public int diameterBinaryTree(TreeNode root) {
        depthDiameter(root);
        return diameter;
    }

    public int depthDiameter(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int diameterLeftSubTree = depthDiameter(node.left);
        int diameterRightSubTree = depthDiameter(node.right);
        diameter = Math.max(diameter, diameterLeftSubTree+diameterRightSubTree);

        // once base case is returned, add 1
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
