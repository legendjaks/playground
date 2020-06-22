package com.jay.graph;

import java.util.*;

public class InformEmployees {

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        Map<Integer, List<Integer>> employees = new HashMap<>();
        for(int index = 0; index < manager.length; index++){
            int mgr = manager[index];
            employees.computeIfAbsent(mgr, f -> new ArrayList<>()).add(index);
        }

        return dfs(headID, employees, informTime);
    }

    public int dfs(int current, Map<Integer, List<Integer>> employees, int[] informTime){

        int max = 0;
        List<Integer> edges = employees.get(current);
        if(edges != null) {
            for (int edge : edges) {
                int elapsed = dfs(edge, employees, informTime);
                max = Math.max(max, elapsed);
            }
        }

        return max + informTime[current];
    }

    public static void main(String[] args) {
        InformEmployees ob = new InformEmployees();
        int res = ob.numOfMinutes(11, 4, new int[]{5,9,6,10,-1,8,9,1,9,3,4}, new int[]{0,213,0,253,686,170,975,0,261,309,337});
        System.out.println("res: " + res);
    }
}
