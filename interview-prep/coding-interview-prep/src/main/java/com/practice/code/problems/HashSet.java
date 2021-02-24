package com.practice.code.problems;

import java.util.Arrays;

public class HashSet {
  boolean[] arr;

  /** Initialize your data structure here. */
  public HashSet() {
    arr = new boolean[1000000];
    Arrays.fill(arr, false);
  }

  public void add(int key) {
    arr[key] = true;
  }

  public void remove(int key) {
    arr[key] = false;
  }

  /** Returns true if this set contains the specified element */
  public boolean contains(int key) {
    return arr[key];
  }
}