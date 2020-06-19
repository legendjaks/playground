package com.jay.array;

public class MinSubsetSumK {
    public int minSubArrayLen(int s, int[] nums) {

        if(nums.length == 0)
            return 0;

        int res = 0;
        int current = nums[0];
        int i = 0;
        int j = 1;

        while(i < nums.length || j < nums.length) {

            if(current < s || j <= i){
                if(j >= nums.length)
                    break;

                current += nums[j];
                j++;
            }

            if(current >= s){

                if(current == s)
                    res++;

                current -= nums[i];
                i++;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        MinSubsetSumK ob = new MinSubsetSumK();

        int res = ob.minSubArrayLen(0, new int[]{1, -1, 1});
        System.out.println("res: " + res);

    }
}