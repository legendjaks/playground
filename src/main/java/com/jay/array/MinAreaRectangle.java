package com.jay.array;

import java.util.*;

public class MinAreaRectangle {

    public class Line {
        int y1;
        int y2;

        public Line(int y1, int y2){
            this.y1 = y1;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return y1 == line.y1 &&
                    y2 == line.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y1, y2);
        }
    }

    public int minAreaRect(int[][] points) {

        Arrays.sort(points, (a, b)-> {
            if(a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        TreeMap<Integer, List<Integer>> cols = new TreeMap<>();

        for(int[] point: points){
            int x = point[0];
            int y = point[1];
            cols.computeIfAbsent(x, f -> new ArrayList<>()).add(y);
        }

        int res = Integer.MAX_VALUE;

        Map<Line, Integer> lines = new HashMap<>();

        for(int x: cols.keySet()){

            List<Integer> col = cols.get(x);
            for(int i = 0; i< col.size(); i++){
                for(int j = i+1;j< col.size(); j++){

                    int y1 = col.get(i);
                    int y2 = col.get(j);

                    Line line = new Line(y1, y2);
                    if(lines.containsKey(line)){
                        int x1 = lines.get(line);
                        int area = (x - x1) * (y2-y1);
                        res = Math.min(res, area);
                    }

                    lines.put(line, x);
                }
            }
        }

        return (res == Integer.MAX_VALUE)? 0: res;
    }

    public static void main(String[] args) {
        MinAreaRectangle ob = new MinAreaRectangle();
        int res = ob.minAreaRect(new int[][]{{1,3},{3,3},{1,1},{3,1},{2,2}});
        System.out.println("res: " + res);
    }
}
