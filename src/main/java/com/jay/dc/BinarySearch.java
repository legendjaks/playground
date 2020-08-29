package com.jay.dc;

public class BinarySearch {

    public int search(int[] data, int x) {

        int lo = 0;
        int hi = data.length - 1;

        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(data[mid] == x)
                return mid;
            else if(data[mid] > x)
                hi = mid;
            else
                lo = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] data = {2, 3, 5, 8, 11, 15, 20};
        BinarySearch ob = new BinarySearch();
        int res = ob.search(data, 20);
        System.out.println("res: " + res);
    }

}
