package com.jay.dynamic;

// https://leetcode.com/problems/stone-game-iv/
public class StoneGameIV {

    public boolean winnerSquareGame(int n) {

        Boolean[] cache = new Boolean[n + 1];

        return play(n, cache);
    }

    public boolean play(int n, Boolean[] cache) {

        if (n == 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        if (cache[n] != null) {
            return cache[n];
        }

        int sqrt = (int) Math.sqrt(n);
        for (int x = sqrt; x >= 1; x--) {

            var current = play(n - x * x, cache);
            if (!current) {
                cache[n] = true;
                return true;
            }
        }

        cache[n] = false;
        return false;
    }

    public static void main(String[] args) {
        StoneGameIV ob = new StoneGameIV();
        int x = 92719;
        System.out.println(x + ": " + ob.winnerSquareGame(x));

        for (int i = 1; i <= 20; i++) {
            System.out.println(i + ": " + ob.winnerSquareGame(i));
        }
    }
}
