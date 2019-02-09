import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
// INORDER:   LEFT, ROOT, RIGHT ------------------------------
// PREORDER:  ROOT, LEFT, RIGHT ------------------------------
// POSTORDEr: LEFT, RIGHT, ROOT ------------------------------
class Tree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
            left = null;
            right = null;
        }
    }
    static class BinaryTree{
        TreeNode root;
        BinaryTree(){
            root = null;
        }
        // inorder traversal algorithm using recursion
        List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            inorderRecursive(root, inorder);
            return inorder;
        }
        void inorderRecursive(TreeNode root, List<Integer> arr){
            if (root == null){
                return;
            }
            // left node first, then root, then right node
            inorderRecursive(root.left, arr);
            arr.add(root.val);
            inorderRecursive(root.right, arr);
        }
        // inorder traversal but using iterative loop
        List<Integer> inorderTraversalIterative(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            List<Integer> in = new ArrayList<>();
            TreeNode current = root;
            // crucial to understand, while a right child node may be null the stack MUST be empty to complete the traversal
            while (current != null || !stack.empty()) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                // use stack as backorder
                current = stack.pop();
                in.add(current.val);
                current = current.right;
            }
            return in;
        }
        // preorder traversal using recursion
        List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> preorder = new ArrayList<>();
            preorderRecursive(root, preorder);
            return preorder;
        }
        void preorderRecursive(TreeNode root, List<Integer> preorder) {
            if (root == null) {
                return;
            }
            // root first, then left then right nodes
            preorder.add(root.val);
            preorderRecursive(root.left, preorder);
            preorderRecursive(root.right, preorder);
        }
        List<Integer> inorder(){
            return inorderTraversal(root);
        }
        List<Integer> preorder(){
            return preorderTraversal(root);
        }
        // Given a binary tree, find its maximum depth.
        // The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int depthL = maxDepth(root.left);
            depthL++; // add level
            int depthR = maxDepth(root.right);
            depthR++; // add level
            return Math.max(depthL, depthR);
        }
        // Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
        // For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 0) {
                return null;
            }
            int med = (int) Math.ceil(nums.length / 2);
            TreeNode head = new TreeNode(nums[med]);

            int[] numsLeft = Arrays.copyOfRange(nums, 0, med);
            int[] numsRight = Arrays.copyOfRange(nums, med + 1, nums.length);

            head.left = sortedArrayToBST(numsLeft);
            head.right = sortedArrayToBST(numsRight);
            return head;
        }
    }
    public static void main(String... args){
        BinaryTree bt = new BinaryTree();
        bt.root = new TreeNode(1);
        bt.root.left = new TreeNode(2);
        bt.root.right = new TreeNode(3); 
        bt.root.left.left = new TreeNode(4); 
        bt.root.left.right = new TreeNode(5);
        List<Integer> in = bt.inorder();
        for(Integer e: in ){
            System.out.print(e+ " ");
        }
    }
}