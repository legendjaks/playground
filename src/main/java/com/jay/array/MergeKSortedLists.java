package com.jay.array;

import java.util.*;

public class MergeKSortedLists {

    public List<Integer> merge(List<List<Integer>> lists) {

        List<Integer> res = new ArrayList<>();

        int k = lists.size();
        int[] indexes = new int[k];

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n[0]));

        for (int i = 0; i < k; i++) {
            var list = lists.get(i);
            if (!list.isEmpty())
                heap.offer(new int[]{list.get(0), i});
        }

        while (!heap.isEmpty()) {

            var current = heap.poll();

            indexes[current[1]]++;
            var list = lists.get(current[1]);
            if (indexes[current[1]] < list.size()) {
                heap.offer(new int[]{list.get(indexes[current[1]]), current[1]});
            }

            if (!res.isEmpty()) {
                int last = res.get(res.size() - 1);
                if (current[0] == last)
                    continue;
            }
            res.add(current[0]);
        }

        return res;
    }

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> l2 = Arrays.asList(2, 20, 21, 25);
        List<Integer> l3 = Arrays.asList(1, 2, 13);

        List<List<Integer>> input = Arrays.asList(l1, l2, l3);

        MergeKSortedLists ob = new MergeKSortedLists();
        List<Integer> res = ob.merge(input);
        System.out.println();
        for (var x : res) {
            System.out.print(x + " ");
        }
    }
}
