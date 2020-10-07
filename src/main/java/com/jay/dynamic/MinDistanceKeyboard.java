package com.jay.dynamic;

import java.util.HashMap;
import java.util.Map;

public class MinDistanceKeyboard {

    public int minimumDistance(String word) {

        if(word.isBlank() || word.isEmpty())
            return 0;

        Map<Integer, Integer> cache = new HashMap<>();
        return find(word.toCharArray(), 0, -1, 1, cache);
    }

    private int find(char[] word, int i, int j, int index, Map<Integer, Integer> cache){

        if(index >= word.length){
            return 0;
        }
        int key = keyOf(i, j, index, word.length);
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        int first = distance(word[i], word[index]) + find(word, index, j, index + 1, cache);

        int secondDistance = (j > 0)? distance(word[j], word[index]): 0;
        int second =  secondDistance + find(word, i, index, index + 1, cache);

        int res = Math.min(first, second);
        cache.put(key, res);

        return res;
    }

    private int keyOf(int i, int j, int k, int n){
        return (i+1)*(n+1)*(n+1) + (j+1)*(n+1) + (k+1);
    }

    private int distance(char s, char t) {

        int a = s - 'A';
        int x1 = a/6;
        int y1 = a%6;

        int b = t - 'A';
        int x2 = b/6;
        int y2 = b%6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {

        MinDistanceKeyboard ob = new MinDistanceKeyboard();
        int res = ob.minimumDistance("HAPPY");
        System.out.println("res: " + res);
    }
}
