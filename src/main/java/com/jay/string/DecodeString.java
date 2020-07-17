package com.jay.string;

import java.util.Stack;

public class DecodeString {

    public String decodeString(String s) {

        Stack<String> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '[') {
                int number = 0;
                int pow = 1;
                while (!stack.isEmpty()) {
                    String digit = stack.pop();
                    int n = numberOf(digit);
                    if (n < 0) {
                        stack.push(digit);
                        stack.push(String.valueOf(number));
                        stack.push(String.valueOf(c));
                        break;
                    } else {
                        number += n * pow;
                        pow *= 10;
                    }
                }

                if (stack.isEmpty()) {
                    stack.push(String.valueOf(number));
                    stack.push(String.valueOf(c));
                }
            } else if (c == ']') {

                StringBuilder text = new StringBuilder();

                while (!stack.isEmpty()) {

                    String current = stack.pop();
                    if (current.equals("[")) {
                        String digit = stack.pop();
                        int times = numberOf(digit);

                        String inner = text.toString();
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < times; i++) {
                            sb.append(inner);
                        }
                        stack.push(sb.toString());
                        break;
                    } else {
                        text.insert(0, current);
                    }
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {

            String current = stack.pop();
            res.insert(0, current);
        }

        return res.toString();
    }

    public int numberOf(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ex) {
            return -1;
        }
    }

    public static void main(String[] args) {
        DecodeString ob = new DecodeString();
        String s = ob.decodeString("3[a]2[bc]");
        System.out.println("res: " + s);
    }
}
