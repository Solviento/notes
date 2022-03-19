/*Given the root of a binary tree, imagine yourself
standing on the right side of it, return the values
of the nodes you can see ordered from top to bottom.

Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]

Example 2:
Input: root = [1,null,3]
Output: [1,3]

Example 3:
Input: root = []
Output: []*/
package com.practice.code.trees;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView implements CodeRunner {
    @Override
    public void run() {

    }

    // BFS Approach using Queue
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>();
        nextLevel.add(root);
        ArrayDeque<TreeNode> currLevel;
        List<Integer> rightside = new ArrayList<>();
        TreeNode node = null;
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel.clone();
            nextLevel.clear();
            while (!currLevel.isEmpty()) {
                node = currLevel.poll();
                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null)
                    nextLevel.offer(node.left);
                if (node.right != null)
                    nextLevel.offer(node.right);
            }
            // The current level is finished.
            // Its last element is the rightmost one.
            rightside.add(node.val);
        }
        return rightside;
    }

    // DFS Approach
    List<Integer> rightside = new ArrayList<>();

    public void helperDFS(TreeNode node, int level) {
        if (level == rightside.size())
            rightside.add(node.val);

        if (node.right != null)
            helperDFS(node.right, level + 1);
        if (node.left != null)
            helperDFS(node.left, level + 1);
    }

    public List<Integer> rightSideViewDFS(TreeNode root) {
        if (root == null) return rightside;

        helperDFS(root, 0);
        return rightside;
    }
}
