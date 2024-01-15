package com.ace.algorithm.dp;

import static com.ace.utils.Utils.print2DArray;

public class 背包问题 {

    public static void main(String[] args) {
        int[] w = {3, 2, 4, 7, 3, 1, 7};
        int[] v = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        // 42
//        System.out.println(maxValue(w, v, bag));
        System.out.println(dpWay(w, v, bag));
    }


    public static int dpWay(int[] w, int[] v, int bag) {
        // 行 index(货物下标)                范围:  0~n
        // 列 restBag(剩余重量)      范围: 负~bag
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];
        // dp[n][...] = 0  第n行为0
        for (int index = n - 1; index >= 0; index--) {
            for (int restBag = 0; restBag <= bag; restBag++) {
                int p1 = dp[index + 1][restBag];
                int p2 = 0;
                int next = restBag - w[index] < 0 ? -1 : dp[index + 1][restBag - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][restBag] = Math.max(p1,p2);
            }

        }

        print2DArray(dp);
        return dp[0][bag];
    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process1(w, v, 0, bag);
    }

    /**
     * @return 最大价值
     */
    private static int process1(int[] w, int[] v, int index, int restBag) {
        // base case
        if (restBag < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 不要当前货物的情况 价值为 找下一个
        int p1 = process1(w, v, index + 1, restBag);

        // 要当前货物
        int p2 = 0;
        int next = process1(w, v, index + 1, restBag - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }

        return Math.max(p1, p2);


    }
}
