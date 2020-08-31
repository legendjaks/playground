package com.jay.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
public class ShortestPathKObstacle {

    public class Item {
        int x, y;
        int obstacles;
        int cost;

        public Item(int x, int y, int obstacles, int cost) {
            this.x = x;
            this.y = y;
            this.obstacles = obstacles;
            this.cost = cost;
        }
    }

    public int shortestPath(int[][] grid, int k) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] obstacles = new int[rows][cols];

        for (var row : obstacles) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        obstacles[0][0] = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        Queue<Item> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i.cost));
        queue.offer(new Item(0, 0, 0, 0));

        while (!queue.isEmpty()) {

            var current = queue.poll();
            // found the target
            if (current.x == rows - 1 && current.y == cols - 1) {
                return current.cost;
            }

            for (int index = 0; index < dx.length; index++) {
                int nx = current.x + dx[index];
                int ny = current.y + dy[index];

                // out of bounds
                if (nx < 0 || ny < 0 || nx >= rows || ny >= cols)
                    continue;

                int obs = grid[nx][ny];
                if ((obs + current.obstacles) > k) {
                    continue;
                }

                if (obstacles[nx][ny] > (obs + current.obstacles)) {
                    obstacles[nx][ny] = obs + current.obstacles;
                    queue.offer(new Item(nx, ny, obs + current.obstacles, 1 + current.cost));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ShortestPathKObstacle ob = new ShortestPathKObstacle();
        int[][] data = {
                {0, 0, 0}, {1, 1, 0}, {1, 0, 0}, {1, 0, 1}, {0, 0, 0}
        };
        int res = ob.shortestPath(data, 0);
        System.out.println("res: " + res);
    }
}
