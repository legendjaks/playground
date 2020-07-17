package com.jay.array;

import java.util.HashMap;
import java.util.Map;

public class DivideXGroups {

    public boolean hasGroupsSizeX(int[] deck) {

        Map<Integer, Integer> groups = new HashMap<>();

        for (int card : deck) {
            int current = groups.getOrDefault(card, 0);
            groups.put(card, current + 1);
        }

        int gcd = Integer.MAX_VALUE;
        for (var key : groups.keySet()) {
            var size = groups.get(key);

            if (gcd == Integer.MAX_VALUE)
                gcd = size;
            else
                gcd = gcd(gcd, size);
        }

        if (gcd == 1)
            return false;

        for (var key : groups.keySet()) {
            var size = groups.get(key);
            if (size % gcd != 0) return false;
        }

        return true;
    }

    public int gcd(int a, int b) {

        if (a < b) {
            return (a == 0) ? b : gcd(b % a, a);
        }

        return gcd(a % b, b);
    }

    public static void main(String[] args) {
        DivideXGroups ob = new DivideXGroups();
        int[] data = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        boolean res = ob.hasGroupsSizeX(data);
        System.out.println("res: " + res);
    }
}
