package com.jay.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class AllPathSrcDest {

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {

        if (edges.length == 0 && source == destination) return true;

        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        for (var edge : edges) {
            var from = edge[0];
            var to = edge[1];

            var node = graph.getOrDefault(from, new HashSet<>());
            node.add(to);
            graph.put(from, node);
        }

        var destEdges = graph.get(destination);
        if (destEdges != null) return false;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        HashSet<Integer> visiting = new HashSet<>();

        while (!queue.isEmpty()) {

            var current = queue.poll();
            visiting.add(current);

            var adjacents = graph.get(current);
            if (adjacents == null) return false;

            if (adjacents.contains(current))
                return false;

            for (var node : adjacents) {
                if (node == destination) {
                    visiting.clear();
                    continue;
                }

                if (visiting.contains(node)) return false;
                queue.offer(node);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        AllPathSrcDest ob = new AllPathSrcDest();
        int[][] edges = {};
        /* {{0,1},{0,3},{1,2},{2,1}}
        {{0,1},{0,2},{1,3},{2,3}}
        {{0,1},{1,1},{1,2}}
        {{0,1},{1,1}}
        {{0,1},{0,3},{1,2},{2,1}}
        {{0,2},{0,1},{2,3},{1,3},{2,1}};
        */
        boolean res = ob.leadsToDestination(1, edges, 0, 0);
        System.out.println("res: " + res);
    }
}
