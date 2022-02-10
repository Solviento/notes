/*You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

https://leetcode.com/explore/interview/card/google/60/linked-list-5/3063/discuss/1340/A-summary-about-how-to-solve-Linked-List-problem-C++*/
package com.practice.code.linkedlists;

import com.practice.code.model.ListNode;
import com.practice.code.runner.CodeRunner;

public class AddTwoNumbers implements CodeRunner {

    @Override
    public void run() {
        // 15/10 = 1
        int carryOver = 15/10;
        // 8/10 = 0
        int carrOver2 = 8/10;
        System.out.println("c1: " + 15/10 + " c2: " + 8/10);
    }

    public ListNode addTwoNumbersFirst(ListNode l1, ListNode l2) {
        int carryOver = 0;
        ListNode returnNode = new ListNode();
        ListNode head = returnNode;
        // if we run out of nodes in either l1 or l2, we need to calculate the final node value using carryOver
        while (carryOver != 0 || l1 != null || l2 != null) {
            if (l1 != null) {
                carryOver += l1.val;
            }
            if (l2 != null) {
                carryOver += l2.val;
            }
            // create new resultant sum node
            head.next = new ListNode(carryOver % 10);
            // iterate to next node
            head = head.next;
            // eliminate small carryOvers less than 10 to proceed to next node
            carryOver /= 10;
            // iterate to next nodes in lists
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // start of returned linked list
        return returnNode.next;
    }
}
