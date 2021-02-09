package com.practice.code.runner;

import com.practice.code.problems.CodeRunner;

import java.lang.reflect.Constructor;

public class LeetcodeRunner {

    public static void main(String[] args) throws Exception {
        System.out.println("This is a basic runner for leetcode/algo problems.");
        String className = "Anagram";//args[0];
        String packagePath = "com.practice.code.problems";
        // debug below, not taking input
        Class<?> clazz = Class.forName("THISISACLASS");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        ((CodeRunner) instance).run();
    }
}
