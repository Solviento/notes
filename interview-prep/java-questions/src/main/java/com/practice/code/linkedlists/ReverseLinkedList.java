package com.practice.code.linkedlists;

import com.practice.code.model.ListNode;
import com.practice.code.runner.CodeRunner;

public class ReverseLinkedList implements CodeRunner {

    @Override
    public void run() {

    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

}
