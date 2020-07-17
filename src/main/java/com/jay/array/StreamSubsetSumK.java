package com.jay.array;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StreamSubsetSumK {

    public class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int subsetSumK(int[] stream, int k) {

        Set<Position> possible = new HashSet<>();

        //   0 1 2 3 4 5  - sum
        // 0 F F F F F F
        // 4 T F F F T F   x = 1, y = 4 ->

        for (int x = 1; x <= stream.length; x++) {

            possible.add(new Position(x, 0));

            for (int y = 1; y <= k; y++) {
                boolean current = possible.contains(new Position(x - 1, y));
                if (y >= stream[x - 1]) {
                    current = current || possible.contains(new Position(x, y - stream[x - 1]));
                }

                if (current) {
                    possible.add(new Position(x, y));

                    if (y == k)
                        return x - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        StreamSubsetSumK ob = new StreamSubsetSumK();
        int[] stream = {4, 12, 8, 9, 2, 6, 7, 18, 9, 5, 13};
        int res = ob.subsetSumK(stream, 3);
        System.out.println("res: " + res);
    }
}
