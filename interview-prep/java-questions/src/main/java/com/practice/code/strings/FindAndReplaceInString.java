/*You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.

To complete the ith replacement operation:

Check if the substring sources[i] occurs at index indices[i] in the original string s.
If it does not occur, do nothing.
Otherwise if it does occur, replace that substring with targets[i].
For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.

For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
Return the resulting string after performing all replacement operations on s.
Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".
*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;

public class FindAndReplaceInString implements CodeRunner {

    public String findReplaceString(String S, int[] indices, String[] sources, String[] targets) {
        // maps out: indexWord -> indexWord index (this is used later to retrieve source word)
        Map<Integer, Integer> table = new HashMap<>();
        for (int i=0; i<indices.length; i++) {
            // if a match is found in the original string, record it
            if (S.startsWith(sources[i], indices[i])) {
                table.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<S.length(); ) {
            if (table.containsKey(i)) {
                // if a replacement was recorded before
                sb.append(targets[table.get(i)]);
                i+=sources[table.get(i)].length();
            } else {
                // if no replacement happened at this index
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    @Override
    public void run() {
        String s = "abcd";
        int[] indices = {0,2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};

        String newString = findReplaceString(s, indices, sources, targets);
    }
}
