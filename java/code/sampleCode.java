import java.util.Scanner;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class sampleCode {
   static Map<Integer, ArrayList<String>> test2 = new HashMap<Integer, ArrayList<String>>(){
      {
         // put(1, new ArrayList<String>("two"));
      }
   };
   public static void main(String []args) {
      // thousand, hundred, -ty, -one
      Scanner reader = new Scanner(System.in);
      System.out.println("Enter number: ");
      int n = reader.nextInt();
      System.out.println(test2);
   }
}