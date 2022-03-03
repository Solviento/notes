/*Given a binary tree, find the lowest
common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two
nodes p and q as the lowest node in T that has both p
and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1*/
package com.practice.code.treesgraphs;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

public class LowestCommonAncestorOfABinaryTree implements CodeRunner {
    @Override
    public void run() {

    }

    public TreeNode backtrack(TreeNode root, TreeNode p, TreeNode q) {

        // for the first level recursion, if p(or q) is exactly the whole
        // tree's root, return the root(because root is the lowest node).
        // for deeper(higher nodes) recursions, it indicates:
        // (1) we find p(or q), which is fine because this node will not
        // be returned(to output) instantly since it belongs to a deeper
        // recursion but will be used for determinations of shallower(lower nodes) recursions
        // (2) we reach the bottom of the tree, terminate the current track
        // instantly and "null" will also be used for shallower recursions' determine statements.
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = backtrack(root.left, p, q);
        TreeNode right = backtrack(root.right, p, q);

        // determine statements

        if (left != null && right != null) // which means p,q exist below different subtrees;
            return root;

        return left != null ? left : right; // which means p,q exist below the same subtree;

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return backtrack(root, p, q);

    }
}
