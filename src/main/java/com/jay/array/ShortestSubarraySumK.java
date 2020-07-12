package com.jay.array;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
public class ShortestSubarraySumK {

    public int shortestSubarray(int[] A, int K) {

        int len = A.length;
        int[] sum = new int[len + 1];

        for(int index = 0; index < A.length; index++){
            sum[index + 1] = sum[index] + A[index];
        }

        Deque<Integer> inc = new LinkedList<>();

        int res = Integer.MAX_VALUE;
        // sum[y] - sum[x] >= K
        for(int y = 0; y < sum.length; y++){

            while(!inc.isEmpty() && sum[y] <= sum[inc.getLast()] ){
                inc.removeLast();
            }

            while(!inc.isEmpty() && (sum[y] - sum[inc.getFirst()]) >= K){
                res = Math.min(res, y - inc.removeFirst());
            }

            inc.addLast(y);
        }

        return (res == Integer.MAX_VALUE)? -1 : res;
    }

    public static void main(String[] args) {

        ShortestSubarraySumK ob = new ShortestSubarraySumK();
        int[] data = {0,2,-1,2,2,2};

        int res = ob.shortestSubarray(data, 3);
        System.out.println("res: " + res);
    }
}
