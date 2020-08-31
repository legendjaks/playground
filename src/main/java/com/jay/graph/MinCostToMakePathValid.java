package com.jay.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostToMakePathValid {

    public int minCost(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        int[][] dirs = new int[][]{{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int x = node[0];
            int y = node[1];
            int d = node[2];

            if (x == rows - 1 && y == cols - 1) {
                return d;
            }
            for (int i = 1; i <= 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];

                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                // if proper direction is there only
                // we can move into proper cell
                int nd = d + (i == grid[x][y] ? 0 : 1);
                if (dist[nx][ny] > nd) {
                    dist[nx][ny] = nd;
                    pq.offer(new int[]{nx, ny, nd});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MinCostToMakePathValid ob = new MinCostToMakePathValid();
        //int[][] data = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        int[][] data = {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}};
        int res = ob.minCost(data);
        System.out.println("res: " + res);
    }
}
