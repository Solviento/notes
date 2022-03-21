/*https://www.geeksforgeeks.org/level-order-tree-traversal/*/

package com.practice.code.searching;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

public class BreadthFirstSearchRecursiveTree implements CodeRunner {

    @Override
    public void run() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Level order traversal of" +
                "binary tree is ");
        printLevelOrder(root);
    }

    /* function to print level order traversal of tree*/
    void printLevelOrder(TreeNode root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            printCurrentLevel(root, i);
        }
    }

    /* Compute the "height" of a tree -- the number of
    TreeNodes along the longest path from the root TreeNode
    down to the farthest leaf TreeNode.*/
    int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight) {
                return (lheight + 1);
            } else {
                return (rheight + 1);
            }
        }
    }

    /* Print TreeNodes at the current level */
    void printCurrentLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.val + " ");
        } else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }
    }
}
