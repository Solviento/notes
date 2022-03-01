package com.practice.code.model;

public class DoublyLinkedListNode {

    public int val;
    public DoublyLinkedListNode left;
    public DoublyLinkedListNode right;

    public DoublyLinkedListNode() {}

    public DoublyLinkedListNode(int _val) {
        val = _val;
    }

    public DoublyLinkedListNode(int _val,DoublyLinkedListNode _left,DoublyLinkedListNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
