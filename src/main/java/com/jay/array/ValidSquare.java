package com.jay.array;

import java.util.Arrays;

public class ValidSquare {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        int[][] points = new int[][]{p1, p2, p3, p4};

        Arrays.sort(points, (p, q) -> (p[0] == q[0])? p[1] - q[1] : p[0] - q[0]);

        return distance(points[0], points[1]) != 0
                && distance(points[0], points[1]) == distance(points[0], points[2])
                && distance(points[0], points[1]) == distance(points[2], points[3])
                && distance(points[0], points[1]) == distance(points[1], points[3])
                && distance(points[0], points[3]) == distance(points[1], points[2]);
    }

    public int distance(int[] p, int[] q){
        int d1 = p[0] - q[0];
        int d2 = p[1] - q[1];
        return d1*d1 + d2*d2;
    }

    public static void main(String[] args) {
        ValidSquare ob = new ValidSquare();
        boolean res = ob.validSquare(new int[]{1, 1}, new int[]{5, 3}, new int[]{3, 5}, new int[]{7, 7});
        System.out.println("res: " + res);
    }
}
