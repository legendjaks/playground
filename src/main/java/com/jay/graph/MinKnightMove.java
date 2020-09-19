package com.jay.graph;

import java.util.*;

public class MinKnightMove {

    public int minKnightMoves(int x, int y) {

        if(x < 0){
            x = -x;
        }

        if(y < 0){
            y = -y;
        }

        int[] dx = {-1, -2, -1, 2, -2, 1, 1, 2};
        int[] dy = {-2, -1, 2, -1, 1, -2, 2, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});

        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {

            var current = queue.poll();
            //System.out.println(current[0] + "-" + current[1]);

            if (current[0] == x && current[1] == y) {
                return current[2];
            }
            if(visited.contains(current[0] + "-" + current[1]))
                continue;

            visited.add(current[0] + "-" + current[1]);

            for (int i = 0; i < dx.length; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if(nx < 0 || ny < 0 || nx > 300 || ny > 300)
                    continue;

                String key = nx + "-" + ny;
                if(visited.contains(key))
                    continue;

                queue.offer(new int[]{nx, ny, 1 + current[2]});
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        MinKnightMove ob = new MinKnightMove();
        int res = ob.minKnightMoves(52, -99);
        System.out.println("res: " + res);
    }
}
