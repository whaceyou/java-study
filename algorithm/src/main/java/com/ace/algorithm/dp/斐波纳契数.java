package com.ace.algorithm.dp;

public class 斐波纳契数 {

    public static void main(String[] args) {


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
