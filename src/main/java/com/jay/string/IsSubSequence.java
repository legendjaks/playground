package com.jay.string;

public class IsSubSequence {

    public boolean isSubsequence(String s, String t) {

        if (s.length() > t.length()) return false;

        if (s.length() == 0 && t.length() == 0) return true;

        if (s.length() == 0) return true;

        int i = 0, j = 0;

        while (i < s.length() && j < t.length()) {

            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }

            j++;
        }

        return i >= s.length();
    }

    public static void main(String[] args) {
        IsSubSequence ob = new IsSubSequence();
        boolean res = ob.isSubsequence("axc", "ahbgdc");
        System.out.println("res: " + res);
    }
}
