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
            List<Integer> inorderList = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            // TreeNode prev = null;
            while(root != null || stack.isEmpty() == false){
                // traverse and collect all left child nodes
                while(root!=null){
                    stack.push(root);
                    root = root.left;
                }
                // no more left child nodes, collect center node and then set node to right child node
                root = stack.pop();
                inorderList.add(root.val);
                root = root.right;
            }
            return inorderList;
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
        // Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
        // For example, this binary tree [1,2,2,3,4,4,3] is symmetric
        // But the following [1,2,2,null,3,null,3] is not
        // https://www.youtube.com/watch?v=XV7Sg2hJO3Q
        public boolean isSymmetric(TreeNode root) {
            if (root == null)
                return true;
            return isSymRecursive(root.left, root.right);
        }
        boolean isSymRecursive(TreeNode leftChild, TreeNode rightChild) {
            if (leftChild == null && rightChild == null)
                return true;
            if (leftChild == null || rightChild == null)
                return false;
            else {
                boolean valCheck = (leftChild.val == rightChild.val) ? true : false;
                return valCheck && isSymRecursive(leftChild.left, rightChild.right)
                        && isSymRecursive(leftChild.right, rightChild.left);
            }
        }
        // iterative version of tree symmetry
        public boolean isSymmetricIt(TreeNode root) {
            Queue<TreeNode> queue_ = new LinkedList<>();
            queue_.add(root);
            queue_.add(root);
            while (!queue_.isEmpty()) {
                TreeNode leftChild = queue_.poll();
                TreeNode rightChild = queue_.poll();
                if (leftChild == null && rightChild == null) {
                    // valid condition, structure is good
                    continue;
                }
                if (leftChild == null || rightChild == null) {
                    // structure does not hold symmetric condition
                    return false;
                }
                if (leftChild.val != rightChild.val) {
                    // structure may be valid but values are not symmetric
                    return false;
                }
                // add remaining children nodes
                queue_.add(leftChild.left);
                queue_.add(rightChild.right);
                // symmetric principle
                queue_.add(leftChild.right);
                queue_.add(rightChild.left);
            }
            // if none of the above conditions invalidate tree, then return true
            return true;
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