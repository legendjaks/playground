package com.jay.graph;

import java.util.*;

public class JumpGame4 {

    public class Item {
        int index;
        int cost;

        public Item(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public int minJumps(int[] arr) {

        int n = arr.length;
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int index = 0; index < n; index++) {
            map.putIfAbsent(arr[index], new HashSet<>());
            map.get(arr[index]).add(index);
        }

        Queue<Item> queue = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1.cost));
        queue.offer(new Item(0, 0));

        while (!queue.isEmpty()) {

            var current = queue.poll();
            //System.out.println("Visited: " + arr[current.index] + " - " + current.cost);

            if (current.index == n - 1) {
                return current.cost;
            }

            Set<Integer> next = new HashSet<>();
            next.add(current.index - 1);
            next.add(current.index + 1);

            next.addAll(map.get(arr[current.index]));

            for (var edge : next) {
                if (edge == current.index || edge < 0 || edge >= n) continue;

                if (cost[edge] > 1 + current.cost) {
                    cost[edge] = 1 + current.cost;
                    queue.offer(new Item(edge, 1 + current.cost));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        JumpGame4 ob = new JumpGame4();
        //int[] data = {100, 23, -23, 404, 100, 23, 23, 23, 3, 404};
        int[] data = {51, 64, -15, 58, 98, 31, 48, 72, 78, -63, 92, -5, 64, -64, 51, -48, 64, 48, -76, -86, -5, -64, -86, -47, 92, -41, 58, 72, 31, 78, -15, -76, 72, -5, -97, 98, 78, -97, -41, -47, -86, -97, 78, -97, 58, -41, 72, -41, 72, -25, -76, 51, -86, -65, 78, -63, 72, -15, 48, -15, -63, -65, 31, -41, 95, 51, -47, 51, -41, -76, 58, -81, -41, 88, 58, -81, 88, 88, -47, -48, 72, -25, -86, -41, -86, -64, -15, -63};
        int res = ob.minJumps(data);
        System.out.println("res: " + res);
    }
}
