/*https://www.geeksforgeeks.org/level-order-tree-traversal/*/

package com.practice.code.searching;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearchIterativeTree implements CodeRunner {

    @Override
    public void run() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Level order traversal" +
                "of binary tree is - ");
        printLevelOrder(root);
    }

    /* Given a binary tree. Print its nodes in level order using array for implementing queue  */
    // 1) queue 2) add root 3) poll queue, check node value 4) check left child, enqueue it 5) check right child, enqueue it
    void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            /* poll() removes the present head.*/
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.val + " ");

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
}
