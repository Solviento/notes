package com.practice.code.runner;

import java.util.*;

public class TesterTips implements CodeRunner {
    @Override
    public void run() {

        // Priority Queue
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("Geeks");
        priorityQueue.add("For");
        priorityQueue.add("Geeks");
        priorityQueue.remove();
        Iterator<String> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        // priority queue uses min heap as default
        PriorityQueue<Integer> priorityQueueInt = new PriorityQueue<>();
        priorityQueueInt.add(12);
        priorityQueueInt.add(18);
        priorityQueueInt.add(100);
        priorityQueueInt.add(1);
        System.out.println("\nBefore remove(): " + priorityQueueInt);
        priorityQueueInt.remove();
        System.out.println("After remove(): " + priorityQueueInt);
        System.out.println("poll() returns: " + priorityQueueInt.poll());
        System.out.println("After poll(): " + priorityQueueInt);

        // priority queue using max heap
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        };
        // comparator using lambdas (use this cleaner approach)
        Comparator<Integer> cmpLambda = (x, y) -> y - x;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(cmp);

        /* MAPS */

        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        // insertion order is not maintained in HashMap, but it is in LinkedHashMap
        linkedHashMap.put(10, "abc");
        linkedHashMap.put(3, "xyz");
        linkedHashMap.put(1, "abc");
        System.out.println("Mappings of LinkedHashMap: " + linkedHashMap);

        // Hashmap Functionality
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(10, "abc");
        hashMap.put(3, "xyz");
        hashMap.put(1, "abc");
        System.out.println("Mappings of HashMap: " + hashMap);

        // Iterating through a map (linked/hashmap)
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println("Mapping entry: " + entry);
        }

        for (Integer key : hashMap.keySet()) {
            System.out.print("key: " + key +" ");
        }

        for (String value : hashMap.values()) {
            System.out.print("value: " + value + " ");
        }

        // HashMap Merge function
        hashMap.put(100, "init");
        // merge using lambda function
        hashMap.merge(100, "mergedValue", (x, y) -> x + y);

        // merged value will be "initmergedValue"
        System.out.println("New merged value in map: " + hashMap.get(100));

        // additional methods for map
        hashMap.get(10);
        String ret = hashMap.getOrDefault(4, "defaultValue");
        System.out.println("GetOrDefault Mapping value: " + ret);

        /* ARRAYS */

        //
        int[] array = new int[]{1, 2, 3};
        int[] cloneArr = array.clone();

        /* LIST */

        // convert elements into array list
        List<Integer> arrayList = Arrays.asList(10, 12, 14);

        /* STRINGS */

        // print stack using String.valueOf()
        Stack<Character> stackChars = new Stack<>();
        stackChars.push('P');
        stackChars.push('O');
        stackChars.push('P');
        // "[P, O, P]"
        String stackString = String.valueOf(stackChars);
        System.out.println("String value of stack: " + stackString);

        // splitting a string by formatting and delimiting
        String splitMe = "This is a long sentence. Defined by periods. And commas.";
        // IMPORTANT, REPLACEALL USES REGEX SO TO REPLACE PERIODS, COMMAS, ETC. MUST PREFIX WITH \\
        splitMe = splitMe.replaceAll("\\.", "");
        splitMe = splitMe.toLowerCase();
        System.out.println("Formatted string: " + splitMe);
        String[] stringTokens = splitMe.split(" ");
        for (String s : stringTokens) {
            System.out.print(s + " | ");
        }

        // proper way of evaluating string equality
        String compareMe = "this is a long sentence defined by periods and commas";
        boolean comparison = splitMe.equals(compareMe);
        System.out.println("\nComparison : " + comparison);

        // converting string to char array
        char[] chars = compareMe.toCharArray();

        // extracting substring
        String subStringMe = "subThisSandwich";
        // will extract from index to the end of string
        String sub1 = subStringMe.substring(3);
        // extract between indexes
        String sub2 = subStringMe.substring(3, 7);
        System.out.println("substring(3): " + sub1 + " substring(3, 7): " + sub2);

        /* QUEUES */

        // add, peek, poll
        Queue<String> queueStr = new LinkedList<>();
        queueStr.add("added to queue");
        queueStr.add("seconded");
        String peekStr = queueStr.peek();
        String pollStr = queueStr.poll();
        System.out.println("Queue peek: " + peekStr + " Queue poll: " + pollStr);

        /* STACK */

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        int popped1 = stack.pop();
        int peek1 = stack.peek();
        boolean emptyStack = stack.isEmpty();
        System.out.println("Stack popped 1 : " + popped1 + ", stack peek 1 : " + peek1 + ", isEmpty: " + emptyStack);

        // Java division operators
        int small = 9;
        int big = 18;
        int ten = 10;
        int div1 = small / ten;
        int div2 = small % ten;
        int div3 = big / ten;
        int div4 = big % ten;
        System.out.println("9 / 10 = " + div1 + " | 9 % 10 = " + div2 + " | 18 / 10 = " + div3 + " | 18 % 10 = " + div4);

        // Random
        Random rand = new Random();
        int numrand = rand.nextInt(100);
        System.out.println("\nRandom number from 0 - 100: " + numrand);

        /* COLLECTIONS */
        Set<String> bannedWords = new HashSet<>();
        String[] banned = {"bann", "morebanned"};
        Collections.addAll(bannedWords, banned);

        // addAll for Lists
        List<String> bannedList = new ArrayList<>();
        List<String> moreBan = new ArrayList<>();
        moreBan.add("banme");
        bannedList.addAll(moreBan);

        /* MATH */
        int lesser = 100;
        int greater = 140;
        int pow = 2;
        int max = Math.max(lesser, greater);
        double sqrt = Math.sqrt(lesser);
        double exp = Math.pow(lesser, pow);
    }
}
