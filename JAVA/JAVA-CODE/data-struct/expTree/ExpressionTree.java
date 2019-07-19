import java.util.Stack;

public class ExpressionTree {
  private Stack top;
  private String peek;
  // Constructor, tree root must be initialized to null
  public ExpressionTree() {
    MyStack<String> stack = new MyStack<>();
    top = null;
    peek = stack.peek();
  }
  // Nested Class - Will create ExpressionNode Object
  private class ExpressionNode {
    private String data;
    private ExpressionNode left, right; // Children
    // Exp Node Constructor
    public ExpressionNode(String expr) {
      data = expr;
      left = null;
      right = null;
    }
  }
  // Stack based object to hold Expression Node
  public class Stack {
    // Instance variables
    private ExpressionNode node;
    private Stack next;
    // Stack Constructor
    public Stack(ExpressionNode word) {
      node = word;
      next = null; // node.next must evaluate to null
    }
  }
  // Push method for Stack
  public void push(ExpressionNode node) {
    // Two cases, empty stack or non-empty
    if (top == null) {
      top = new Stack(node);
    } else {
      Stack node2 = new Stack(node);
      node2.next = top;
      top = node2;
    }
  }
  // Pop method for Stack
  public ExpressionNode pop() {
    if (top == null) {
      // Throw underflow exception, can't go any less
      throw new RuntimeException("Underflow");
    } else {
      ExpressionNode popped = top.node;
      top = top.next; // top is now null
      return popped; // must return popped element
    }
  }
  // Peek method for Stack
  public ExpressionNode peek() {
    return top.node; // ROOT of Exp. tree
  }
  // Determines whether to pop/push, depends on operator/operand input
  public void stackPush(String x) {
    // Two cases, operand (0, 2..) operator(+, -...)
    if (checkOperand(x)) {
      // Tree node of x is created and pushed
      ExpressionNode node = new ExpressionNode(x);
      push(node);
    } else if (checkOperator(x)) {
      // Must pop and form a new tree, which is then pushed
      ExpressionNode node = new ExpressionNode(x);
      // Pops the highest node in stack
      // Set as left child
      node.left = pop();
      node.right = pop(); // Pops second highest node in stack, right child
      push(node);
    } else {
      System.out.println("Non operator/operand entered");
    }
  }
  // Verifies if input is an operand
  public boolean checkOperand(String x) {
    if (isInteger(x)) {
      return true;
    } else {
      return false;
    }
  }
  // Checks if integer
  public static boolean isInteger(String input) {
    try {
      int x = Integer.parseInt(input);
      return x > -1;
    } catch (NumberFormatException e) {
      // Non integers return false
      return false;
    }
  }
  // Verifies if input is an operator
  public boolean checkOperator(String x) {
    if (x.equals("+") || x.equals("-") || x.equals("/") || x.equals("*")) {
      return true;
    } else {
      return false;
    }
  }
  // Converts to int
  public int stringInt(String s) {
    return Integer.parseInt(s);
  }
  public void Tree(String expression) {
    // FIX: POSTFIX INPUT WILL BE 34 2 - 5 * WHICH IS EQUAL TO INFIX:
    // (34-2)*5
    // Use for loop to check and push elements into expression stack
    String in = expression;
    String[] parsed = in.split(" ");
    for (int i = 0; i < parsed.length; i++) {
      stackPush(parsed[i]);
    }
  }
  public int eval() {
    // When invoked on an expression tree object it will
    // return the integer value associated with evaluating
    // the expression tree
    // It will return arithmetic result of the tree
    return evaluate(peek());
  }
  // Recursive method is used to find leaf nodes
  public int evaluate(ExpressionNode node) {
    // Check first if tree node is child-less
    // If childless, data is ready to be used in calculations
    if (node.left == null && node.right == null) {
      return stringInt(node.data);
    } else {
      String op = node.data;
      int left = evaluate(node.left);
      int right = evaluate(node.right);
      int sum = 0;
      if (op.equals("+")) {
        sum = left + right;
      } else if (op.equals("-")) {
        sum = right - left;
      } else if (op.equals("*")) {
        sum = left * right;
      } else if (op.equals("/")) {
        sum = left / right;
      }
      return sum;
    }
  }
  private String postfix = "";

  public String postfix() {
    // When invoked on expression tree obj. will return
    // a String that contains the corresponding prefix
    // expression (needs to call a private recursive method
    // that takes in the root)
    return post(peek());
  }

  private String post(ExpressionNode node) {
    // Already postfix
    if (node != null) {
      post(node.right);
      post(node.left);
      postfix += node.data + " ";
    }
    return postfix;
  }

  public String prefix() {
    // Invoke on exp. tree obj. will return a String that
    // contains the coressponding prefix expression
    // Will need to call a private recursive method
    // that takes in the root
    return pre(peek());
  }

  // Recursive
  private String prefix = "";
  private String pre(ExpressionNode node) {
    if (node != null) {
      prefix += node.data + " ";
      pre(node.right);
      pre(node.left);
    }
    return prefix;
  }

  private String infix = " ";
  public String infix() {
    // Invoke on exp. tree obj. will return a String that
    // contains the corresponding infix expression
    // Will need to call a private recursive method
    // that takes in the root
    return in(peek());
  }
  private String in(ExpressionNode node) {

    if (node != null) {
      infix += "(";
      in(node.right);
      infix += node.data;
      in(node.left);
      infix += ")";
    }
    return infix;
  }
}