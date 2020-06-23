package com.jay.stack;

import java.util.Stack;

public class ValidateSequence {

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if(pushed.length == 0) return true;

        int p = 0, q = 0;

        Stack<Integer> stack = new Stack<>();
        while(p < pushed.length || q < popped.length){

            if(!stack.isEmpty() && stack.peek() == popped[q]){
                stack.pop();
                q++;
            }else if(p < pushed.length){
                stack.push(pushed[p++]);
            }else
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidateSequence ob = new ValidateSequence();
        // [1,2,3,4,5], popped = [4,5,3,2,1]
        //boolean res = ob.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1});
        boolean res = ob.validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,1,2});
        System.out.println("res: " + res);
    }
}
