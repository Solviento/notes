/*A linked list of length n is given such that each ListNode contains an additional random pointer, which could point to any ListNode in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new ListNodes, where each new ListNode has its value set to the value of its corresponding original ListNode. Both the next and random pointer of the new ListNodes should point to new ListNodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to ListNodes in the original list.

For example, if there are two ListNodes X and Y in the original list, where X.random --> Y, then for the corresponding two ListNodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n ListNodes. Each ListNode is represented as a pair of [val, random_index] where:

val: an integer representing ListNode.val
random_index: the index of the ListNode (range from 0 to n-1) that the random pointer points to, or null if it does not point to any ListNode.
Your code will only be given the head of the original linked list.

Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]*/

package com.practice.code.linkedlists;

import com.practice.code.model.ListNode;
import com.practice.code.runner.CodeRunner;

public class CopyListWithRandomPointer implements CodeRunner {
    @Override
    public void run() {

    }

    public ListNode copyRandomList(ListNode head) {
        ListNode iter = head, next;

        // First round: make copy of each nRode,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            ListNode copy = new ListNode(iter.val);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy ListNodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        ListNode pseudoHead = new ListNode(0);
        ListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }

}
