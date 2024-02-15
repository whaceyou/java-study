package com.ace.algorithm.dp;

import java.util.stream.IntStream;

public class 斐波纳契数 {

    public static void main(String[] args) {



    }


    private static int fDp(int i) {
        int[] dp = new int[i + 1];
        dp[1] = 1;
        for (int j = 2; j <= i ; j++) {
            dp[j] = dp[j-1] + dp[j-2];
        }
        return dp[i];
    }

    private static int fDpCache(int i, int[] dp) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = fDpCache(i - 1, dp) + fDpCache(i - 2, dp);
        dp[i] = ans;
        return ans;
    }

    /**
     * dp(i) = dp(i-1) + dp(i-2)
     *
     * @param x x
     * @return r
     */
    private static int f(int x) {
        int a = 0;
        int b = 1;
        int r = 0;
        for (int i = 2; i < x; i++) {
            r = a + b;
            a = b;
            b = r;
        }

        return r;

    }

    /**
     * dp(i) = dp(i-1) + dp(i-2)
     *
     * @param x x
     * @return r
     */
    private static int f1(int x) {
        return switch (x) {
            case 0 -> 0;
            case 1 -> 1;
            default -> f(x - 1) + f(x - 2);
        };

    }
}
