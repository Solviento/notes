class isPalindrome{
  // slow method using built in methods
  public static boolean isPalindromeI(String s) {
    s = s.toLowerCase();
    s = s.replaceAll("[^\\w]","");
    int left = 0, right = s.length() - 1;
    while (left < right) {
      char l = s.charAt(left);
      char r = s.charAt(right);
      if (l != r) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
  // faster, no built ins also using two pointers
  public static boolean isPalindrome_(String s){
    int left = 0, right = s.length() - 1;
    while (left < right) {
      char l = s.charAt(left);
      char r = s.charAt(right);
      if(!Character.isLetterOrDigit(l)){
        left++;
        continue;
      }
      else if(!Character.isLetterOrDigit(r)){
        right--;
        continue;
      }
      else if (Character.toUpperCase(l) != Character.toUpperCase(r)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
  // Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
  // input is only lowercase abc AND input can be at MOST off by one letter
  public static boolean validPalindromeII(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
      char l = s.charAt(left);
      char r = s.charAt(right);
      if (l != r) {
        // break string into two parts
        boolean left_ = subPalindrome(s, left + 1, right);
        boolean right_ = subPalindrome(s, left, right - 1);
        return left_ || right_;
      }
      left++;
      right--;
    }
    return true;
  }
  // helper method
  static boolean subPalindrome(String s, int left, int right) {
    while (left < right) {
      char l = s.charAt(left);
      char r = s.charAt(right);
      if (l != r) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }
  public static void main(String... args){
    String s = "0P";
    boolean b = isPalindrome_((s));
    System.out.println(b);
  }
}