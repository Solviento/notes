package com.practice.code.searching;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.Stack;

public class DepthFirstSearchIterativeTree implements CodeRunner {

    @Override
    public void run() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        traversePostOrder(root);
    }

    // root, left, right
    // 1) stack 2) push root 3) pop stack, check node value 4) check right child, push to stack 5) check left child, push to stack
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

    // left, root, right
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

    // left, right, root
    public void traversePostOrder(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = node;
        TreeNode current;
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
