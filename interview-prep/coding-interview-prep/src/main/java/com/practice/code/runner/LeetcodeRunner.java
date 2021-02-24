package com.practice.code.runner;

import com.practice.code.problems.CodeRunner;

public class LeetcodeRunner {

    public static void main(String[] args) {
        System.out.println("This is a basic runner for leetcode/algo problems.");
        String className = "RunLengthEncoding";//args[0];
        String packagePath = "com.practice.code.problems.";
        try {
            Class clazz = Class.forName(packagePath + className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            ((CodeRunner) instance).run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
