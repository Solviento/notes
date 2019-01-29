/* Will convert degrees to celsius and vice versa */
import java.util.Scanner;

public class degCon{
  // verify user input is a numeric value
  public static double verifyInput(String s){
    Scanner in = new Scanner(System.in);
    double number;
    boolean set;
    do{
      System.out.print("Enter " + s + " value: ");
      while(!in.hasNextDouble()){
        System.out.print("Please enter a numeric value: ");
        in.next();
      }
      number = in.nextDouble();
      set = true;
    }while(!set);
    in.close();
    return number;
  }
  public static void main(String... args) {
    // verify user input for degree conversion
    int number;
    Scanner in = new Scanner(System.in);
    do{
      System.out.print("Enter 1 to convert from Celsius to Farenheit, Enter 2 to convert from Farenheit to Celsius: ");
      while(!in.hasNextInt()){
        System.out.print("Please enter the number 1 or 2: ");
        in.next();
      }
      number = in.nextInt();
    }while(number <=0 || number > 2);
    // compute calculations
    switch (number) {
      case 1:
        double c = verifyInput("celsius");
        double f = (c*(9.0/5.0) + 32.0);
        System.out.printf("Conversion to fahrenheit: %.2f", f);
        break;
      case 2:
        double f_ = verifyInput("farenheit");
        double c_ = (f_-32)*(5.0/9.0);
        System.out.printf("Conversion to celsius: %.2f", c_);
        break;
      default:
        System.out.println("Wrong input");
        break;
    }
  }
}