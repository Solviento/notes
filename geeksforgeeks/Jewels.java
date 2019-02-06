import java.util.HashMap;

class Jewels {
  public static int numJewelsInStones(String J, String S) {
    HashMap<Character, Integer> jewels = new HashMap<>();
    for (int i = 0; i < J.length(); i++) {
      jewels.put(J.charAt(i), 1);
    }
    int unique = 0;
    for (int j = 0; j < S.length(); j++) {
      if (jewels.containsKey(S.charAt(j))) {
        unique += 1;
      }
    }
    return unique;
  }
  public static void main(String... args){
    String J = "aA";
    String S = "aAAbbb";
    int j = numJewelsInStones(J, S);
    System.out.println(j);
  }
}