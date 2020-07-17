package com.jay.stack;

import java.util.Deque;
import java.util.LinkedList;

public class ValidateParanthesis {

    public class Item {
        char c;
        int index;

        public Item(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    public boolean checkValidString(String s) {

        Deque<Item> running = new LinkedList<>();
        Deque<Integer> stars = new LinkedList<>();

        for (int index = 0; index < s.length(); index++) {

            char c = s.charAt(index);

            if (c == ')') {
                if (running.isEmpty() && stars.isEmpty())
                    return false;

                if (!running.isEmpty())
                    running.removeLast();
                else if (!stars.isEmpty())
                    stars.removeLast();

            } else if (c == '*') {
                stars.addLast(index);
            } else {
                running.addLast(new Item(c, index));
            }
        }

        while (!running.isEmpty() && !stars.isEmpty()) {
            var top = running.getLast();

            if (top.index < stars.getLast()) {
                running.removeLast();
                stars.removeLast();
            } else break;
        }

        return running.isEmpty();
    }

    public static void main(String[] args) {

        ValidateParanthesis ob = new ValidateParanthesis();
        String[] texts = {"(*))", "(*)", "(**))", "(**()", "**)(", "", "((*****))())"};

        for (String text : texts) {
            boolean res = ob.checkValidString(text);
            System.out.println(text + " : " + res);
        }
    }
}
