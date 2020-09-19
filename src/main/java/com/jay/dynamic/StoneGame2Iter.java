package com.jay.dynamic;

public class StoneGame2Iter {

    public int stoneGame(int[] piles) {

        /*
        Dynamic programming

        Sub-problem state:
        dp[i][m] - the max scores A can get from piles [i:] when M = m

        A take [i: i+x] for x in [1: 2*m]
        B is left with piles[i+x:] and new_m = max(m, x)
        and B has the choices of y in [1: 2*new_m]
        A should be left with min_y(dp[i+x+y]) for y in [1: 2*new_m],
        if i+x+y out of bound, it means 0 left

        transfer function:

        dp[i][m]:

            for x in [1, 2 * m]:
                score_a = sum(piles[i:i+x))
                new_m = min(m, x)
                min_score_left = MAX_V
                for y in [1, 2*new_m]:
                    min_score_left = min(min_score_left, dp[i+x+y][2*new_m])
                dp[i][m] = max(dp[i][m], min_score_left + score_a)

        what's the max value of m? -> piles.length

        return dp[0][1]

        initial state:
        dp[n-1][m] for m in [1,n-1] = piles[n-1]
        dp[i][n-1] for i in [0, n-1] = sums of piles from i to end
        */

        int n = piles.length;
        int[][] dp = new int[n][n];
        int[] sums = new int[n + 1];

        // prefix sum
        for (int i = 1; i <= n; i++) sums[i] = sums[i - 1] + piles[i - 1];

        // initial state: m = n-1, can just take all the stones from position i
        for (int i = n - 1; i >= 0; i--) dp[i][n - 1] = sums[n] - sums[i];

        // initial state: i = n-1, with any m just take the last stone
        for (int m = 1; m < n; m++) dp[n - 1][m] = piles[n - 1];

        // loop from bottom right corner to top left corner of 2D array dp[][]
        for (int i = n - 2; i >= 0; i--) {
            for (int m = n - 2; m >= 1; m--) {
                if (i + m * 2 >= n) dp[i][m] = sums[n] - sums[i]; // A takes all the rest
                else {
                    dp[i][m] = Integer.MIN_VALUE;

                    for (int x = 1; x <= 2 * m; x++) {

                        int scoreA = sums[i + x] - sums[i]; // A takes x stones from position i
                        int newM = Math.max(x, m);
                        int minLeft = Integer.MAX_VALUE; // the min score B leaves to A

                        for (int y = 1; y <= 2 * newM; y++) {
                            int left = i + x + y < n ? dp[i + x + y][Math.max(newM, y)] : 0; // score that B leaves
                            minLeft = Math.min(minLeft, left);
                        }

                        dp[i][m] = Math.max(dp[i][m], minLeft + scoreA); // maximize the score that B leaves
                    }
                }
            }
        }

        return dp[0][1];
    }

    public static void main(String[] args) {

        StoneGame2Iter ob = new StoneGame2Iter();
        int[] data = {3, 4, 5, 100};
        //int[] data = {1, 2, 3, 4, 5, 100};
        //int[] data = {2, 1, 5, 6, 7, 1, 2, 6, 7, 1, 2, 5, 6, 8, 3, 2, 5, 7, 8, 2, 3};
        var res = ob.stoneGame(data);
        System.out.println("res: " + res);
    }
}
