/*A permutation also called an “arrangement number” or “order,”
is a rearrangement of the elements of an ordered list S into a
one-to-one correspondence with S itself. A string of length n has n! permutation.

Below are the permutations of string ABC.
ABC ACB BAC BCA CBA CAB*/

package com.practice.code.backtracking;

import com.practice.code.runner.CodeRunner;

public class PermutationsOfAString implements CodeRunner {

    /**
     * permutation function
     *
     * @param str string to calculate permutation for
     * @param l   starting index
     * @param r   end index
     */
    private void permute(String str, int l, int r) {
        if (l == r)
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    /**
     * Swap Characters at position
     *
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    @Override
    public void run() {
        String str = "ABC";
        int n = str.length();
        PermutationsOfAString permutation = new PermutationsOfAString();
        permutation.permute(str, 0, n - 1);
    }
}
