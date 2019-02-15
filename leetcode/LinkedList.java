/**
 * Definition for singly-linked list. 
 * public class ListNode { 
 * int val; 
 * ListNode next; 
 * ListNode(int x) { val = x; } }
 */
class LinkedList {
  class SLinkedList{
    ListNode head;
    SLinkedList(){
      head = null;
    }
  }
  class DLinkedList{

  }
  class ListNode{
    int val;
    ListNode next;
    ListNode (int x){
      val = x;
    }
  }
  // iterative, S: O(n) T: O(1)
  static ListNode reverseLList(ListNode head) {
    ListNode tail = null;
    while (head != null) {
      // stash away head.next node
      ListNode nextCopy = head.next;
      // reverse pointers
      head.next = tail;
      // iterate and update tail to become head
      tail = head;
      // retrieve head.next node and continue iteration
      head = nextCopy;
    }
    return tail;
  }
  // S: O(n) T: O(n)
  // 1->2->3->null, DRAW THIS OUT USING NODES!!
  static ListNode reverseListR(ListNode head){
    // iterate to the last node
    if (head == null || head.next == null) {
      return head;
    }
    // think of recursively returning a reversed list: 
    ListNode reversedListHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    // when base case has executed, return 1->(2->null)<-3
    return reversedListHead;
  }
  // delete node in a singly linked list given only access to that node
  public void deleteNode(ListNode node) {
      node.val = node.next.val;
      node.next = node.next.next;
  }
  // Floyd’s Cycle-Finding Algorithm
  // detects cycle in a linked list using two pointers, fast and slow
  // Time: O(n) Space: O(1)
  public boolean hasCycle(ListNode head) {
      if (head == null || head.next == null){
          return false;
      }
      ListNode slow = head;
      ListNode fast = head.next;
      while(slow!=fast){
          if(fast == null || fast.next == null){
              return false;
          }
          slow = slow.next;
          fast = fast.next.next;
      }
      return true;
  }

  public static void main(String... args){

  }
}