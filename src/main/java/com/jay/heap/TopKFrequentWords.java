package com.jay.heap;

import java.util.*;

public class TopKFrequentWords {

    public class Item {
        String word;
        int count;

        public Item(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> counts = new HashMap<>();

        for (String word : words) {
            counts.put(word, 1 + counts.getOrDefault(word, 0));
        }

        PriorityQueue<Item> maxHeap = new PriorityQueue<Item>(
                (a, b) -> (a.count == b.count) ? a.word.compareTo(b.word) : b.count - a.count);

        for (String word : counts.keySet()) {
            var count = counts.get(word);
            maxHeap.offer(new Item(word, count));
        }

        List<String> res = new ArrayList<>();
        int len = Math.min(k, maxHeap.size());
        for (int index = 0; index < len; index++)
            res.add(maxHeap.poll().word);

        return res;
    }

    public static void main(String[] args) {

        TopKFrequentWords ob = new TopKFrequentWords();
        var res = ob.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
        for (var word : res) {
            System.out.println(word);
        }
    }
}
