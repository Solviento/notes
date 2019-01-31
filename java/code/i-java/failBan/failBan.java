import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class failBan {

  public static void main(String[] args) {

    boolean done = false;
    while (!done) {
      try {
        String filename = args[0];
        readFile(filename);
        done = true;
      } catch (FileNotFoundException exception) {
        System.out.println("File not found.");
        break;
      } catch (NoSuchElementException exception) {
        System.out.println("File contents invalid.");
        break;
      } catch (IOException exception) {
        exception.printStackTrace();
        break;
      }
    }
  }

  public static void readFile(String filename) throws IOException {
    String invalidTerm = "Invalid";
    ArrayList<String> textLine = new ArrayList<String>();
    ArrayList<String> invalidAddressList = new ArrayList<String>();

    File inputFile = new File(filename);
    Scanner in = new Scanner(inputFile);

    while (in.hasNext()) {
      String addLine = in.nextLine();
      textLine.add(addLine); // Works! Each line is now read!
    }
    in.close();
    for (int i = 0; i < textLine.size(); i++) {
      String lineOf = textLine.get(i); // Get (i) string from arrayList
      int intIndex = lineOf.indexOf(invalidTerm); // Get index of "from"
      if (intIndex > -1) {
        String badIP = extractIP(lineOf); // RETURNS STRINGS OF BAD IPs
        // Now put all bad IP addresses into arrayList
        invalidAddressList.add(badIP);
      }
    }
    outputBan(invalidAddressList);
  }

  public static String extractIP(String textLine) throws IOException {
    String extractedIP = "";
    int intIndex = textLine.indexOf("from");
    // Must now find IP index by adding 5, hardcoded
    int intIP = intIndex + 5;
    // Now extract String values until end of String line
    for (int i = intIP; i < textLine.length(); i++) {
      extractedIP += Character.toString(textLine.charAt(i));
    }
    return extractedIP;
  }

  public static void outputBan(ArrayList<String> textLine) throws FileNotFoundException {
    // Write output file using printWriter
    PrintWriter banFile = new PrintWriter("ban.txt");
    Set<String> set = new HashSet<String>();
    for (int i = 0; i <textLine.size(); i++){
      int freq = Collections.frequency(textLine, textLine.get(i));
      if (freq > 3){
        set.add(textLine.get(i));
      }
    }
    for (String s : set) {
      banFile.println(s);
    }
    banFile.close();
  }
}