package com.a11logic.fbhk2020;

import java.util.*;

public class RunningOnFumes2 {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        for (int i = 1; i <= numTests; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int a = scanner.nextInt() - 1;
            int b = scanner.nextInt() - 1;
            ArrayList<Node> nodes = new ArrayList(n);
            LinkedList<Integer> adj[] = new LinkedList[n];
            for (int j = 0; j < n; j++) {
                adj[j] = new LinkedList<Integer>();
            }
            for (int j = 0; j < n; j++) {
                int p = scanner.nextInt() - 1;
                long c = scanner.nextInt();
                if(c == 0)
                    c = Long.MAX_VALUE;
                Node node = new Node(c, j);
                nodes.add(node);
                if(p != -1) {
                    adj[j].add(p);
                    adj[p].add(j);
                }

            }
            runTestCase(i, m, a, b, nodes, adj);
        }
    }

    private LinkedList<Node> bfs(int a, int b,
                     ArrayList<Node> nodes, LinkedList<Integer> adj[]) {
        boolean[] visited = new boolean[nodes.size()];
        LinkedList<Node> flattenedNodes = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        //initialize starting node
        visited[a] = true;
        nodes.get(a).distance = 0;
        flattenedNodes.add(nodes.get(a));
        queue.add(a);

        while(queue.size() > 0) {
            int i = queue.poll();

            //if the node is b, we can stop
            if(i == b)
                break;

            //process adjacent vertices
            for(int n : adj[i]) {
                if (!visited[n])
                {
                    visited[n] = true;
                    nodes.get(n).distance = nodes.get(i).distance + 1;
                    flattenedNodes.add(nodes.get(n));
                    queue.add(n);
                }
            }
        }
        return flattenedNodes;

    }

    private void runTestCase(int caseNum, int m, int a, int b,
                             ArrayList<Node> nodes, LinkedList<Integer> adj[]) {
        //bfs starting from node a, stop at node b
        LinkedList<Node> flattenedNodes = bfs(a, b, nodes, adj);

        //cost of reaching up to M distant cities from city a is 0
        LinkedList<Node> costs = new LinkedList<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
//        for(int i=0; i <= m && i < c.length; i++) {
        while(true) {
            Node node = flattenedNodes.poll();
            if(node == null)
                break;
            //if gone past m distance, insert it back and break out
            if(node.distance > m) {
                flattenedNodes.addFirst(node);
                break;
            }
            costs.add(node);
            long cost = node.c;
            if(node.distance == 0)
                cost = 0;
            node.cost = cost;
            //a sorted data structure to maintain all costs
            //to get min efficiently
            //remove the first element as window slides
            //A Priority Queue should work
            pq.add(cost);
        }


        //for next cities cost is min of last m distance cities (cost[i] + c[i])
        for(int i = m + 1; i < nodes.get(b).distance; i++) {
            //move the sliding window
            while (true) {
                Node costNode = costs.poll();
                if (costNode == null)
                    break;
                if (costNode.distance + m >= i) {
                    costs.addFirst(costNode);
                    break;
                }

                pq.remove(costNode.cost);
            }
            //update the cost of distance i nodes
            while (true) {
                Node flattenedNode = flattenedNodes.poll();
                if (flattenedNode == null)
                    break;
                if (flattenedNode.distance > i) {
                    flattenedNodes.addFirst(flattenedNode);
                    break;
                }
                long cost = flattenedNode.c + pq.peek();
                if (cost < 0)
                    cost = Long.MAX_VALUE;
                flattenedNode.cost = cost;
                costs.add(flattenedNode);
                pq.add(cost);
            }
        }
        //move the sliding window
        while (true) {
            Node costNode = costs.poll();
            if (costNode == null)
                break;
            if (costNode.distance + m >= nodes.get(b).distance) {
                costs.addFirst(costNode);
                break;
            }
            pq.remove(costNode.cost);
        }

        //determine the cost of last city without regard to refueling, as it is the final destination.
        Long result = pq.peek();
        if (result == null || result == Long.MAX_VALUE)
            result = -1l;
        System.out.println("Case #" + caseNum + ": " + result);
    }

    public static void main(String[] args) {
        RunningOnFumes2 s = new RunningOnFumes2();
        s.runTests();
    }

}

    class Node {
        int distance;
        long c;
        long cost;
        int i;

        public Node(long c, int i) {
            this.c = c;
            this.i = i;
        }
    }
