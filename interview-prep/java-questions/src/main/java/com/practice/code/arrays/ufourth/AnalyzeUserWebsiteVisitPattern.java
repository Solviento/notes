/*You are given two string arrays username and website and an
integer array timestamp. All the given arrays are of the same
length and the tuple [username[i], website[i], timestamp[i]]
indicates that the user username[i] visited the
website website[i] at time timestamp[i].

A pattern is a list of three websites (not necessarily distinct).

For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"],
and ["luffy", "luffy", "luffy"] are all patterns.
The score of a pattern is the number of users that visited all the
websites in the pattern in the same order they appeared in the pattern.

For example, if the pattern is ["home", "away", "love"], the score
is the number of users x such that x visited "home" then
visited "away" and visited "love" after that.
Similarly, if the pattern is ["leetcode", "love", "leetcode"], the
score is the number of users x such that x visited "leetcode"
then visited "love" and visited "leetcode" one more time after that.
Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the
number of users x such that x visited "luffy" three different times at different timestamps.
Return the pattern with the largest score. If there is more than one
pattern with the same largest score, return the lexicographically smallest such pattern.

Example 1:
Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: The tuples in this example are:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],
["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
The pattern ("home", "about", "career") has score 2 (joe and mary).
The pattern ("home", "cart", "maps") has score 1 (james).
The pattern ("home", "cart", "home") has score 1 (james).
The pattern ("home", "maps", "home") has score 1 (james).
The pattern ("cart", "maps", "home") has score 1 (james).
The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).

Example 2:
Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
Output: ["a","b","a"]*/
package com.practice.code.arrays.ufourth;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern implements CodeRunner {
    @Override
    public void run() {

    }


    class Event {
        private String user;
        private int timestamp;
        private String web;

        Event(String user, int timestamp, String web) {
            this.user = user;
            this.timestamp = timestamp;
            this.web = web;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            events.add(new Event(username[i], timestamp[i], website[i]));
        }
        Comparator<Event> comp = (Event a, Event b) -> a.timestamp - b.timestamp;
        Collections.sort(events, comp);
        Map<String, List<String>> userToWeb = new HashMap<>();
        for (Event e : events) {
            if (!userToWeb.containsKey(e.user)) userToWeb.put(e.user, new ArrayList<>());
            userToWeb.get(e.user).add(e.web);
        }

        Map<String, Integer> webTofreq = new HashMap<>();
        for (String user : userToWeb.keySet()) {
            Set<String> seqs = get3Seq(userToWeb.get(user));
            for (String seq : seqs) webTofreq.put(seq, webTofreq.getOrDefault(seq, 0) + 1);
        }
        int max = 0;
        String ans = "";
        for (String seq : webTofreq.keySet()) {
            int freq = webTofreq.get(seq);
            if (freq < max) continue;
            if (freq > max) ans = seq;
            if (max == freq) ans = (seq.compareTo(ans) < 0) ? seq : ans;
            max = freq;
        }
        return Arrays.asList(ans.split("#"));
    }

    private Set<String> get3Seq(List<String> webs) {
        Set<String> res = new HashSet<>();
        if (webs.size() < 3) return res;
        dfs(webs, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(List<String> webs, int idx, List<String> tmp, Set<String> res) {
        if (tmp.size() == 3) {
            String seq = tmp.get(0) + "#" + tmp.get(1) + "#" + tmp.get(2);
            if (!res.contains(seq)) res.add(seq);
            return;
        }
        for (int i = idx; i < webs.size(); i++) {
            tmp.add(webs.get(i));
            dfs(webs, i + 1, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }
}
