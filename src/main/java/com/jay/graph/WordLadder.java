package com.jay.graph;

import java.util.*;

public class WordLadder {

    public class Item {
        String key;
        int level;

        public Item(String key, int level) {
            this.key = key;
            this.level = level;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Map<String, Set<String>> dict = new HashMap<>();
        Map<String, Set<String>> wildcards = new HashMap<>();

        Set<String> words = new HashSet<>(wordList);
        words.add(beginWord);

        // O(N * L * L) for wildcard generation
        for (String word : words) {
            Set<String> generated = generateWildcardStrings(word);
            wildcards.put(word, generated);

            for (String wildcard : generated) {
                Set<String> existing = dict.getOrDefault(wildcard, new HashSet<>());
                existing.add(word);
                dict.put(wildcard, existing);
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<Item> queue = new LinkedList<>();

        queue.offer(new Item(beginWord, 1));

        // O(N * L * N )
        while (!queue.isEmpty()) {
            Item current = queue.poll();
            if (visited.contains(current.key))
                continue;

            visited.add(current.key);

            Set<String> possibleWildcards = wildcards.get(current.key);
            for (String wildcard : possibleWildcards) {
                Set<String> edges = dict.get(wildcard);
                if (edges == null) continue;

                for (String edge : edges) {
                    if (edge.equals(endWord)) {
                        return 1 + current.level;
                    }

                    queue.offer(new Item(edge, 1 + current.level));
                }
            }
        }

        return 0;
    }

    public Set<String> generateWildcardStrings(String input) {
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
        WordLadder ob = new WordLadder();
        int res = ob.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println("res: " + res);
    }
}
