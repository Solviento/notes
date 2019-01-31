import java.util.Scanner;

public class expTester {

  public static final void main(String... args) {

    ExpressionTree test = new ExpressionTree();
    Scanner in = new Scanner(System.in);

    System.out.println("Enter postfix expression (ex: 34 2 - 5 *): ");
    String exp = in.nextLine();

    test.Tree(exp);

    System.out.println("Prefix: " + test.prefix());
    System.out.println("Postfix: " + test.postfix());
    System.out.println("Infix:" + test.infix());
    System.out.println("Expression evaluates to: " + test.eval());
  }
}