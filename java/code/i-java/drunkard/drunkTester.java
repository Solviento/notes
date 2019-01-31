// simple demo of how to use instantiate an object using a helper class file (drunk.java)
import java.util.Scanner;

public class drunkTester {
  public static void main(String... args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Please enter a starting avenue (integer): ");
    int ave = in.nextInt();
    System.out.print("Please enter the starting street (integer): ");
    int street = in.nextInt();
    drunk d = new drunk(ave, street);
    // Move 100 itersections
    d.fastForward(100);
    // Get current location
    String location = d.getLocation();
    // Get dist from start
    int dist = d.howFar();
    System.out.println("Current loc: " + location + "\nIt is " + dist + " blocks from the starting point.");
  }

}