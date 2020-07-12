package com.jay.graph;

import java.util.*;

public class JumpGame {

    public class Item {
        int index;
        int level;

        public Item(int index, int level){
            this.index = index;
            this.level = level;
        }
    }

    public int minJumps(int[] arr) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int index = 0; index < arr.length; index++){
            int d = arr[index];
            if(!graph.containsKey(d)){
                graph.put(d, new ArrayList<>());
            }

            graph.get(d).add(index);
        }

        Set<Integer> visited = new HashSet<>();

        Queue<Item> queue = new LinkedList<>();
        queue.offer(new Item(0, 0));

        while(!queue.isEmpty()){

            var current = queue.poll();
            visited.add(current.index);
            if(current.index == arr.length - 1){
                return current.level;
            }

            if(current.index + 1 < arr.length && !visited.contains(current.index + 1)){
                if(current.index + 1 == arr.length - 1){
                    return 1 + current.level;
                }

                queue.offer(new Item(current.index + 1, 1 + current.level));
            }

            if(current.index - 1 >= 0 && !visited.contains(current.index - 1)){
                queue.offer(new Item(current.index - 1, 1 + current.level));
            }

            var edges = graph.get(arr[current.index]);
            if(edges == null) continue;

            for(int index = edges.size() - 1; index >=0; index--){

                if(edges.get(index) == arr.length - 1){
                    return 1 + current.level;
                }

                if(!visited.contains(edges.get(index))){
                    queue.offer(new Item(edges.get(index), 1 + current.level));
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        JumpGame ob = new JumpGame();
        int[] data = new int[]{7};
        int res = ob.minJumps(data);
        System.out.println("res: " + res);
    }
}
