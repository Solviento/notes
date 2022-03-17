/*You are given an array of k linked-lists lists,
each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
*/
package com.practice.code.linkedlists;

import com.practice.code.model.ListNode;
import com.practice.code.runner.CodeRunner;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLinkedLists implements CodeRunner {
    @Override
    public void run() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode[] lists = new ListNode[]{l1, l2, l3};

        ListNode sorted = mergeKListsLinear(lists);
        while(sorted != null) {
            System.out.print(sorted.val + " ");
            sorted = sorted.next;
        }
    }

    // t: o(n) s: o(1)
    public ListNode mergeKListsLinear(ListNode[] lists) {
        int min_index = 0;
        ListNode dummyRoot = new ListNode();
        ListNode currentHead = dummyRoot;
        while (true) {
            boolean isBreak = true;
            int min = Integer.MAX_VALUE;
            // lists = [node1, node2, node3]
            // lists = [[1,4,5],[1,3,4],[2,6]]
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (lists[i].val < min) {
                        min_index = i;
                        min = lists[i].val;
                    }
                    isBreak = false;
                }

            }
            // no new minimum found since we found the biggest element in the linked lists, finish loop
            if (isBreak) {
                break;
            }
            currentHead.next = new ListNode(lists[min_index].val);
            currentHead = currentHead.next;
            lists[min_index] = lists[min_index].next;
        }
        currentHead.next = null;
        return dummyRoot.next;
    }

    // t: o(logn) s: o(n)
    public ListNode mergeKListsLog(ListNode[] lists) {
        Comparator<ListNode> cmp;
        cmp = (x, y) -> x.val - y.val;

        PriorityQueue<ListNode> q = new PriorityQueue<>(cmp);
        for (ListNode l : lists) {
            if (l != null) {
                q.add(l);
            }
        }
        ListNode head = new ListNode(0);
        ListNode point = head;
        while (!q.isEmpty()) {
            point.next = q.poll();
            point = point.next;
            ListNode next = point.next;
            if (next != null) {
                q.add(next);
            }
        }
        return head.next;
    }

}
