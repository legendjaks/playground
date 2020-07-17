package com.jay.graph;

import java.util.*;

public class ReOrderRoutesToZero {

    public int minReorder(int n, int[][] connections) {

        int res = 0;

        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> adjacents = new HashMap<>();

        for (var connection : connections) {
            var from = connection[0];
            var to = connection[1];

            var edges = graph.getOrDefault(from, new HashSet<>());
            edges.add(to);
            graph.put(from, edges);

            var fromAdjacency = adjacents.getOrDefault(from, new HashSet<>());
            fromAdjacency.add(to);
            adjacents.put(from, fromAdjacency);

            var toAdjacency = adjacents.getOrDefault(to, new HashSet<>());
            toAdjacency.add(from);
            adjacents.put(to, toAdjacency);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(0);

        while (!queue.isEmpty()) {

            var current = queue.poll();
            visited.add(current);

            var adjacencies = adjacents.get(current);
            for (var node : adjacencies) {
                if (visited.contains(node)) continue;

                queue.offer(node);

                if (graph.containsKey(node)) {
                    var edges = graph.get(node);
                    if (!edges.contains(current))
                        res++;
                } else
                    res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ReOrderRoutesToZero ob = new ReOrderRoutesToZero();
        //int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}};
        int[][] connections = {{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        int res = ob.minReorder(6, connections);
        System.out.println("res: " + res);
    }
}
