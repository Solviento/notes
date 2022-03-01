/*Design a logger system that receives a stream of messages along with their timestamps.
Each unique message should only be printed at most every 10 seconds (i.e. a message printed
at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
All messages will come in chronological order. Several messages may arrive at the same timestamp.
Implement the Logger class:
Logger() Initializes the logger object.
bool shouldPrintMessage(int timestamp, string message) Returns true if
the message should be printed in the given timestamp, otherwise returns false.

Example 1:
Input
["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage",
"shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
[[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
Output
[null, true, true, false, false, false, true]

Explanation
Logger logger = new Logger();
logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21*/

package com.practice.code.design;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class LoggerRateLimiter implements CodeRunner {
    @Override
    public void run() {
        LoggerRateLimiter loggerRateLimiter = new LoggerRateLimiter();
        boolean fa1 = loggerRateLimiter.shouldPrintMessage(1, "hey");
        boolean fa2 = loggerRateLimiter.shouldPrintMessage(10, "hey");
        boolean fa3 = loggerRateLimiter.shouldPrintMessage(2, "bye");
        boolean fa4 = loggerRateLimiter.shouldPrintMessage(12, "bye");
        System.out.println("Logger 1: " + fa1 + " Logger 2: " + fa2 + " Logger 3: " + fa3 + " Logger 4: " + fa4);
    }

    // key: keyWord value: timestamp
    private final HashMap<String, Integer> msgDict;

    /**
     * Initialize your data structure here.
     */
    // pojo with hashmap to store keyword and timestamp
    public LoggerRateLimiter() {
        msgDict = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {

        if (!this.msgDict.containsKey(message)) {
            this.msgDict.put(message, timestamp);
            return true;
        }

        Integer oldTimestamp = this.msgDict.get(message);
        if (timestamp - oldTimestamp >= 10) {
            this.msgDict.put(message, timestamp);
            return true;
        } else
            return false;
    }

}
