package com.practice.code.problems;

import java.util.ArrayList;
import java.util.List;

// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
// For example, given n = 3, a solution set is:
// [
//   "((()))",
//   "(()())",
//   "(())()",
//   "()(())",
//   "()()()"
// ]
// Time: ?? Space: O(2n), does around 2n calls on the recursion call stack
class GenerateParenthesis {
  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    // number of left parenthesis remaining = n, number of right parenthesis remaining = n
    // initial string = ""
    backtrackParenthesis(result, n, n, "");
    return result;
  }
  // constraints:
  // a ')' must always have a corresponding '('
  // if string is equal to n letters, return recursion call
  // say '(((' exists, we still have 3 ')' remaining
  void backtrackParenthesis(List<String> result, int numLeftParen, int numRightParen, String str) {
    // no more left or right parenthesis to add
    if (numLeftParen == 0 && numRightParen == 0) {
      result.add(new String(str));
      return;
    }
    // keep adding left parenthesis until numLeftParen reaches 0
    if (numLeftParen > 0) {
      backtrackParenthesis(result, numLeftParen - 1, numRightParen, str + "(");
    }
    // if numLeftParen is 0 or less than numRightParen, we are allowed to add ")" to
    // close any left parenthesis from current string
    // say numLeftParen = 1 and numRightParen = 1, then this if statement will not execute
    // since adding a right parenthesis will cause string to become unbalnaced
    if (numLeftParen < numRightParen) {
      backtrackParenthesis(result, numLeftParen, numRightParen - 1, str + ")");
    }
  }
}