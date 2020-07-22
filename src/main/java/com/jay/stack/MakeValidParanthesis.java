package com.jay.stack;

import java.util.Stack;

public class MakeValidParanthesis {

    public String minRemoveToMakeValid(String s) {

        Stack<String> stack = new Stack<>();
        StringBuilder running = new StringBuilder();
        for(Character c: s.toCharArray()){

            if(c == ')'){
                if(running.length() != 0){
                    stack.push(running.toString());
                    running = new StringBuilder();
                }

                StringBuilder sb = new StringBuilder();

                while(!stack.isEmpty()){
                    if(stack.peek().equals("(")){
                        String top = stack.pop();
                        sb.insert(0, top);
                        sb.append(c);
                        break;
                    }

                    String top = stack.pop();
                    sb.insert(0, top);
                }

                if(sb.length() != 0)
                    stack.push(sb.toString());
            }else if (c == '('){
                if(running.length() != 0){
                    stack.push(running.toString());
                    running = new StringBuilder();
                }
                stack.push(String.valueOf(c));
            }else{
                running.append(c);
            }
        }

        if(running.length() != 0){
            stack.push(running.toString());
        }

        StringBuilder res = new StringBuilder();

        while(!stack.isEmpty()){
            String top = stack.pop();
            if(top.equals("("))
                continue;

            res.insert(0, top);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        MakeValidParanthesis ob = new MakeValidParanthesis();
        String res = ob.minRemoveToMakeValid("))((");
        System.out.println("res: " + res);
    }
}
