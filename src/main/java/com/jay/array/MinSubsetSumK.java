package com.jay.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinSubsetSumK {
    public int minSubArrayLen(int k, int[] nums) {

        int sum = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        // when sum - k = 0, we need to count 1
        map.put(0, 1);

        for(int num: nums){

            sum += num;
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }

            map.put(sum, 1 + map.getOrDefault(sum, 0));
        }

        return count;
    }

    public static void main(String[] args) {
        MinSubsetSumK ob = new MinSubsetSumK();

        //int res = ob.minSubArrayLen(100, new int[]{-92,-63,75,-86,-58,22,31,-16,-66,-67,420});
        //int res = ob.minSubArrayLen(0, new int[]{1, -1, 1});
        int res = ob.minSubArrayLen(1, new int[]{1, -1, 2, 3, 1});
        //int res = ob.minSubArrayLen(0, new int[]{1});
        System.out.println("res: " + res);

    }
}