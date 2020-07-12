package com.jay.array;

import java.util.HashSet;
import java.util.Set;

public class DivideChoclate {

    public int maximizeSweetness(int[] sweetness, int K) {

        int lo = 0;
        int hi = 0;

        for (int s : sweetness) {
            hi += s;
        }

        while (lo <= hi) {

            int level = (lo + hi) / 2;

            boolean possible = possibleToDivide(sweetness, level, K + 1);
            if (possible) {
                lo += 1;
            } else {
                hi -= 1;
            }
        }

        return lo - 1;
    }

    public boolean possibleToDivide(int[] sweetness, int level, int parts) {

        int count = 0;
        int current = 0;

        for (int s : sweetness) {

            current += s;

            if (current >= level) {
                current = 0;
                count++;
            }
        }

        return count >= parts;
    }

    public static void main(String[] args) {

        DivideChoclate ob = new DivideChoclate();
        int[] s = new int[]{1, 2, 2, 1, 2, 2, 1, 2, 2};
        int res = ob.maximizeSweetness(s, 2);
        System.out.println("res: " + res);
    }
}
