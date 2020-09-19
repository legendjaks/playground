package com.jay.array;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class SubarraySumKTwice {

    public int minSumOfLengths(int[] arr, int target) {

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n));

        Stack<int[]> stack = new Stack<>();

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            var current = find(arr, target, start, end);
            if (current[0] == -1) break;

            if(stack.isEmpty()){
                stack.push(current);
            }else{
                var prev = stack.peek();
                if(current[0] < prev[0] + prev[1]){
                    if(prev[1] > current[1]){
                        stack.pop();
                        stack.push(current);
                    }
                }else
                    stack.push(current);
            }

            start = current[0] + 1;
        }

        while(!stack.isEmpty()){
            heap.offer(stack.pop()[1]);
        }

        if (heap.size() < 2)
            return -1;

        return heap.poll() + heap.poll();
    }

    // this works only if all values are +ve
    public int[] find(int[] arr, int k, int start, int end) {

        int[] p = new int[end - start + 2];

        for (int index = 1; index < p.length; index++) {
            p[index] = p[index - 1] + arr[start + index - 1];
        }

        int i = 0;
        int j = 1;

        while (j < p.length) {

            var sum = p[j] - p[i];

            if (sum == k) {
                int len = j - i;
                int pos = i;

                return new int[]{start + pos, len};
            } else if (sum < k) {
                j++;
            } else {
                i++;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        int[] data = {1,1,1,2,2,2,4,4};
        SubarraySumKTwice ob = new SubarraySumKTwice();
        int res = ob.minSumOfLengths(data, 6);
        System.out.println("res: " + res);
    }
}
