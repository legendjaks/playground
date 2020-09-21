package com.jay.dynamic;

import java.util.Arrays;

// https://leetcode.com/problems/stone-game-ii/
public class StoneGameIII {

    public String stoneGameIII(int[] stoneValue) {

        int sum = 0;
        for (int i = 0; i < stoneValue.length; i++) {
            sum += stoneValue[i];
        }

        // 2 7 9 4 4
        // sum = alex + bob => 2+7+9+4+4
        // findMaxScore = alex - bob => 2-7-9+4+4

        int[] dp = new int[stoneValue.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int delta = findMaxScore(stoneValue, 0, dp);
        int a = (sum + delta) / 2;
        int b = sum - a;

        if (a == b)
            return "Tie";

        return (a > b) ? "Alice" : "Bob";
    }

    // 2 7 9 4 4
    public int findMaxScore(int[] piles, int index, int[] dp) {

        int n = piles.length;
        int score = Integer.MIN_VALUE;

        if (index >= n) {
            return 0;
        }

        if (dp[index] != Integer.MAX_VALUE) {
            return dp[index];
        }

        int sum = 0;
        for (int i = 1; i <= 3; i++) {
            if (index + i > n) {
                break;
            }

            sum += piles[index + i - 1];
            int current = sum - findMaxScore(piles, index + i, dp);
            score = Math.max(score, current);
        }

        dp[index] = score;
        return score;
    }

    public static void main(String[] args) {
        StoneGameIII ob = new StoneGameIII();
        //int[] data = {2, 7, 9, 4, 4};
        int[] data = {1, 2, 3, -7};
        var res = ob.stoneGameIII(data);
        System.out.println("res: " + res);
    }
}
