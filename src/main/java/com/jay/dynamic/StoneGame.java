package com.jay.dynamic;

import java.util.HashMap;
import java.util.Objects;

public class StoneGame {

    public class Item {
        int start;
        int end;
        int limit;

        public Item(int start, int end, int limit) {
            this.start = start;
            this.end = end;
            this.limit = limit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return start == item.start &&
                    end == item.end &&
                    limit == item.limit;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, limit);
        }
    }

    public int stoneGameII(int[] piles) {

        int length = piles.length;
        int[][] stones = new int[length][length];

        for (var len = 1; len <= length; len++) {
            for (var i = 0; i < length - len + 1; i++) {
                var j = i + len - 1;
                stones[i][j] = piles[j];
                if (j > 0)
                    stones[i][j] += stones[i][j - 1];
            }
        }

        var cache = new HashMap<Item, Integer>();

        int res = game(0, length - 1, 2, stones, cache);
        return res;
    }

    public int game(int start, int end, int limit, int[][] stones, HashMap<Item, Integer> cache) {

        var key = new Item(start, end, limit);
        if (cache.containsKey(key)) {
            System.out.println("cache: [" + start + ", " + end + ", " + limit + "] = " + cache.get(key));
            return cache.get(key);
        }

        if (start > end) return 0;
        if ((end - start) <= limit) return stones[start][end];

        var max = 0;
        for (int m = 1; m <= limit; m++) {

            var opp_max = 0;
            var opp_steps = 0;
            for(int om = 1; om <= 2*m; om++){
                if(start + m <= end && (start+m+om <= end)) {
                    var current = stones[start + m][start + m + om - 1] + game(start + m + om, end, om * 2, stones, cache);
                    if (current > opp_max) {
                        opp_max = current;
                        opp_steps = om;
                    }
                }
            }

            var current = stones[start][start + m - 1] + game(start + m + opp_steps, end, opp_steps*2, stones, cache);
            max = Math.max(max, current);
        }

        cache.put(key, max);

        return max;
    }

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
        int[] sums = new int[n+1];

        // prefix sum
        for (int i = 1; i <= n; i++) sums[i] = sums[i-1] + piles[i-1];

        // initial state: m = n-1, can just take all the stones from position i
        for (int i = n-1; i >= 0 ; i--) dp[i][n-1] = sums[n] - sums[i];

        // initial state: i = n-1, with any m just take the last stone
        for (int m = 1; m < n; m++) dp[n-1][m] = piles[n-1];

        // loop from bottom right corner to top left corner of 2D array dp[][]
        for (int i = n-2; i >= 0; i--) {
            for (int m = n-2; m >= 1; m--) {
                if (i + m*2 >= n) dp[i][m] = sums[n] - sums[i]; // A takes all the rest
                else
                {
                    dp[i][m] = Integer.MIN_VALUE;

                    for (int x = 1; x <= 2*m; x++) {

                        int scoreA = sums[i+x] - sums[i]; // A takes x stones from position i
                        int newM = Math.max(x, m);
                        int minLeft = Integer.MAX_VALUE; // the min score B leaves to A

                        for (int y = 1; y <= 2*newM; y++) {
                            int left = i+x+y < n ? dp[i+x+y][Math.max(newM,y)] : 0; // score that B leaves
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

        StoneGame ob = new StoneGame();
        int[] data = {3, 4, 5, 100};
        //int[] data = {1, 2, 3, 4, 5, 100};
        //int[] data = {2, 1, 5, 6, 7, 1, 2, 6, 7, 1, 2, 5, 6, 8, 3, 2, 5, 7, 8, 2, 3};
        var res = ob.stoneGame(data);
        System.out.println("res: " + res);
    }
}
