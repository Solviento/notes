/*You are given two non-empty linked lists representing two
non-negative integers. The most significant digit comes first
and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]

Example 2:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]

Example 3:
Input: l1 = [0], l2 = [0]
Output: [0]

Approach, same as AddTwoNumbers but now use ReverseLinkedList algorithm to reverse both lists
Reverse both input lists: l1 = reverseList(l1), l2 = reverseList(l2).
Initialize the result list: head = None.
Initialize the carry: carry = 0.
Loop through lists l1 and l2 until you reach both ends.
Set x1 = l1.val if l1 is not finished yet, and x1 = 0 otherwise.
Set x2 = l2.val if l2 is not finished yet, and x2 = 0 otherwise.
Compute the current value: val = (carry + x1 + x2) % 10, and the current carry: carry = (carry + x1 + x2) / 10.
Update the result by adding the current value to front.
Move to the next elements in the lists.
If the carry is not equal to zero, append it to frond of the result list.
Return the result list: return head.
*/

package com.practice.code.linkedlists;

import com.practice.code.model.ListNode;
import com.practice.code.runner.CodeRunner;

public class AddTwoNumbersII implements CodeRunner {

    @Override
    public void run() {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode sum = addTwoNumbers(l1, l2);
        while(sum != null) {
            System.out.print(sum.val + " ");
            sum = sum.next;
        }
    }

    // reverse input, then construct output by adding to front
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            // keep the next node
            ListNode tmp = head.next;
            // reverse the link
            head.next = last;
            // update the last node and the current node
            last = head;
            head = tmp;
        }
        return last;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // reverse lists
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode sumNode = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x1, x2;
            // get the current values
            if (l1 != null) {
                x1 = l1.val;
            } else {
                x1 = 0;
            }
            if (l2 != null) {
                x2 = l2.val;
            } else {
                x2 = 0;
            }

            // current sum and carry
            int val = (carry + x1 + x2) % 10;
            carry = (carry + x1 + x2) / 10;

            // update the result: add to front
            ListNode tmp = new ListNode(val);
            tmp.next = sumNode;
            sumNode = tmp;

            // move to the next elements in the lists
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = sumNode;
            sumNode = curr;
        }

        return sumNode;
    }
}
