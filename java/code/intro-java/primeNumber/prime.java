/* Will evaluate primeness of an integer 
 It uses the fact that a prime (except 2 and 3) 
 is of form 6k - 1 or 6k + 1 and looks only at 
 divisors of this form.
 */
import java.util.Scanner;

public class prime {
  public static boolean checkPrime(int number) {
    if (number == 0)
      return false;
    else if (number == 2)
      return true;
    else if (number == 3)
      return true;
    else if (number % 2 == 0)
      return false;
    else if (number % 3 == 0)
      return false;
    else{
      int i = 5;
      int w = 2;
      while (i * i < number){
        if (number % i == 0)
          return false;
        i += w;
        w = 6-w;
      }
      return true;
    }
  }
  public static void main(String... args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter a number to check primeness: ");
    int num = -1;
    do{
      while(!in.hasNextInt()){
        System.out.print("Please enter a positive integer: ");
        in.next();
      }
      num = in.nextInt();
      if (num < 0)
        System.out.print("Please enter a positive integer: ");
    }while(num < 0);

    if (checkPrime(num)){
      System.out.println("Prime");
    }
    else{
      System.out.println("Composite");
    }
  }
}