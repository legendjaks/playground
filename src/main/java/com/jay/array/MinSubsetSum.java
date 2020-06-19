package com.jay.array;

public class MinSubsetSum {
    public int minSubArrayLen(int s, int[] nums) {

        int current, i, j;
        int res = Integer.MAX_VALUE;

        current = i = j = 0;

        while(i < nums.length) {

            if(current < s){
                if(j >= nums.length)
                    break;

                current += nums[j];
                j++;
            }

            if(current >= s){
                res = Math.min(res, j - i);
                current -= nums[i];
                i++;
            }

        }

        if(res == Integer.MAX_VALUE)
            return 0;

        return res;

    }

    public static void main(String[] args) {
        MinSubsetSum ob = new MinSubsetSum();

        int res = ob.minSubArrayLen(7, new int[]{2,3,1,2,4,3});
        System.out.println("res: " + res);

    }
}