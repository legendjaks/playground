package com.jay.array;

public class SubarraySumK {

    // this works only if all values are +ve
    public int[] find(int[] arr, int k) {

        int[] p = new int[arr.length + 1];

        for (int index = 1; index < p.length; index++) {
            p[index] = p[index - 1] + arr[index - 1];
        }

        int i = 0;
        int j = 1;

        int len = Integer.MAX_VALUE;
        int pos = -1;

        while (j < p.length) {

            var sum = p[j] - p[i];

            if (sum == k) {
                if (len > j - i) {
                    len = j - i;
                    pos = i;
                }

                i = j;
                j++;
            } else if (sum < k) {
                j++;
            } else {
                i++;
            }
        }

        return (len == Integer.MAX_VALUE) ? new int[]{-1, -1} : new int[]{pos, len};
    }

    public static void main(String[] args) {

        int[] data = {4, 6, 1, 2, 2, 8, 3, 2};

        SubarraySumK ob = new SubarraySumK();
        int[] res = ob.find(data, 5);
        System.out.println("res: " + res[0] + " - " + res[1]);
    }
}
