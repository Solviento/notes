/*Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.*/
package com.practice.code.treesgraphs;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

public class CountCompleteTree implements CodeRunner {
    public int countNodes(TreeNode root) {
        if (root != null) {
            return 1 + countNodes(root.right) + countNodes(root.left);
        } else {
            return 0;
        }
    }

    @Override
    public void run() {

    }
}
