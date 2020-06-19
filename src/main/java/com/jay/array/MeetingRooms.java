package com.jay.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms {

    public int minMeetingRooms(int[][] intervals) {

        Comparator<int[]> sorter = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if(o1[0] > o2[0])
                    return 1;

                if(o1[0] < o2[0])
                    return -1;

                if(o1[1] > o2[1])
                    return 1;

                if(o1[1] < o2[1])
                    return -1;

                return 0;
            }
        };

        Comparator<int[]> arranger = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if(o1[1] > o2[1])
                    return 1;

                if(o1[1] < o2[1])
                    return -1;

                if(o1[0] > o2[0])
                    return 1;

                if(o1[0] < o2[0])
                    return -1;

                return 0;
            }
        };

        Arrays.sort(intervals, sorter);

        PriorityQueue<int[]> heap = new PriorityQueue<>(arranger);

        int res = 0;

        for(int[] current: intervals){

            if(heap.isEmpty()){
                heap.offer(current);
                res = 1;
                continue;
            }

            int[] top = heap.peek();
            if(top[1] <= current[0]){
                heap.poll();
            }
            heap.offer(current);

            res = Math.max(res, heap.size());
        }

        return res;
    }

    public static void main(String[] args) {
        MeetingRooms ob = new MeetingRooms();
        //int res = ob.minMeetingRooms(new int[][]{{0, 30},{5, 10},{15, 20}});
        int res = ob.minMeetingRooms(new int[][]{{7,10},{2,4}});
        System.out.println("res: " + res);
    }
}
