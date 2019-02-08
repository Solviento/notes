/*
Write a function that reverses a string. The input string is given as an array of characters char[].
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
You may assume all the characters consist of printable ascii characters.
*/
class reverseString {
  static void reverseString(char[] s) {
    int left = 0, right = s.length - 1;
    while (left < right) {
      char tmp = s[right];
      s[right] = s[left];
      s[left] = tmp;
      left++;
      right--;
    }
  }
  public static void main(String... args){
    char[] c = new char[]{'H', 'a', 'p', 'p', 'y'};
    reverseString(c);
  }
}