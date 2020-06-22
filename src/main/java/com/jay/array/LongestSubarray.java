package com.jay.array;

import java.util.ArrayDeque;

public class LongestSubarray {

    // [10,1,2,4,7,2] limit: 5
    public int longestSubarray(int[] nums, int limit) {

        if(nums.length == 0)
            return 0;

        int i = 0, j = 0;
        int len = 1;

        int min = nums[0];
        int max = nums[0];

        while(j < nums.length){

            if(i == j){
                j++;
            }else{
                if(Math.abs(min - nums[j]) <= limit && Math.abs(max - nums[j]) <= limit){
                    len = Math.max(len, 1 + j - i);
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                    j++;
                }else{
                    i++;
                    min = nums[j];
                    max = nums[j];
                    for(int index = j-1; index >= i; index--) {

                        if(Math.abs(min - nums[index]) <= limit && Math.abs(max - nums[index]) <= limit) {
                            min = Math.min(min, nums[index]);
                            max = Math.max(max, nums[index]);
                        }else{
                            i = index + 1;
                            break;
                        }
                    }
                }
            }
        }

        return len;
    }

    public int longestSubarray2(int[] nums, int limit) {
        ArrayDeque<Integer> increasing = new ArrayDeque<>();
        ArrayDeque<Integer> decreasing = new ArrayDeque<>();
        int result = 0;
        int startPos = 0;

        for (int i=0 ; i<nums.length ; ++i) {
            while (!increasing.isEmpty() && nums[increasing.getLast()] > nums[i]) {
                increasing.removeLast();
            }
            increasing.addLast(i);
            while (!decreasing.isEmpty() && nums[decreasing.getLast()] < nums[i]) {
                decreasing.removeLast();
            }
            decreasing.addLast(i);

            while (nums[decreasing.getFirst()] - nums[increasing.getFirst()] > limit) {
                if (decreasing.getFirst() < increasing.getFirst()) {
                    startPos = decreasing.removeFirst() + 1;
                } else {
                    startPos = increasing.removeFirst() + 1;
                }
            }
            result = Math.max(result, i-startPos+1);
        }
        return result;
    }

    public static void main(String[] args) {
        LongestSubarray ob = new LongestSubarray();
        //int res = ob.longestSubarray2(new int[]{10,1,2,4,7,2}, 5);
        int res = ob.longestSubarray2(new int[]{4,8,5,1,7,9}, 6);
        System.out.println("res: " + res);
    }
}
