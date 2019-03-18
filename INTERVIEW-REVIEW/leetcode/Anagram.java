// Given two strings s and t , write a function to determine if t is an anagram of s.
// Input: s = "anagram", t = "nagaram"
// Output: true
import java.util.HashMap;
class Anagram {
  // another solution is to sort the strings, use two pointers, if pointers dont match, then false OR use .equals() 
  // sorting will require Time: O(nlogn) and Space: O(1)
  
  // by using a hashmap we can simply store the frequency of letters for the first string, the second string will then
  // match against the frequency values in the hashmap. If any frequency values are not equal to 0, then it is not an anagram
  // Time: O(n) Space: O(n)
  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    // can be used for unicode letters as well
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        int val = map.get(c);
        map.put(c, val + 1);
      } else {
        map.put(c, 1);
      }
    }
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      if (map.containsKey(c)) {
        int val = map.get(c);
        map.put(c, val - 1);
      } else {
        map.put(c, 1);
      }
    }
    for (Character key : map.keySet()) {
      int val = map.get(key);
      if (val != 0) {
        return false;
      }
    }
    return true;
  }
}