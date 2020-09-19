package com.jay.dynamic;

import java.util.Arrays;

// https://leetcode.com/problems/stone-game-ii/
public class StoneGameII {

    public int stoneGameII(int[] piles) {

        int sum = 0;
        for(int i = 0; i< piles.length; i++){
            sum += piles[i];
        }

        // 2 7 9 4 4
        // sum = alex + bob => 2+7+9+4+4
        // findMaxScore = alex - bob => 2-7-9+4+4

        int[][] dp = new int[piles.length][piles.length];
        for(int index = 0; index < piles.length; index++){
            Arrays.fill(dp[index], Integer.MAX_VALUE);
        }

        int delta = findMaxScore(piles, 0, 1, dp);
        int score = (sum + delta)/2;

        return score;
    }

    // 2 7 9 4 4
    public int findMaxScore(int[] piles, int index, int M, int[][] dp) {

        int n = piles.length;
        int score = Integer.MIN_VALUE;

        if(index >= n){
            return 0;
        }

        if(dp[index][M] != Integer.MAX_VALUE){
            System.out.println(index + "-" + M + ":" + dp[index][M]);
            return dp[index][M];
        }

        int sum = 0;
        for(int i = 1; i <= 2*M; i++ ){
            if(index + i > n){
                break;
            }

            sum += piles[index + i - 1];
            int nextM = Math.max(i,M);
            int current = sum - findMaxScore(piles, index + i, nextM, dp);
            score = Math.max(score, current);
        }

        dp[index][M] = score;

        return score;
    }

    public static void main(String[] args) {
        StoneGameII ob = new StoneGameII();
        //int[] data = {2, 7, 9, 4, 4};
        int[] data = {2, 7, 9, 4, 4, 1, 1,2,3,4,5};
        int res = ob.stoneGameII(data);
        System.out.println("res: " + res);
    }
}
