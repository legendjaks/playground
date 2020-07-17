package com.jay.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoldTag {

    public class Position {
        int start;
        int end;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public String addBoldTag(String s, String[] dict) {

        if (dict.length == 0) return s;

        List<Position> positions = new ArrayList<>();
        for (var word : dict) {
            var position = find(s, word, 0);
            while (position != null) {
                positions.add(position);
                position = find(s, word, 1 + position.start);
            }
        }

        if (positions.isEmpty()) return s;

        positions = mergePositions(positions);
        return addTags(s, positions);
    }

    private Position find(String text, String interested, int start) {

        int p = start, q = 0;

        while (p < text.length() && q < interested.length()) {

            if (text.charAt(p) == interested.charAt(q)) {
                p++;
                q++;

                if (q == interested.length()) {
                    return new Position(p - interested.length(), p);
                }
            } else {
                p = p - q + 1;
                q = 0;
            }
        }

        return null;
    }

    private List<Position> mergePositions(List<Position> positions) {

        Collections.sort(positions, (a, b) -> (a.start == b.start) ? b.end - a.end : a.start - b.start);

        List<Position> res = new ArrayList<>();

        Position prev = positions.get(0);
        for (int index = 1; index < positions.size(); index++) {

            var current = positions.get(index);

            if (prev.end >= current.start || prev.end == current.start) {
                prev.end = Math.max(prev.end, current.end);
            } else {
                res.add(prev);
                prev = current;
            }
        }
        res.add(prev);

        return res;
    }

    private String addTags(String text, List<Position> positions) {

        int start = 0;
        StringBuilder sb = new StringBuilder();

        for (Position position : positions) {
            sb.append(text.substring(start, position.start));
            sb.append("<b>");
            sb.append(text.substring(position.start, position.end));
            sb.append("</b>");

            start = position.end;
        }

        sb.append(text.substring(start));

        return sb.toString();
    }

    public static void main(String[] args) {
        BoldTag ob = new BoldTag();

        //Position pos = ob.find("aaabc", "aab");
        //System.out.println(pos.start + ", " + pos.end);

        String res = ob.addBoldTag("abcdef", new String[]{"a", "e", "c"});
        System.out.println("res: " + res);
    }
}
