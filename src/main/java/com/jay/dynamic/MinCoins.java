package com.jay.dynamic;

import java.util.Arrays;

public class MinCoins {

    public int coinChange(int[] coins, int amount) {

        int max = amount + 1;
        int res[] = new int[max];

        // fill all to max, except 0
        Arrays.setAll(res, p -> p > 0 ? max : 0);

        for (var coin : coins) {
            for (int i = coin; i <= amount; i++) {
                // min ( current, including current coin in change )
                res[i] = Math.min(res[i], 1 + res[i - coin]);
            }
        }

        return (res[amount] >= max) ? -1 : res[amount];
    }
}
