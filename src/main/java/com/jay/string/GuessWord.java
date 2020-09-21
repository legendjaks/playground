package com.jay.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// https://leetcode.com/problems/guess-the-word/
public class GuessWord {

    public void findSecretWord(String[] wordlist, Master master) {

        Random random = new Random();
        List<String> options = Arrays.asList(wordlist);

        int trials = 10;

        while (trials > 0) {

            int guess = random.nextInt(options.size());
            String candidate = options.get(guess);
            int count = master.guess(candidate);
            if (count == 6) {
                //System.out.println("Secret: " + candidate);
                break;
            }

            List<String> nextOptions = new ArrayList<>();
            for (var word : options) {
                if (countMatches(word, candidate) == count)
                    nextOptions.add(word);
            }

            options = nextOptions;
            trials--;
        }
    }

    public int countMatches(String word1, String word2) {

        int size = Math.min(word1.length(), word2.length());
        int count = 0;
        for (int index = 0; index < size; index++) {
            if (word1.charAt(index) == word2.charAt(index))
                count++;
        }

        return count;
    }

    public class Master {

        public int guess(String word) {
            System.out.println("Guess: " + word);
            return countMatches(word, "abcdef");
        }
    }

    public static void main(String[] args) {

        String[] words = {"abcdex", "xbcdef", "abxdef", "xyzdef", "accbzz", "ayufgf", "abcdef"};

        GuessWord ob = new GuessWord();
        ob.findSecretWord(words, ob.new Master());
    }
}
