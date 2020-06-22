package com.jay.graph;

import java.util.*;

public class OpenLock {

    public class Item {
        String key;
        int level;

        public Item(String key, int level){
            this.key = key;
            this.level = level;
        }
    }

    public int openLock(String[] deadends, String target) {

        Set<String> deadset = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";

        if(deadset.contains(start)) return -1;

        Set<String> visited = new HashSet<>();
        Queue<Item> queue = new LinkedList<>();

        queue.offer(new Item(start, 0));
        while(!queue.isEmpty()){
            Item current = queue.poll();
            if(visited.contains(current.key))
                continue;

            visited.add(current.key);

            Set<String> edges = generatePossibleRotations(current.key, deadset);
            for(String edge: edges){
                if(edge.equals(target)){
                    return 1 + current.level;
                }

                queue.offer(new Item(edge, 1 + current.level));
            }
        }

        return -1;
    }

    public Set<String> generatePossibleRotations(String input, Set<String> deadends){
        int len = input.length();
        Set<String> res = new HashSet<>();

        for(int index = 0; index< len; index++){
            char[] digits = rotateDigit(input.charAt(index));
            for(char digit:digits) {
                StringBuilder sb = new StringBuilder();
                sb.append(input, 0, index);
                sb.append(digit);
                sb.append(input, index + 1, len);

                String rotation = sb.toString();
                if (!deadends.contains(rotation))
                    res.add(rotation);
            }
        }

        return res;
    }

    public char[] rotateDigit(char c){
        char[] res = new char[2];

        res[0] = digitOf(c - 1);
        res[1] = digitOf(c + 1);

        return res;
    }

    public char digitOf(int c){
        if(c > '9') return (char)(c - 10);
        if(c < '0') return (char)(c + 10);

        return (char)c;
    }

    public String[] generateWildcardStrings(String input){
        int len = input.length();
        String[] res = new String[len];

        for(int index = 0; index< len; index++){
            StringBuilder sb = new StringBuilder();
            sb.append(input, 0, index);
            sb.append('*');
            sb.append(input, index + 1, len);
            res[index] = sb.toString();
        }

        return res;
    }

    public static void main(String[] args) {
        // ["0201","0101","0102","1212","2002"], target = "0202"
        OpenLock ob  = new OpenLock();
        int res = ob.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202");
        System.out.println("res: " + res);
        // "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"
    }
}
