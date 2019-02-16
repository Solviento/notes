class StrStr {
  // KMP is Time: O(m+n) Space: O(n)
  static int strStr(String haystack, String needle) {
    // first pre process string, find Longest Prefix Suffix (LPS)
    int[] lps = preProcess(needle);
    // now iterate through haystack and use KMP algorithm skip/checks
    return 0;
  }
  static int[] preProcess(String n){
    int[] lps = new int[n.length()];
    lps[0] = 0;
    int i = 1;
    // length of previous longest prefix suffix
    int j = 0;
    while(i < n.length()){
      if (n.charAt(i) == n.charAt(j)){
        j++;
        lps[i] = j;
        i++;
      }
      else{
        // tricky case: needle="AACA", need to add condition to reset lcs
        // possible pre-suffixes: [AA], [A  A], first one is just AA (two chars), second one is A  A (four chars)
        if(j!=0){
          j = lps[j-1];
        }
        else{
          lps[i] = j;
          i++;
        }
      }
    }
    return lps;
  }
  public static void main(String... args){
    String hay = "blakinpockiyabkinkmkinkibiko";
    String n = "AACA";
    int index = strStr(hay, n);
  }
}
