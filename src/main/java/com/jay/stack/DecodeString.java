package com.jay.stack;

import java.util.Stack;

public class DecodeString {

    public class Item {

        String data;
        boolean isNumber;

        public Item(String data, boolean isNumber){
            this.data = data;
            this.isNumber = isNumber;
        }
    }

    // 3[a2[c]]
    public String decodeString(String s) {

        Stack<Item> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int number = 0;

        for(int index = 0; index < s.length(); index++){

            char c = s.charAt(index);

            if(Character.isDigit(c)){

                if(sb.length() > 0){
                    stack.push(new Item(sb.toString(), false));
                    sb = new StringBuilder();
                }

                number = number * 10 + (c - '0');
            }else if(c == '['){
                stack.push(new Item(String.valueOf(number), true));
                number = 0;
            }else if(c == ']'){

                if(sb.length() > 0) {
                    stack.push(new Item(sb.toString(), false));
                    sb = new StringBuilder();
                }

                while(!stack.isEmpty()) {
                    Item top1 = stack.pop();
                    Item top2 = stack.pop();

                    if(top2.isNumber) {
                        StringBuilder temp = new StringBuilder();

                        int total = Integer.parseInt(top2.data);
                        for (int count = 0; count < total; count++) {
                            temp.append(top1.data);
                        }
                        stack.push(new Item(temp.toString(), false));

                        break;
                    }else{
                        stack.push(new Item(top2.data + top1.data, false));
                    }
                }
            }else{
                sb.append(c);
            }
        }

        stack.push(new Item(sb.toString(), false));

        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()){
            res.insert(0, stack.pop().data);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        DecodeString ob = new DecodeString();
        String res = ob.decodeString("abc3[cd]xyz");

        System.out.println("res: " + res);
    }
}
