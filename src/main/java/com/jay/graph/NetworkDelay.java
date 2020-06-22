package com.jay.graph;

import java.util.*;

public class NetworkDelay {

    public class Node {
        int key;
        List<Edge> edges;

        public Node(int key){
            this.key = key;
            this.edges = new ArrayList<>();
        }
    }

    public class Edge {
        Node node;
        int weight;

        public Edge(Node node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {

        Map<Integer, Node> nodes = new HashMap<>();

        for(int[] time: times){
            int key = time[0];
            int end = time[1];
            int weight = time[2];

            if(!nodes.containsKey(key)){
                nodes.put(key, new Node(key));
            }

            if(!nodes.containsKey(end)){
                nodes.put(end, new Node(end));
            }

            Node source = nodes.get(key);
            Node dest = nodes.get(end);
            source.edges.add(new Edge(dest, weight));
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for(int i = 1; i<=N; i++)
            dist.put(i, Integer.MAX_VALUE);

        dist.put(K, 0);

        boolean[] visited = new boolean[N+1];

        while(true){

            int candidateIndex = -1;
            int distance = Integer.MAX_VALUE;

            for(int index=1;index<=N;index++){

                if(!visited[index] && dist.get(index) < distance){
                    candidateIndex = index;
                    distance = dist.get(index);
                }
            }

            if(candidateIndex == -1) break;

            visited[candidateIndex] = true;

            Node node = nodes.get(candidateIndex);
            int currentDistance = dist.get(candidateIndex);
            for(Edge edge: node.edges){
                dist.put(edge.node.key, Math.min(dist.get(edge.node.key), currentDistance + edge.weight));
            }
        }

        int elapsed = 0;
        for(int key: dist.keySet()){
            int distance = dist.get(key);
            if(distance == Integer.MAX_VALUE) return -1;
            elapsed = Math.max(elapsed, distance);
        }

        return elapsed;
    }

    public int dfs(Edge edge, Set<Node> visited){

        int currentTime = edge.weight;
        visited.add(edge.node);

        int max = 0;

        for(Edge e: edge.node.edges){
            if(!visited.contains(e.node)){
                int elapsed = dfs(e, visited);
                max = Math.max(max, elapsed);
            }
        }

        return currentTime + max;
    }

    public static void main(String[] args) {
        NetworkDelay ob = new NetworkDelay();
        //int res = ob.networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2);
        int res = ob.networkDelayTime(new int[][]{{1,2,1},{2,3,2},{1,3,4}}, 3, 1);
        System.out.println("res: " + res);
    }
}
