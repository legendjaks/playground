package com.jay.string;

public class RemoveWhiteSpaces {

    public String trim(String input){

        char[] text = input.toCharArray();
        boolean first = true;
        boolean foundChar = false;
        int index = 0, current = 0, lastChar = -1;

        while(index < text.length){
            if(text[index] == ' '){
                if(first && foundChar){
                    text[current++] = text[index];
                    first = false;
                }
            }else{
                text[current++] = text[index];
                lastChar = current;
                foundChar = true;
                first = true;
            }
            index ++;
        }

        if(lastChar < 0)
            return "";

        return new String(text, 0, Math.min(current, lastChar));
    }

    public static void main(String[] args) {
        RemoveWhiteSpaces ob  = new RemoveWhiteSpaces();
        String res = ob.trim("   3   a      b  c     ");
        //String res = ob.trim("   ");
        System.out.println("|" + res + "|");
    }
}
