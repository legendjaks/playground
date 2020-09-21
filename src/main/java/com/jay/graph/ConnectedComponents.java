package com.jay.graph;

import java.util.HashSet;
import java.util.Set;

public class ConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        int[] ids = new int[n];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }

        for (int[] edge : edges) {
            union(edge[0], edge[1], ids);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i, ids));
        }

        return set.size();
    }

    private void union(int edge1, int edge2, int[] ids) {
        int parent1 = find(edge1, ids);
        int parent2 = find(edge2, ids);
        ids[parent1] = parent2;
    }

    private int find(int edge, int[] ids) {
        if (ids[edge] != edge) ids[edge] = find(ids[edge], ids);
        return ids[edge];
    }

    public static void main(String[] args) {
        ConnectedComponents ob = new ConnectedComponents();
        int[][] graph = {{0, 1}, {1, 2}, {3, 4}};
        int res = ob.countComponents(5, graph);
        System.out.println("res: " + res);
    }
}
