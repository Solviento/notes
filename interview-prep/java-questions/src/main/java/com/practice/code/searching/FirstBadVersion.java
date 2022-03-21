/*You are a product manager and currently leading a team to develop a new product.
Unfortunately, the latest version of your product fails the quality check. Since
each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
You are given an API bool isBadVersion(version) which returns whether
version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example 1:
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.

Example 2:
Input: n = 1, bad = 1
Output: 1
 */
package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class FirstBadVersion implements CodeRunner {
    @Override
    public void run() {

    }

    // some leetcode API method
    boolean isBadVersion(int version) {
        return true;
    }

    // 1 2 3 4 5 6 7 8 9
    // F F F T T T T T T
    // use binary search to find first failing build number
    // t: logn s: 1
    public int firstBadVersion(int n) {
        int startIndex = 1;
        int endIndex = n;
        while (startIndex < endIndex) {
            int mid = startIndex + (endIndex - startIndex) / 2;
            if (isBadVersion(mid)) {
                endIndex = mid;
            } else {
                startIndex = mid + 1;
            }
        }
        return startIndex;
    }
}
