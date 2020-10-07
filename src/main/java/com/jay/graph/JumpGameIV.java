package com.jay.graph;

import java.util.*;

public class JumpGameIV {

    public int minJumps(int[] arr) {

        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        int steps = 0;
        while(!queue.isEmpty()){

            int size = queue.size();
            for(int i = 0; i < size; i++){
                int current = queue.poll();
                System.out.println(current);

                if(current == n - 1){
                    return steps;
                }

                if(map.containsKey(arr[current])) {
                    for (int edge : map.get(arr[current])) {
                        if (!visited.contains(edge)) {
                            queue.offer(edge);
                            visited.add(edge);
                        }
                    }
                }
                map.remove(arr[current]);

                if(current > 0 && !visited.contains(current - 1)){
                    queue.offer(current - 1);
                    visited.add(current - 1);
                }

                if(!visited.contains(current + 1)){
                    queue.offer(current + 1);
                    visited.add(current + 1);
                }
            }

            steps++;
        }

        return steps;
    }

    public static void main(String[] args) {
        JumpGameIV ob = new JumpGameIV();
        int[] data = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        int res = ob.minJumps(data);
        System.out.println("res: " + res);
    }
}
