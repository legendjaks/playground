package com.jay.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FrogJump {

    public class Item {
        int stone;
        int unit;

        public Item(int stone, int unit) {
            this.stone = stone;
            this.unit = unit;
        }
    }

    public boolean canCross(int[] stones) {

        int lastStone = stones[stones.length - 1];

        Set<Integer> steps = new HashSet<>();
        for (int stone : stones) {
            steps.add(stone);
        }

        Queue<Item> queue = new LinkedList<>();
        queue.offer(new Item(0, 1));

        while (!queue.isEmpty()) {

            var current = queue.poll();
            if (current.stone == lastStone) {
                return true;
            }

            if (steps.contains(current.stone + current.unit + 1)) {
                queue.offer(new Item(current.stone + current.unit + 1, current.unit + 1));
            }

            if (current.unit > 0 && steps.contains(current.stone + current.unit)) {
                queue.offer(new Item(current.stone + current.unit, current.unit));
            }

            if (current.unit > 1 && steps.contains(current.stone + current.unit - 1)) {
                queue.offer(new Item(current.stone + current.unit - 1, current.unit - 1));
            }
        }

        return false;
    }

    public static void main(String[] args) {

        FrogJump ob = new FrogJump();
        int[] data1 = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        int[] data2 = new int[]{0, 1, 2, 3, 4, 8, 9, 11};
        boolean res = ob.canCross(data2);
        System.out.println("res: " + res);
    }
}
