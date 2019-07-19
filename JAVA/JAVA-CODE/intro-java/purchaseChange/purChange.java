/* Will calculate change based on purchase */
import java.util.Scanner; // Scanner

public class purChange{
  public static void main(String[] args) {
    String s = "";
    String g = "";
    Scanner in = new Scanner(System.in);
    do{
      System.out.print("Enter amount due ($xx.xx format): $");
      String due = in.next();
      if (due.matches("[0-9]{1,3}[.][0-9]{2}")){
        s += due;
        do{
          System.out.print("Enter amount given ($xx.xx): $");
          String given = in.next();
          if (given.matches("[0-9]{1,3}[.][0-9]{2}")){
            g +=  given;
          }
        }while(g.length() <= 0);
      }
    }while(s.length() <= 0);
    String[] arr = s.split("\\.");
    int dollar_d = Integer.parseInt(arr[0]);
    int cents_d = Integer.parseInt(arr[1]);

    String[] giv = g.split("\\.");
    int dollar_g = Integer.parseInt(giv[0]);
    int cents_g = Integer.parseInt(giv[1]);

    int dollar = dollar_g - dollar_d;
    int cents = cents_g - cents_d;
    // missing case
    int quarters = dollar * 4;
    int cent_quarters = (cents / 25);
    int dimes = (cents-(cent_quarters*25))/10;
    int nickels = (cents-(cent_quarters*25)-(dimes*10))/5;
    int pennies = (cents-(cent_quarters*25)-(dimes*10)-(nickels*5))/1;
    System.out.println("Change is: " + (cent_quarters+quarters) + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + pennies + " pennies");
  }
}