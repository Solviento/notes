package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class Implement3StacksUsingSingleArray implements CodeRunner {
    @Override
    public void run() {

    }

    static class newStack{
        int arr[];
        int top[];
        int next[];

        int n, k;
        int free;

        newStack(int k1, int n1){

            k = k1;
            n = n1;
            arr = new int[n];
            top = new int[k];
            next = new int[n];

            for(int i = 0; i < k; i++)
                top[i] = -1;

            free = 0;
            for(int i = 0; i < n - 1; i++)
                next[i] = i + 1;
            next[n - 1] = -1;
        }

        boolean isFull(){
            return (free == -1);
        }

        void push(int item, int sn){

            if(isFull()){
                System.out.println("Stack Overflow");
                return;
            }

            int i = free;

            free = next[i];

            next[i] = top[sn];
            top[sn] = i;

            arr[i] = item;
        }

        int pop(int sn){

            if(isEmpty(sn)){
                System.out.println("Stack Underflow");
                return Integer.MAX_VALUE;
            }

            int i = top[sn];

            top[sn] = next[i];

            next[i] = free;
            free = i;

            return arr[i];
        }

        boolean isEmpty(int sn){
            return (top[sn] == -1);
        }

    }
}
