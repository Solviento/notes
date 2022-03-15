package com.practice.code.runner;

import com.practice.code.arrays.first.*;
import com.practice.code.arrays.second.*;
import com.practice.code.arrays.third.*;
import com.practice.code.other.*;

import java.util.ArrayList;

public class LeetcodeRunner {

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("This is a basic runner for leetcode/algo problems.");
        runPracticeClass();
//        runAllPracticeClasses(practiceClasses());
    }

    private static void runPracticeClass() {
        TesterTips runMe = new TesterTips();
//        FourSumII runMe = new FourSumII();
        runMe.run();
        System.out.println("----------------------------------------------------------------------------------\n");
    }

    public static ArrayList<String> practiceClasses() {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("sorting.FindFirstAndLastPositionOfElementInSortedArray");
        return classes;
    }

    public static void runAllPracticeClasses(ArrayList<String> classes) {
        String packagePath = "com.practice.code.";
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
