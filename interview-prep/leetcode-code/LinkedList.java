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
  // iterative, Space: O(n) Time: O(1)
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
  // Time: O(n) Space: O(1) ** very good problem, uses floyd cycle pointers and another function to reverse linked list using a node
  public boolean isPalindrome(ListNode head) {
    // have two pointers, one fast and one slow
    // fast one will be 2x as fast, which will end at tail and slow pointer will be
    // at middle
    // be careful with uneven and odd lengths
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // if even length, fast will end up as null
    // if odd, fast ptr will end up at final node
    // odd case:
    // 1-2-1-2-1
    //     s   f
    // 1-2 .. 2-1
    // f      s
    if (fast != null) {
      slow = slow.next;
    }
    // even case:
    // 1-2-2-1
    //     s f
    // 1-2 .. 2-1
    // f      s
    fast = head;
    slow = reverseLList_(slow);
    // perform value check on every node
    while (slow != null) {
      if (slow.val != fast.val) {
        return false;
      }
      slow = slow.next;
      fast = fast.next;
    }
    return true;
  }

  public ListNode reverseLList_(ListNode head) {
    ListNode prev = null;
    while (head != null) {
      ListNode tmp = head.next; // make a copy of a reference to the next node for last step, aka iteration
                                // (head = tmp)
      head.next = prev; // re-wire head node to point to previous node
      prev = head; // move prev pointer to head location
      head = tmp; // move head pointer to tmp location (head.next)
    }
    return prev;
  }
  // Write a program to find the node at which the intersection of two singly
  // linked lists begins. If no intersection exists, return null
  // This method uses two pointers, have each pointer iterate through both linked lists
  // if one pointer reaches null, have it reassigned to the head of the Other list and vice versa
  // if there is an intersection, break, if both pointers reach null then there is no intersection, break, draw out iterations to understand
  // Time: O(m+n) Space: O(1)
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode aPointer = headA;
    ListNode bPointer = headB;
    while (aPointer != bPointer) {
      if (aPointer == null) {
        aPointer = headB;
        bPointer = bPointer.next;
      } else if (bPointer == null) {
        bPointer = headA;
        aPointer = aPointer.next;
      } else {
        bPointer = bPointer.next;
        aPointer = aPointer.next;
      }
    }
    return aPointer;
  }
  // alternative above method using differences in length of two linked lists
  public ListNode getIntersectionNode_(ListNode headA, ListNode headB) {
    // get copies of head node reference
    ListNode headACpy = headA;
    ListNode headBCpy = headB;
    // get length of list A
    int lengthA = 0;
    while (headA != null) {
      lengthA++;
      headA = headA.next;
    }
    // get length of list B
    int lengthB = 0;
    while (headB != null) {
      lengthB++;
      headB = headB.next;
    }
    // get abs difference of list lengths
    int difference = Math.abs(lengthA - lengthB);
    // assigned references to longest, shortest lists
    ListNode longestList = (lengthA >= lengthB) ? headACpy : headBCpy;
    ListNode smallList = (lengthA < lengthB) ? headACpy : headBCpy;
    // iterate longest linked list using abs difference
    for (int i = 0; i < difference; i++) {
      longestList = longestList.next;
    }
    // nodes should now start along same indices AND meet at intersection or null
    while (longestList != smallList) {
      longestList = longestList.next;
      smallList = smallList.next;
    }
    // return intersection or null
    return longestList;
  }

  public static void main(String... args){

  }
}