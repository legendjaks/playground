package com.jay.dc;

import java.util.Arrays;

public class DivideChoclate {

    public int maximizeSweetness(int[] sweetness, int K) {
        int lo = 1;
        int hi = Arrays.stream(sweetness).sum() / (K + 1);

        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            int chunks = split(sweetness, mid);
            if (chunks >= K + 1)
                lo = mid;
            else
                hi = mid - 1;
        }

        return lo;
    }

    private int split(int[] sweetness, int level) {
        int sum = 0;
        int chunks = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
            if (sum >= level) {
                chunks++;
                sum = 0;
            }
        }

        return chunks;
    }

    public static void main(String[] args) {
        DivideChoclate ob = new DivideChoclate();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int res = ob.maximizeSweetness(data, 5);
        System.out.println("res: " + res);
    }
}
