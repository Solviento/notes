/*There is a safe protected by a password. The password is a sequence of n digits where each digit can be in the range [0, k - 1].

The safe has a peculiar way of checking the password. When you enter in a sequence,
it checks the most recent n digits that were entered each time you type a digit.

For example, the correct password is "345" and you enter in "012345":
After typing 0, the most recent 3 digits is "0", which is incorrect.
After typing 1, the most recent 3 digits is "01", which is incorrect.
After typing 2, the most recent 3 digits is "012", which is incorrect.
After typing 3, the most recent 3 digits is "123", which is incorrect.
After typing 4, the most recent 3 digits is "234", which is incorrect.
After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.
Return any string of minimum length that will unlock the safe at some point of entering it.

Example 1:

Input: n = 1, k = 2
Output: "10"
Explanation: The password is a single digit, so enter each digit. "01" would also unlock the safe.

Example 2:
Input: n = 2, k = 2
Output: "01100"
Explanation: For each possible password:
- "00" is typed in starting from the 4th digit.
- "01" is typed in starting from the 1st digit.
- "10" is typed in starting from the 3rd digit.
- "11" is typed in starting from the 2nd digit.
Thus "01100" will unlock the safe. "01100", "10011", and "11001" would also unlock the safe.*/
package com.practice.code.treesgraphs;

import com.practice.code.model.TreeNode;
import com.practice.code.runner.CodeRunner;

import java.util.LinkedList;
import java.util.Queue;

public class FlipEquivalentBinaryTrees implements CodeRunner {
    @Override
    public void run() {

    }

    public boolean flipEquivIterative(TreeNode root1, TreeNode root2) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode curr1 = queue.poll();
            TreeNode curr2 = queue.poll();

            if (curr1 == null && curr2 == null) {
                continue;
            }
            if (!isEquals(curr1, curr2)) {
                return false;
            }
            if (isEquals(curr1.left, curr2.left) && isEquals(curr1.right, curr2.right)) {
                queue.offer(curr1.left);
                queue.offer(curr2.left);
                queue.offer(curr1.right);
                queue.offer(curr2.right);
            } else if (isEquals(curr1.left, curr2.right) && isEquals(curr1.right, curr2.left)) {
                queue.offer(curr1.left);
                queue.offer(curr2.right);
                queue.offer(curr1.right);
                queue.offer(curr2.left);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isEquals(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null && root1.val == root2.val) {
            return true;
        } else {
            return false;
        }
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return true;
        }
        if(root1 == null || root2 == null){
            return false;
        }
        if(root1.val != root2.val){
            return false;
        }

        boolean leftLeft = flipEquiv(root1.left, root2.left);
        boolean leftRight = flipEquiv(root1.left, root2.right);
        boolean rightLeft = flipEquiv(root1.right, root2.left);
        boolean rightRight = flipEquiv(root1.right, root2.right);

        if((leftLeft && rightRight) || (leftRight && rightLeft)){
            return true;
        }
        return false;
    }
}
