package com.practice.code.problems;
// Given an array of integers A sorted in non-decreasing order,
// return an array of the squares of each number, also in sorted non-decreasing order.
import java.util.Arrays;

class sortedSquares{
  // quicksort O(nlog(n)), space: O(log(n))
  public static int[] sortedSquaresQS(int[] A) {
    int[] sq = new int[A.length];
    for (int i = 0; i < A.length; i++) {
      sq[i] = A[i] * A[i];
    }
    Arrays.sort(sq);
    return sq;
  }
  // pointer method O(n), space O(1)
  public static int[] sortedSquaresN(int[] A){
    int[] sq = new int[A.length];
    int leftPointer = 0, rightPointer = A.length-1; // compare leftPointer and rightPointer elements using Math.abs
     for(int i = A.length-1; i >= 0; i--){
       if(Math.abs(A[leftPointer]) < Math.abs(A[rightPointer])){
        sq[i] = A[rightPointer]*A[rightPointer];
        rightPointer--;
       }
       else{
         sq[i] = A[leftPointer]*A[leftPointer];
         leftPointer++;
       }
       if(leftPointer>rightPointer){
         break;
       }
     }

    return sq;
  }
  public static void main(String... args){
    // ASSUME: input array is already sorted, which makes the O(n) method possible
    // Going backwards on the array and comparing left
    int[] a = new int[]{-4,-1,0,3,10};
    int[] A = sortedSquaresN(a);
    for(Integer e: A){
      System.out.print(e +" ");
    }
  }
}