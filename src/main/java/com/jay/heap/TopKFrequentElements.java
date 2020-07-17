package com.jay.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    public class Item {
        int number;
        int count;

        public Item(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> counts = new HashMap<>();

        for (var number : nums) {
            counts.put(number, 1 + counts.getOrDefault(number, 0));
        }

        PriorityQueue<Item> maxHeap = new PriorityQueue<Item>((a, b) -> b.count - a.count);

        for (Integer number : counts.keySet()) {
            var count = counts.get(number);
            maxHeap.offer(new Item(number, count));
        }

        int[] res = new int[k];
        for (int index = 0; index < k; index++)
            res[index] = maxHeap.poll().number;

        return res;
    }
}
