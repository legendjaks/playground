package com.jay.array;

import java.util.TreeMap;

public class InsertIntervals {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        if(intervals.length == 0)
            return new int[][]{ newInterval };

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int[] interval : intervals) {
            map.put(interval[0], interval[1]);
        }

        var start = newInterval[0];
        var end = newInterval[1];

        var prev = map.floorKey(start);
        var newEnd = end;
        if(prev != null){
            newEnd = map.get(prev);

            if(map.get(prev) < start){
                prev = start;
            }
        }else
            prev = start;

        var next = map.ceilingKey(start);
        if(next == null){
            map.put(prev, Math.max(end, newEnd));
        }else{

            do{
                if(next <= end){
                    newEnd = map.get(next);

                    if(map.get(next) >= end){
                        map.remove(next);
                        break;
                    }else
                        map.remove(next);
                }else
                    break;

                next = map.ceilingKey(next);
            }while(next != null);

            map.put(prev, Math.max(end, newEnd));
        }

        int[][] res = new int[map.size()][2];
        int index = 0;
        for(Integer key: map.keySet()){
            res[index++] = new int[]{ key, map.get(key)};
        }

        return res;
    }

    public static void main(String[] args) {

        InsertIntervals ob = new InsertIntervals();
        //int[][] res = ob.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        //int[][] res = ob.insert(new int[][]{ {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 17});
        int[][] res = ob.insert(new int[][]{ {1, 5}}, new int[]{6, 8});
        //int[][] res = ob.insert(new int[][]{ {1, 5}}, new int[]{2, 3});
        for (int[] val : res) {
            System.out.println(val[0] + ", " + val[1]);
        }
    }
}
