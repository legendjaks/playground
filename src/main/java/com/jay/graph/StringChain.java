package com.jay.graph;

import java.util.*;

public class StringChain {

    public class Item {
        String key;
        int level;

        public Item(String key, int level) {
            this.key = key;
            this.level = level;
        }
    }

    public int longestStrChain(String[] words) {

        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() == s2.length())
                return s1.compareTo(s2);
            return s1.length() - s2.length();
        });

        Map<String, Set<String>> patterns = new HashMap<>();

        for (String word : words) {
            Set<String> generated = generateMatchingPatterns(word);

            for (String pattern : generated) {
                Set<String> existing = patterns.getOrDefault(pattern, new HashSet<>());
                existing.add(word);
                patterns.put(pattern, existing);
            }
        }

        Map<String, Set<String>> wildcards = new HashMap<>();

        for (String word : words) {
            Set<String> generated = generateWildcardStrings(word);
            wildcards.put(word, generated);
        }

        int max = 0;
        for (String word : words) {
            //System.out.println(w);
            int res = findStrChain(word, patterns, wildcards);
            max = Math.max(max, res);
        }

        return max;
    }

    public int findStrChain(String start, Map<String, Set<String>> patterns, Map<String, Set<String>> wildcards) {

        int max = 1;

        Set<String> visited = new HashSet<>();
        Queue<Item> queue = new LinkedList<>();
        queue.offer(new Item(start, 1));

        while (!queue.isEmpty()) {
            Item current = queue.poll();
            if (visited.contains(current.key))
                continue;

            visited.add(current.key);

            Set<String> possibleWildcards = wildcards.get(current.key);
            for (String wildcard : possibleWildcards) {
                Set<String> edges = patterns.get(wildcard);
                if (edges == null) continue;

                for (String edge : edges) {
                    queue.offer(new Item(edge, 1 + current.level));
                    max = Math.max(max, 1 + current.level);
                }
            }
        }

        return max;
    }

    public Set<String> generateWildcardStrings(String input) {
        int len = input.length();
        Set<String> res = new HashSet<>();

        for (int index = 0; index <= len; index++) {
            StringBuilder sb = new StringBuilder();
            sb.append(input, 0, index);
            sb.append('*');
            if (index < len)
                sb.append(input, index, len);
            res.add(sb.toString());
        }

        return res;
    }

    public Set<String> generateMatchingPatterns(String input) {
        int len = input.length();
        Set<String> res = new HashSet<>();

        for (int index = 0; index < len; index++) {
            StringBuilder sb = new StringBuilder();
            sb.append(input, 0, index);
            sb.append('*');
            sb.append(input, index + 1, len);
            res.add(sb.toString());
        }

        return res;
    }

    public static void main(String[] args) {
        StringChain ob = new StringChain();
        // ["ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"]
        int res = ob.longestStrChain(new String[]{"ksqvsyq", "ks", "kss", "czvh", "zczpzvdhx", "zczpzvh", "zczpzvhx", "zcpzvh", "zczvh", "gr", "grukmj", "ksqvsq", "gruj", "kssq", "ksqsq", "grukkmj", "grukj", "zczpzfvdhx", "gru"});
        // ks, kss, kssq, ksqsq, ksqvsq, ksqvsyq
        // gr, gru, gruj, grukj, grukmj, grukkmj
        // czvh, zczvh, zcpzvh, zczpzvh, zczpzvhx, zczpzvdhx, zczpzfvdhx

        System.out.println("res: " + res);
    }
}
