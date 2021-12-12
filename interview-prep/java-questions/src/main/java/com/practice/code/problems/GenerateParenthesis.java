package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

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
// Time: ?? Space: O(n), does around 2n calls on the recursion call stack
public class GenerateParenthesis implements CodeRunner {
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
  void backtrackParenthesis(List<String> result, int numOfLeftParenthesis, int numOfRightParenthesis, String str) {
    // no more left or right parenthesis to add
    if (numOfLeftParenthesis == 0 && numOfRightParenthesis == 0) {
      result.add((str));
      return;
    }
    // keep adding left parenthesis until numOfLeftParenthesis reaches 0
    if (numOfLeftParenthesis > 0) {
      backtrackParenthesis(result, numOfLeftParenthesis - 1, numOfRightParenthesis, str + "(");
    }
    // if numOfLeftParenthesis is 0 or less than numOfRightParenthesis, we are allowed to add ")" to
    // close any left parenthesis from current string
    // say numOfLeftParenthesis = 1 and numOfRightParenthesis = 1, then this if statement will not execute
    // since adding a right parenthesis will cause string to become unbalnaced
    if (numOfLeftParenthesis < numOfRightParenthesis) {
      backtrackParenthesis(result, numOfLeftParenthesis, numOfRightParenthesis - 1, str + ")");
    }
  }

  @Override
  public void run() {
    System.out.println("// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.\n" +
            "For example, given n = 3, a solution set is:\n" +
            "[\n" +
            "  \"((()))\",\n" +
            "  \"(()())\",\n" +
            "  \"(())()\",\n" +
            "  \"()(())\",\n" +
            "  \"()()()\"\n" +
            "]");
    System.out.println("solution in class file");
  }
}