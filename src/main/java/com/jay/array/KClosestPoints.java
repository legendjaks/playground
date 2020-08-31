package com.jay.array;

import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPoints {

    public int[][] kClosest(int[][] points, int K) {

        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> n2 - n1);

        for(var point: points){
            int distance = distance(point);

            heap.offer(distance);

            if(heap.size() > K)
                heap.poll();
        }

        int size = Math.min(heap.size(), K);
        int[][] res = new int[size][2];
        int pos = 0;

        int max = heap.poll();

        for(var point:points){

            int distance = distance(point);
            if(distance <= max)
                res[pos++] = point;
        }

        return res;
    }

    public int distance(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }
}
