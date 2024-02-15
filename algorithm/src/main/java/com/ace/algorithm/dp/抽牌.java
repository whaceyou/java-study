package com.ace.algorithm.dp;

public class 抽牌 {


    public static void main(String[] args) {

    }


    public static int win1(int[] arr) {
        return Math.max(f1(arr,0, arr.length-1),g1(arr,0, arr.length-1));
    }


    /**
     * 先手函数
     *
     * @param arr arr
     * @return 总分数
     */
    public static int f1(int[] arr, int l, int r) {
        if (l == r) { // base case
            return arr[l];
        }

        // 第一种可能 先手选左边 再后手选右边
        int p1 = arr[l] + g1(arr, l + 1, r);
        // 第二种可能 先手选右边 再后手选左边
        int p2 = arr[r] + g1(arr, l, r - 1);


        return Math.max(p1, p2);


    }

    /**
     * 后手函数
     */
    private static int g1(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // 第一种可能 后手选左边 再先手选右边
        int p1 = f1(arr, l + 1, r);
        // 第二种可能 后手选右边 再先手选左边
        int p2 = f1(arr, l, r - 1);
        return Math.min(p1, p2);

    }


}
