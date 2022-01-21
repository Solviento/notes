/*You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

https://leetcode.com/explore/interview/card/google/60/linked-list-5/3063/discuss/1340/A-summary-about-how-to-solve-Linked-List-problem-C++*/
package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class AddTwoNumbers implements CodeRunner {

    @Override
    public void run() {

    }

    public ListNode addTwoNumbersFirst(ListNode l1, ListNode l2) {
        int c = 0;
        ListNode prev = new ListNode();
        ListNode head = prev;
        while (c != 0 || l1 != null || l2 != null) {
            if (l1 != null) {
                c += l1.val;
            }
            if (l2 != null) {
                c += l2.val;
            }
            head.next = new ListNode(c % 10);
            head = head.next;
            c /= 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return prev.next;
    }
}
