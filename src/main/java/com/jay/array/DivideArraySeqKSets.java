package com.jay.array;

import java.util.TreeMap;

public class DivideArraySeqKSets {

    // [1,2,3,4] k = 3
    public boolean isPossibleDivide(int[] nums, int k) {

        if(nums.length < k) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int n: nums){
            int current = map.getOrDefault(n, 0);
            map.put(n, 1 + current);
        }

        while(map.size() > 0){

            int start = map.firstKey();
            for(int n = start;n< start + k; n++) {
                if (!map.containsKey(n)) {
                    return false;
                }

                int count = map.get(n);
                if(count == 1)
                    map.remove(n);
                else
                    map.put(n, count - 1);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        DivideArraySeqKSets ob = new DivideArraySeqKSets();
        // [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
        //boolean res = ob.isPossibleDivide(new int[]{3,2,1,2,3,4,3,4,5,9,10,11}, 3);
        boolean res = ob.isPossibleDivide(new int[]{3,3,2,2,1,1}, 3);
        System.out.println("res: " + res);
    }
}
