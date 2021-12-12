//package com.practice.code.problems;
//// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//// Note:
//// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
//// Example 1:
//// Input: root = [3,1,4,null,2], k = 1
////    3
////   / \
////  1   4
////   \
////    2
//// Output: 1
//class KthSmallestBST {
//  public int kthSmallest(TreeNode root, int k) {
//    Stack<TreeNode> stack = new Stack<>();
//    // performs DFS to bottom leaf node
//    while (root != null) {
//      stack.push(root);
//      root = root.left;
//    }
//    // continously pop through stack to get kth node
//    while (k != 0) {
//      k--;
//      TreeNode dfs = stack.pop();
//      if (k == 0) {
//        return dfs.val;
//      }
//      // now collect all bottom child nodes on the right side
//      TreeNode right = dfs.right;
//      while (right != null) {
//        stack.push(right);
//        right = right.left;
//      }
//    }
//    return -1; // no kth element
//  }
//}