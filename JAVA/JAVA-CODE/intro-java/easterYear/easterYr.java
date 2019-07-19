// calculates date of easter sunday using Carl Friedrich Gauss algorithm
import java.util.Scanner; 

public class easterYr{
  public static void returnEaster(int y){
    int a = y % 19;
    int b = y / 100;
    int c = y % 100;
    int d = b / 4;
    int e = b % 4;
    int g = (8 * b + 13) / 25;
    int h = (19 * a + b - d - g + 15) % 30;
    int j = c / 4;
    int k = c % 4;
    int m = (a + 11 * h) / 319;
    int r = (2 * e + 2 * j - k - h + m + 32) % 7;
    int n = (h - m + r + 90) / 25;
    int p = (h - m + r + n + 19) % 32;
    printEaster(y, n, p);
  }
  public static void printEaster(int y, int n, int p){
    String month = "";
    switch (n) {
    case 1:
      month += "January";
      break;
    case 2:
      month += "February";
      break;
    case 3:
      month += "March";
      break;
    case 4:
      month += "April";
      break;
    case 5:
      month += "May";
      break;
    case 6:
      month += "June";
      break;
    case 7:
      month += "July";
    default:
      break;
    }
    System.out.println("For year, " + y + " Easter Sunday takes place on " + month + ", " + p);
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int y = -1;
    do{
      System.out.print("Please enter a year after 0 A.D. : ");
      while(!in.hasNextInt()){
        System.out.print("Again, please enter a year after 0 A.D. : ");
        in.next();
      }
      y = in.nextInt();
    }while(y < 0);
    returnEaster(y);
  }
}