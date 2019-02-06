class isPalindrome{
  // slow method using built in methods
  public static boolean isPalindrome(String s) {
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
  public static void main(String... args){
    String s = "0P";
    boolean b = isPalindrome_((s));
    System.out.println(b);
  }
}