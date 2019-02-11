// Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails? 
// Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
// Output: 2
// Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
// Time: O(c) Space: O(c) where c is the total content of emails
import java.util.HashSet;

class Solution {
  public int numUniqueEmails(String[] emails) {
    Set<String> e = new HashSet<>();
    for (int i = 0; i < emails.length; i++) {
      String[] parts = emails[i].split("@");
      String[] local = parts[0].split("\\+");
      String localFormatted = local[0].replace(".", "");
      e.add(localFormatted + "@" + parts[1]);
    }
    return e.size();
  }
}