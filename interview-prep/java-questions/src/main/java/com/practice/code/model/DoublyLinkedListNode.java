package com.practice.code.model;

public class DoublyLinkedListNode {

    int data;
    DoublyLinkedListNode head; // head of list
    DoublyLinkedListNode prev;
    DoublyLinkedListNode next;

    // Constructor to create a new node
    // next and prev is by default initialized as null
    DoublyLinkedListNode(int d) {
        data = d;
    }
}
