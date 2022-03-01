package com.practice.code.searching;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.Stack;

public class DepthFirstSearchIterativeTree implements CodeRunner {
    @Override
    public void run() {

    }

    public void traversePreOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public void traverseInOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);
        while(node != null || !stack.isEmpty()){
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            TreeNode top = stack.pop();
            System.out.println("Value :" + top.val);
            node = top.right;
        }
    }

    public void traversePostOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = node;
        TreeNode current = node;
        stack.push(node);
        while(!stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = (current.left != null || current.right != null);
            boolean isPrevLastChild = (prev == current.right ||
                    (prev == current.left && current.right == null));

            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                System.out.println(current.val);
                prev = current;
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }
}
