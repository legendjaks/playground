package com.jay.graph;

import java.util.*;

public class OrderCourses {

    public int[] order(int N, int[][] deps) {

        int[] indegree = new int[N];
        Map<Integer, List<Integer>> map = new HashMap<>();
        // [1, 0]
        for(var dep: deps){
            indegree[dep[0]]++;

            map.putIfAbsent(dep[1], new ArrayList<>());
            map.get(dep[1]).add(dep[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int index = 0;index< indegree.length;index++){
            if(indegree[index] == 0)
                queue.offer(index);
        }

        int[] res = new int[N];
        int pos = 0;
        int courses = 0;
        while(!queue.isEmpty()){

            var current = queue.poll();
            courses ++;

            if(indegree[current] == 0){
                res[pos++] = current;
            }

            var edges = map.getOrDefault(current, new ArrayList<>());
            for(var next: edges){
                if(--indegree[next] == 0)
                    queue.offer(next);
            }
        }

        return (courses == N)? res: new int[]{};
    }

    public static void main(String[] args) {
        OrderCourses ob = new OrderCourses();
        int[][] data = new int[][]{
                {1,2}, {2,0}, {3,1}, {3,2}, {4, 3}, {4, 1}
        };

        var res = ob.order(5, data);
        System.out.println();
        for(var c: res){
            System.out.print(c + " ");
        }
    }
}
