// simple credit card number checker using Luhn's algorithm
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class creditCard{
    public static void luhn(long num){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while (num > 0){
            arr.add((int)(num%10));
            num = num / 10;
        }
        Collections.reverse(arr);
        int odds = 0;
        for(int i = arr.size() - 1; i >= 0; i--){
            if (i%2 == 0){
                int d = arr.get(i) * 2;
                arr.set(i, d);
            }
            else{
                odds += arr.get(i);
            }
        }
        int singles = 0;
        for(Integer e: arr){
            if (String.valueOf(e).toString().length() < 2){
                singles += e;
            }
        }
        int sum = singles+odds;
        if (sum%10==0){
            System.out.println("Valid");
        }
        else{
            System.out.println("Invalid");
        }
    }
    
    public static void main(String... args){
        long number = -1;
        Scanner in = new Scanner(System.in);
        do{
            System.out.print("Please enter credit cardnumber that is 16 digits: ");
            while(!in.hasNextLong()){
                System.out.print("Please enter credit cardnumber that is 16 digits: ");
                in.next();
            }
            number = in.nextLong();
            if (String.valueOf(number).length() != 16){
                System.out.println("Not 16 digits, try again.");
                number = -1;
            }
        }while(number < 0);
        luhn(number);
    }
}