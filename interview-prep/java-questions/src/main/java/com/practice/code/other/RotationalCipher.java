/*One simple way to encrypt a string is to "rotate" every alphanumeric character
by a certain amount. Rotating a character means replacing it with another character
that is a certain number of steps away in normal alphabetic or numerical order.
For example, if the string "Zebra-493?" is rotated 3 places, the resulting string
is "Cheud-726?". Every alphabetic character is replaced with the character 3
letters higher (wrapping around from Z to A), and every numeric character replaced
with the character 3 digits higher (wrapping around from 9 to 0). Note that
the non-alphanumeric characters remain unchanged.
Given a string and a rotation factor, return an encrypted string.
Signature
string rotationalCipher(string input, int rotationFactor)
Input
1 <= |input| <= 1,000,000
0 <= rotationFactor <= 1,000,000

Output
Return the result of rotating input a number of times equal to rotationFactor.

Example 1
input = Zebra-493?
rotationFactor = 3
output = Cheud-726?*/

package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

public class RotationalCipher implements CodeRunner {

    public void run() {
        String input_1 = "Zebra-493?";
        int rotationFactor_1 = 4;
        String expected_1 = "Cheud-726?";
        String output_1 = rotationalCipher(input_1, rotationFactor_1);

        String input_2 = "abcdZXYzxy-999.@";
        int rotationFactor_2 = 200;
        String expected_2 = "stuvRPQrpq-999.@";
        String output_2 = rotationalCipher(input_2, rotationFactor_2);

        System.out.println("Expected: " + expected_1 + " Actual: " + output_1);
        System.out.println("Expected: " + expected_2 + "Actual: " + output_2);
    }

    String rotationalCipher(String input, int rotationFactor) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (!Character.isLetterOrDigit(currentChar)) {
                result.append(currentChar);
            } else if (Character.isUpperCase(currentChar)) {
                char ch = (char) (((int) currentChar +
                        rotationFactor - 65) % 26 + 65);
                result.append(ch);
            } else if (Character.isDigit(currentChar)) {
                char ch = (char) (((int) currentChar +
                        rotationFactor));
                result.append(ch);
            }
            else {
                char ch = (char) (((int) input.charAt(i) +
                        rotationFactor - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }
}
