package com.jay.dynamic;

import java.util.Arrays;

public class StoneGame {

    public boolean stoneGame(int[] piles) {

        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
        }

        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        int delta = findScore(piles, 0, piles.length - 1, dp);
        int score = (sum + delta) / 2;

        return (sum - score) < score;
    }

    public int findScore(int[] piles, int lo, int hi, int[][] dp) {

        if (lo > hi)
            return 0;

        if (lo == hi)
            return piles[lo];

        if (dp[lo][hi] != Integer.MIN_VALUE)
            return dp[lo][hi];

        int score = Math.max(piles[lo] - findScore(piles, lo + 1, hi, dp),
                piles[hi] - findScore(piles, lo, hi - 1, dp));
        dp[lo][hi] = score;

        return score;
    }

    public boolean stoneGameIter(int[] piles) {

        int n = piles.length;

        int sum = 0;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
            sum += piles[i];
        }

        for (int l = 1; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j],
                        piles[j] - dp[i][j - 1]);
            }
        }

        int delta = dp[0][n - 1];
        int score = (sum + delta) / 2;

        return (sum - score) < score;
    }

    public static void main(String[] args) {

        StoneGame ob = new StoneGame();
        int[] piles = {7, 7, 12, 16, 41, 48, 41, 48, 11, 9, 34, 2, 44, 30, 27, 12, 11, 39, 31, 8, 23, 11, 47, 25, 15, 23, 4, 17, 11, 50, 16, 50, 38, 34, 48, 27, 16, 24, 22, 48, 50, 10, 26, 27, 9, 43, 13, 42, 46, 24};
        boolean res = ob.stoneGameIter(piles);
        System.out.println("res: " + res);
    }
}
