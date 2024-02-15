package com.ace.algorithm.dp;

import java.util.Scanner;

/**
 * 合唱排序顺序：矮 - 高 -矮
 */
public class HJ24_合唱队 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int total = sc.nextInt();
            int[] arr = new int[total];
            for (int i = 0; i < total; i++) {
                arr[i] = sc.nextInt();
            }
            int[] l = left(arr);  //求整个数组中，上升子序列长度，并存入l[]数组中
            int[] r = right(arr);  //求整个数组中，递减子序列长度，并存入r[]数组中
            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                if (max < (l[i] + r[i] - 1)) {
                    max = l[i] + r[i] - 1;
                }
            }
            System.out.println(total - max);
        }
    }

    //求上升子序列长度
    private static int[] left(int[] arr) {
        int[] left = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            left[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }
        return left;

    }

    //求右序列的递减子序列长度
    private static int[] right(int[] arr) {
        int[] right = new int[arr.length];
        for (int i = arr.length - 1; i > 0; i--) {
            right[i] = 1;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }
        return right;
    }


}

