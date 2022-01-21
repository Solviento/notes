// design a DS SpecialStadck that supports all stack operations like push, pop, isempt, isfull
// andthe method getMin() which should return min element in DS
// should be O(1) and can only use standard stack data structure, no arrays or list

package com.practice.code.problems;

import java.util.Stack;

public class SpecialStack extends Stack<Integer> {

    Stack<Integer> min = new Stack<>();

    public void push(int x) {
        if (isEmpty() == true) {
            min.push(x);
            super.push(x);
        } else {
            super.push(x);
            int y = min.pop();
            min.push(x);
            if (y < x) {
                min.push(y);
            } else {
                min.push(x);
            }
        }
    }

    public Integer pop() {
        int x = super.pop();
        min.pop();
        return x;
    }

    public Integer getMin() {
        int x = min.pop();
        min.push(x);
        return x;
    }

    public static void main(String... args) {
        SpecialStack s = new SpecialStack();
        s.push(3);
        s.push(1);
        s.push(5);
        s.push(2);
        s.push(10);
        System.out.println(s.getMin());
    }
}