package com.jay.array;

public class MaxSumNonOverlapping {

    public int maxSumTwoNoOverlap(int[] A, int L, int M) {

        int len = A.length;
        int[] sum = new int[len];

        sum[0] = A[0];
        for (int index = 1; index < len; index++)
            sum[index] = sum[index - 1] + A[index];

        int res = 0;

        int lmax = sum[L - 1];
        for (int index = L; index + M - 1 < len; index++) {
            res = Math.max(res, lmax + sum[index + M - 1] - sum[index - 1]);
            lmax = Math.max(lmax, sum[index] - sum[index - L]);
        }

        int mmax = sum[M - 1];
        for (int index = M; index + L - 1 < len; index++) {
            res = Math.max(res, mmax + sum[index + L - 1] - sum[index - 1]);
            mmax = Math.max(mmax, sum[index] - sum[index - M]);
        }

        return res;
    }

    public static void main(String[] args) {

        MaxSumNonOverlapping ob = new MaxSumNonOverlapping();
        int res = ob.maxSumTwoNoOverlap(new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8}, 4, 3);
        System.out.println("res: " + res);
    }
}
