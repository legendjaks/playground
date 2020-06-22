package com.jay.graph;

import java.util.*;

public class EvaluateDivision {

    public class Node {
        String key;
        List<Edge> edges;

        public Node(String key){
            this.key = key;
            this.edges = new ArrayList<>();
        }
    }

    public class Edge {
        Node node;
        double weight;

        public Edge(Node node, double weight){
            this.node = node;
            this.weight = weight;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Node> nodes = new HashMap<>();

        for(int index = 0; index < equations.size();index++) {

            List<String> equation = equations.get(index);
            String source = equation.get(0);
            String dest = equation.get(1);
            double value = values[index];

            if(!nodes.containsKey(source)){
                nodes.put(source, new Node(source));
            }

            if(!nodes.containsKey(dest)){
                nodes.put(dest, new Node(dest));
            }

            Node sourceNode = nodes.get(source);
            Node destNode = nodes.get(dest);

            sourceNode.edges.add(new Edge(destNode, value));
            destNode.edges.add(new Edge(sourceNode, 1/value));
        }

        double[] res = new double[queries.size()];
        Arrays.fill(res, -1.0);

        for(int index = 0;index< queries.size(); index++){
            List<String> query = queries.get(index);
            Node source = nodes.get(query.get(0));
            Node dest = nodes.get(query.get(1));

            res[index] = dfs(source, dest, nodes, new HashSet<>());
        }

        return res;
    }

    public double dfs(Node start, Node end, Map<String, Node> nodes, Set<Node> visited){

        if(start == null || end == null) return -1.0;

        visited.add(start);

        if(start == end) return 1.0;

        for(Edge edge: start.edges){
            if(visited.contains(edge.node)) continue;

            double value = dfs(edge.node, end, nodes, visited);
            if(value > 0.0){
                return edge.weight * value;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        EvaluateDivision ob = new EvaluateDivision();

        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));
        double[] values = {2.0, 3.0};

        double[] res = ob.calcEquation(equations, values, queries);
        for(double value: res)
            System.out.print(value + ", ");
    }
}
