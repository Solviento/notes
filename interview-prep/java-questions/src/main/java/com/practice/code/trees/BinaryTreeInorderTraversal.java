/*Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]*/

package com.practice.code.trees;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal implements CodeRunner {
    @Override
    public void run() {

    }

    // recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    // t: o(v) s: o(v)
    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            // inorder: left, middle, right
            helper(root.left, res);
            res.add(root.val);
            helper(root.right, res);
        }
    }

    // stack (dfs)
    // t: o(v) s: o(v)
    public List<Integer> inorderTraversalDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // keep pushing left nodes to stack
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // we can now start popping and go from left, middle, right
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
