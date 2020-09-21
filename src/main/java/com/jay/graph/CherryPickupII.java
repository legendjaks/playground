package com.jay.graph;

import java.util.Arrays;

public class CherryPickupII {

    public int cherryPickup(int[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        int[][][] dp = new int[rows][cols][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        int res = pickup(grid, 0, 0, cols - 1, dp);
        return res;
    }

    private int pickup(int[][] grid, int row, int c1, int c2, int[][][] dp) {

        int rows = grid.length;
        int cols = grid[0].length;

        if (row == rows || c1 < 0 || c2 < 0 || c1 == cols || c2 == cols)
            return 0;

        if (dp[row][c1][c2] != Integer.MIN_VALUE) {
            return dp[row][c1][c2];
        }

        System.out.println(row + "-" + c1 + "-" + c2);

        int score = grid[row][c1] + grid[row][c2];
        if (c1 == c2) {
            score -= grid[row][c1];
        }

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 0}, {0, 1},
                {1, -1}, {1, 0}, {1, 1},
        };

        int max = Integer.MIN_VALUE;
        for (int[] direction : directions) {
            int current = pickup(grid, row + 1, c1 + direction[0], c2 + direction[1], dp);
            max = Math.max(max, current);
        }

        dp[row][c1][c2] = score + max;
        return score + max;
    }

    public static void main(String[] args) {
        CherryPickupII ob = new CherryPickupII();
        int[][] grid = {{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}};
        int res = ob.cherryPickup(grid);
        System.out.println("res: " + res);
    }
}
