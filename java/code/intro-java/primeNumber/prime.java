/* Will evaluate primeness of an integer */
import java.util.Scanner; // Scanner
public class prime {
  public static boolean checkPrime(int number) {
    boolean prime = true; // Default is prime
    for (int i = 2; i <= number / 2; i++) {
      // Please verify what prime checker method is this
      if (number % i == 0) {
        prime = false; // Once i divides cleanly into input, not prime
        break;
      }
    }
    return prime;
  }
  public static void main(String... args) {
    Scanner keyb = new Scanner(System.in);
    System.out.println("Enter a number to check if prime: ");
    // int num = keyb.nextInt();
    do{

    }while(num <= 0);
    boolean primeEval = checkPrime(num);
    if (primeEval) {
      System.out.println("Number is prime");
    } else {
      System.out.println("Number is not prime");
    }
  }
}