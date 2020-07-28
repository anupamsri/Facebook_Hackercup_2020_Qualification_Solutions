package com.a11logic.fbhk2020;

import java.util.*;

public class RunningOnFumes1 {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        for (int i = 1; i <= numTests; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            long[] c = new long[n];
            for (int j = 0; j < n; j++) {
                c[j] = scanner.nextInt();
                if(c[j] == 0)
                    c[j] = Long.MAX_VALUE;
            }
            runTestCase(i, m, c);
        }
    }

    private void runTestCase(int caseNum,
                             int m, long[] c) {
        //cost of reaching first M cities from city 0 is 0
        LinkedList<Long> costs = new LinkedList<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0; i <= m && i < c.length; i++) {
            long cost = c[i];
            if(i == 0)
                cost = 0;
            costs.add(cost);
            //a sorted data structure to maintain all costs
            //to get min efficiently
            //remove the first element as window slides
            //A Priority Queue should work
            pq.add(cost);
        }


        //for next cities cost is min of last m cities (cost[i] + c[i])
        for(int i = m + 1; i < c.length -1; i++) {
            //move the sliding window
            pq.remove(costs.pollFirst());
            long cost = c[i] + pq.peek();
            if(cost < 0)
                cost = Long.MAX_VALUE;
            costs.add(cost);
            pq.add(cost);
        }
        //move the sliding window
        if(m < c.length - 1)
            pq.remove(costs.pollFirst());
        //determine the cost of last city without regard to refueling, as it is the final destination.
        long result = pq.peek();
        if(result == Long.MAX_VALUE)
            result = -1;
        System.out.println("Case #" + caseNum + ": " + result);
    }

    public static void main(String[] args) {
        RunningOnFumes1 s = new RunningOnFumes1();
        s.runTests();
    }
}
