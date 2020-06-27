package com.jay.dynamic;

public class NumberGuess {

    public int getMoneyAmount(int n) {

        // cost(i, j) = k + max( cost(i, k-1), cost(k+1, j) ) for k = i..j

        int[][] res = new int[n + 1][n + 1];

        for (var len = 2; len <= n; len++) {
            for (var i = 1; i <= n - len + 1; i++) {

                var j = i + len - 1;
                res[i][j] = Integer.MAX_VALUE;

                for (var k = i; k <= j && k < n; k++) {
                    var cost = k + Math.max(res[i][k - 1], res[k + 1][j]);
                    res[i][j] = Math.min(res[i][j], cost);
                }
            }
        }

        return res[1][n];
    }

    public static void main(String[] args) {

        NumberGuess ob = new NumberGuess();
        for (int n = 1; n <= 6; n++) {
            var res = ob.getMoneyAmount(n);
            System.out.println(n + " : " + res);
        }
    }
}
