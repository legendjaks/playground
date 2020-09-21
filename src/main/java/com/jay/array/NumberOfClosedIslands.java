package com.jay.array;

public class NumberOfClosedIslands {

    public int closedIsland(int[][] grid) {

        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0 && isClosedIsland(grid, i, j, rows, cols)) {
                    res++;
                }
            }
        }

        return res;
    }

    private boolean isClosedIsland(int[][] grid, int i, int j, int rows, int cols) {

        if (grid[i][j] == -1 || grid[i][j] == 1) return true;

        if (isEdge(i, j, rows, cols)) return false;

        grid[i][j] = -1;

        boolean left = isClosedIsland(grid, i - 1, j, rows, cols);
        boolean right = isClosedIsland(grid, i + 1, j, rows, cols);
        boolean up = isClosedIsland(grid, i, j - 1, rows, cols);
        boolean down = isClosedIsland(grid, i, j + 1, rows, cols);

        return left && right && up && down;
    }

    private boolean isEdge(int i, int j, int rows, int cols) {
        return (i == 0 || j == 0 || i == rows - 1 || j == cols - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };

        NumberOfClosedIslands ob = new NumberOfClosedIslands();
        int res = ob.closedIsland(grid);
        System.out.println("res: " + res);
    }
}
