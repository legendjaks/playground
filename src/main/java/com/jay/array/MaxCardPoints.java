package com.jay.array;

public class MaxCardPoints {

    public int maxScore(int[] cardPoints, int k) {

        int res = 0;
        int sum = 0;

        for (int index = 0; index < k; index++) {
            sum += cardPoints[index];
        }

        res = sum;

        int delta = 0;
        for (int index = cardPoints.length - 1; index >= cardPoints.length - k; index--) {
            sum += cardPoints[index] - cardPoints[k - 1 - delta];
            delta++;
            res = Math.max(res, sum);
        }

        return res;
    }

    public static void main(String[] args) {
        MaxCardPoints ob = new MaxCardPoints();
        int res = ob.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7);
        System.out.println("res: " + res);
    }
}
