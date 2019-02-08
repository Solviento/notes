class Anagram {
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