package com.ace.algorithm.sort;


import java.util.Arrays;

public class SortAlgorithm {

    /**
     * 选择排序 O(n^2)
     */
    public void selectSort(int[] arr) {
        if (shouldSkip(arr)) return;
        for (int i = 0; i < arr.length - 1; i++) {
            // 2次遍历 找最小下标
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
        print(arr);
    }

    /**
     * 冒泡排序  O(n^2)
     */
    public void bubbleSort(int[] arr) {
        if (shouldSkip(arr)) return;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        print(arr);
    }


    private static void swap(int[] arr, int first, int next) {
        if (first != next) {
            arr[first] = arr[first] ^ arr[next];
            arr[next] = arr[first] ^ arr[next];
            arr[first] = arr[first] ^ arr[next];
        }
//        int temp = arr[first];
//        arr[first] = arr[next];
//        arr[next] = temp;

    }

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static boolean shouldSkip(int[] arr) {
        return arr == null || arr.length < 2;
    }


}
