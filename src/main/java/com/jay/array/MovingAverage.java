package com.jay.array;

public class MovingAverage {

    private int window[];
    private int index;

    private double sum;

    private int len;

    public MovingAverage(int size) {
        this.window = new int[size];
        this.index = 0;

        this.sum = 0;
        this.len = 0;
    }

    public double next(int val) {

        int n = window.length;

        sum += val - window[index];
        window[index] = val;
        index++;
        index = index % n;

        len++;
        if(len > n)
            len = n;

        return sum/len;
    }

    public static void main(String[] args) {
        MovingAverage ob = new MovingAverage(3);
        int[] data = {1, 10, 3, 5};

        for(int d: data){
            var res = ob.next(d);
            System.out.println("res: " + res);
        }
    }
}
