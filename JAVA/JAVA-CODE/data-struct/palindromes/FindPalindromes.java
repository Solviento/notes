import java.util.Scanner;
import java.io.*;
import java.util.*;

public class FindPalindromes extends MyStack {
  public static void main(String... args) {
      try {
        readFile(args[0]);
      // if input file txt not found
      } catch (FileNotFoundException e) {
        System.out.println("Failure. Input file not found in system.");
      // if input txt file contents are corrupted
      } catch (NoSuchElementException e) {
        System.out.println(e);
      // io error
      } catch (IOException e) {
        e.printStackTrace();
      // if txt argument not given in command line
      } catch(ArrayIndexOutOfBoundsException e){
        e.printStackTrace();
      }
  }
  /* Will take name of a text file (as String) and operate on it */
  public static void readFile(String name) throws IOException {
    FileInputStream input = new FileInputStream(name);
    Scanner in = new Scanner(input);
    String line, stripped;
    while (in.hasNextLine()) {
      line = in.nextLine();
      stripped = parseString(line);
      if (testPalindrome(stripped)) {
        System.out.println(line);
      }
    }
    in.close();
  }
  /* Will format string */
  public static String parseString(String line) {
    String stripped = line.replaceAll("\\W", "");   // \w represents character from a-z,A-Z,0-9
    stripped = stripped.toLowerCase(); 
    return stripped;
  }
  /* use one stack and a string to test pallidrome */
  public static boolean testPalindrome(String line) {
    MyStack<String> s = new MyStack<String>(); 
    String rev = "";
    for(int i = 0; i <line.length(); i++){
      // push <- Character.toString <- line.charAt
      s.push(Character.toString(
                    line.charAt(i)));
    }
    while(!s.isEmpty()){
      rev += s.pop();
    }
    if (rev.equals(line)){
      return true;
    }
    else
      return false;
  }
}