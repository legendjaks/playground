package com.jay.array;

public class SubarrayProduct {

    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if(k == 0)
            return 0;

        int current, i, j;
        int res = 0;

        i = j = 0;
        current = 1;

        while(i < nums.length) {

            if(current < k){

                if(current > 1)
                    res ++;

                if(j >= nums.length)
                    break;

                current *= nums[j];
                j++;
            }
            else {
                current /= nums[i];
                i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        SubarrayProduct ob = new SubarrayProduct();

        int res = ob.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100);
        System.out.println("res: " + res);

    }
}
