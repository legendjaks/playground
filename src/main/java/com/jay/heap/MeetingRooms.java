package com.jay.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {

    public int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, (n1, n2) -> n1[0] - n2[0]);
        PriorityQueue<int[]> heap = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);

        int res = 0;

        for (int[] current : intervals) {

            if (heap.isEmpty()) {
                heap.offer(current);
                res = 1;
                continue;
            }

            int[] top = heap.peek();
            if (top[1] <= current[0]) {
                heap.poll();
            }
            heap.offer(current);

            res = Math.max(res, heap.size());
        }

        return res;
    }

    public static void main(String[] args) {
        MeetingRooms ob = new MeetingRooms();
        int res = ob.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}});
        //int res = ob.minMeetingRooms(new int[][]{{7,10},{2,4}});
        System.out.println("res: " + res);
    }
}
