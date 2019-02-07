import java.util.ArrayList;
import java.util.List;
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
        List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            inorderRecursive(root, inorder);
            return inorder;
        }
        void inorderRecursive(TreeNode root, List<Integer> arr){
            if (root == null){
                return;
            }
            inorderRecursive(root.left, arr);
            arr.add(root.val);
            inorderRecursive(root.right, arr);
        }
        List<Integer> inorder(){
            return inorderTraversal(root);
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