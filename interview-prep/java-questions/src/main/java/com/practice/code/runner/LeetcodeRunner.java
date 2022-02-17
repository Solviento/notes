package com.practice.code.runner;

import com.practice.code.arraysandstrings.Anagram;
import com.practice.code.arraysandstrings.BackspaceStringCompare;
import com.practice.code.backtracking.KnightsTour;
import com.practice.code.dynamicprogramming.BuySellStock;
import com.practice.code.searching.BreadthFirstSearchIterativeTree;

import java.util.ArrayList;

public class LeetcodeRunner {

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("This is a basic runner for leetcode/algo problems.");
        runPracticeClass();
//        runAllPracticeClasses(practiceClasses());
    }

    private static void runPracticeClass() {

//        Anagram anagram = new Anagram();
//        anagram.run();
//        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();
//        backspaceStringCompare.run();
//        KnightsTour knightsTour = new KnightsTour();
//        knightsTour.run();
//        BuySellStock buySellStock = new BuySellStock();
//        buySellStock.run();
        TesterTips testerTips = new TesterTips();
        testerTips.run();
//        BreadthFirstSearchIterativeTree breadthFirstSearchIterativeTree = new BreadthFirstSearchIterativeTree();
//        breadthFirstSearchIterativeTree.run();
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
