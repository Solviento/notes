/*Given the root of a binary tree, check
whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
Input: root = [1,2,2,3,4,4,3]
Output: true

Example 2:
Input: root = [1,2,2,null,3,null,3]
Output: false*/

package com.practice.code.trees;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree implements CodeRunner {
    @Override
    public void run() {

    }

    // bfs recrusive
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    // null edge case
    // null and non null edge case
    public boolean isMirror(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        // ensure root val is equivalent
        // likewise ensure opposing left and right subtrees for 1 and 2 are the same
        return (tree1.val == tree2.val)
                && isMirror(tree1.right, tree2.left)
                && isMirror(tree1.left, tree2.right);
    }

    // bfs iterative
    public boolean isSymmetricIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode tree1 = q.poll();
            TreeNode tree2 = q.poll();
            // leaves previously encountered, continue
            if (tree1 == null && tree2 == null) {
                continue;
            }
            // unequal tree length, return false
            if (tree1 == null || tree2 == null) {
                return false;
            }
            // unequivalent node value, return false
            if (tree1.val != tree2.val) {
                return false;
            }
            // bfs, add left and right nodes for each tree
            q.add(tree1.left);
            q.add(tree2.right);
            q.add(tree1.right);
            q.add(tree2.left);
        }
        return true;
    }
}
