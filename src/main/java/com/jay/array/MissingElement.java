package com.jay.array;

public class MissingElement {

    public int missingElement(int[] nums, int k) {

        int len = nums.length;
        int diff = missing(nums, len -1);

        if(diff < k)
            return nums[len - 1] + k - diff;

        int lo = 0;
        int hi = len -1;

        while(lo != hi){
            int mid = lo + (hi - lo)/2;
            diff = missing(nums, mid);

            if(diff < k)
                lo = mid + 1;
            else
                hi = mid;
        }

        return nums[lo - 1] + k - missing(nums, lo - 1);
    }

    public int missing(int[] x, int index){
        return x[index] - x[0] - index;
    }
}
