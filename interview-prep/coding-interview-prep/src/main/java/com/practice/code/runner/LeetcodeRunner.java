package com.practice.code.runner;

import java.util.ArrayList;

public class LeetcodeRunner {

    public static void main(String[] args) {
        System.out.println("This is a basic runner for leetcode/algo problems.");
<<<<<<< HEAD
        String className = "RunLengthEncoding";//args[0];
=======
        runAllPracticeClasses(practiceClasses());
    }

    public static ArrayList<String> practiceClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Anagram");
        classes.add("BitwiseOperations");
        classes.add("BuySellStock");
        classes.add("ClimbingStairs");
        classes.add("ColumnNumber");
        classes.add("ColumnTitle");
        classes.add("ContainsDuplicate");
        classes.add("CountPrimes");
        classes.add("FirstLastPositionInteger");
        classes.add("FirstUniqueChar");
        classes.add("FizzBuzz");
        classes.add("FourSumII");
        classes.add("GenerateParenthesis");
        classes.add("HappyNumber");
        return classes;
    }

    public static void runAllPracticeClasses(ArrayList<String> classes) {
>>>>>>> e8f20c8a8a0b5f669a30487c394dd78772bad29c
        String packagePath = "com.practice.code.problems.";
        System.out.println("----------------------------------------------------------------------------------\n");
        for (String className : classes) {
            try {
                Class<?> clazz = Class.forName(packagePath + className);
                Object instance = clazz.getDeclaredConstructor().newInstance();
                ((CodeRunner) instance).run();

            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("-----");
        }
        System.out.println("\n----------------------------------------------------------------------------------");
    }
}
