package com.jay.stack;

import java.util.LinkedList;
import java.util.Stack;

public class SimpleExpressionEvaluation {

    // 4+5*8/2+3
    public int evaluate(String s) {

        int number = 0;
        int sign = 1;
        char operator = '#';

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');

                if(i < s.length() - 1)
                    continue;
            }

            number = number * sign;
            stack.push(number);

            number = 0;
            sign = 1;

            if (operator != '#') {
                int n2 = stack.pop();
                int n1 = stack.pop();

                int val = 0;
                if (operator == '*')
                    val = n1 * n2;

                if (operator == '/')
                    val = n1 / n2;

                stack.push(val);

                operator = '#';
            }

            if (c == '+' || c == '-') {
                if (c == '+')
                    sign = 1;
                else
                    sign = -1;
            } else {
                operator = c;
            }
        }

        int res = number * sign;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    public static void main(String[] args) {
        SimpleExpressionEvaluation ob = new SimpleExpressionEvaluation();
        int res = ob.evaluate("24-17/2+3*5-1");
        System.out.println("res: " + res);
    }
}
