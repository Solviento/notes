import java.util.Set;
import java.util.HashSet;
class ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (Integer e : nums) {
      if (set.contains(e)) {
        return true;
      } else {
        set.add(e);
      }
    }
    return false;
  }
  public static void main(String[] args){
    int n = 3;
    int med = (int) Math.ceil(3/2.0);
    System.out.println(med);
  }
}