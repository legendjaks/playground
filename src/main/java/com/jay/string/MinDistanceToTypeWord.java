package com.jay.string;

import java.util.HashSet;
import java.util.Set;

public class MinDistanceToTypeWord {

    public int minimumDistance(String word) {
        int[] keys = new int[word.length()];
        for (int i = 0; i < word.length(); i++) keys[i] = word.charAt(i) - 'A';

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < word.length(); i++) set.add(word.charAt(i) - 'A');

        // precompute costs between all keys
        // key 26 - is a 'finger off the keyboard' key, cost[26][*] = 0
        // we can only go from 'finger off' to some other key not vice versa
        int[][] cost = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = i; j < 26; j++) {
                cost[i][j] = Math.abs(i / 6 - j / 6) + Math.abs(i % 6 - j % 6);
                cost[j][i] = cost[i][j];
            }
        }

        // dp[j] represents minimum distance from current char/key till the end of the word
        // with one finger (leading) always being at prev key and the other
        // finger at an unknown key j prior to prev key - we try all posibile keys for j
        int[] dp = new int[26];

        int res = 0;

        for (int i = keys.length - 1; i > 0; i--) {
            // below var enables memory optimisation - reuse of the same 0->26 dp slots from previous iterations
            int prev_key_cost = dp[keys[i - 1]];
            int current_cost = cost[keys[i - 1]][keys[i]];
            for (int j = 0; j < 26; j++) {
                if (!set.contains(j)) continue;
                // lagging finger at j, leading finger at i - 1
                // either of the two can move hence two options below
                dp[j] = Math.min(
                        // lagging finger moves, i.e. current leading becomes lagging, paying cost from lagging to i
                        prev_key_cost + cost[j][keys[i]],
                        // leading finger moves, i.e. lagging remains lagging, paying cost from leading to i
                        dp[j] + current_cost);
            }

            res = Math.min(prev_key_cost, res + current_cost);
        }

        return res;
    }

    public int distance(char s, char e) {

        if (s == e) return 0;

        int[] p1 = location(s);
        int[] p2 = location(e);

        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public int[] location(char c) {

        int value = c - 'A';
        var x = value / 6;
        var y = value % 6;
        return new int[]{x, y};
    }

    public static void main(String[] args) {

        MinDistanceToTypeWord ob = new MinDistanceToTypeWord();
        String[] words = {"AYZ"};
        //String[] words =  {"AYZ", "CAKE", "NEW", "HAPPYOZ", "YEAOZ"};
        for (var word : words) {
            int res = ob.minimumDistance(word);
            System.out.println(word + " : " + res);
        }
    }
}
