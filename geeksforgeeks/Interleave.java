// Print all interleavings of given two strings
// Assume: AB MN, B comes after A and N comes after M and s1 len == s2 len
// AB MN
// AB MN  interleave = ""
// B MN   interleave = "A"
// MN     interleave = "AB"
// N      interleave = "ABM"
//        interleave = "ABMN" *
// B MN   interleave = "A"
// B N    interleave = "AM"
// N      interleave = "AMB"
//        interleave = "AMBN" *
// B
import java.util.*;
import java.lang.*;
import java.io.*;

class Interleave{
  public static void main(String[] args) {
    String s1 = "abc";
    String s2 = "def";

    int m = s1.length();
    int n = s2.length();
    String interleave = "";
    printInterleave(s1, s2, interleave, m, n);
  }
  static void printInterleave(String s1, String s2, String interleave, int m, int n){
    if (m == 0 && n == 0)
      System.out.println(interleave);
    if (m != 0){
      // interleave += s1.charAt(0);
      printInterleave(s1.substring(1), s2, interleave + s1.charAt(0) , m-1, n);
    }
    if (n != 0) {
      // interleave += s2.charAt(0);
      printInterleave(s1, s2.substring(1), interleave + s2.charAt(0), m, n-1);
    }
  }
}