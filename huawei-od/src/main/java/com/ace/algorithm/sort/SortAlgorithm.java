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


    /**
     * 插入排序  O(n^2) 不稳定
     */
    public void insertSort(int[] arr) {
        // 0-0 有序
        // 0-i 想有序  往前比 (j = i-1)
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                while (arr[j] > arr[j + 1]) {
                    swap(arr, j + 1, j);
                }
            }
        }

        print(arr);
    }


    /**
     * 归并排序  O(N * logN)
     * @see  归并排序处理小和问题以及逆数对问题
     */
    public void mergeSort(int[] arr) {
        if (shouldSkip(arr)) return;
        mergeSort(arr, 0, arr.length - 1);
        print(arr);
    }

    private void mergeSort(int[] arr, int l, int r) {

        if (l == r) {
            return;
        }

        int midIndex = l + ((r - l) >> 1);
        mergeSort(arr, l, midIndex);
        mergeSort(arr, midIndex + 1, r);
        merge(arr, l, midIndex, r);

    }

    private void merge(int[] arr, int l, int midIndex, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = midIndex + 1;

        while (p1 <= midIndex && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 右边越界  说明右边已经放完了  将左边剩余的放入help 和下面情况的只会发生一种
        while (p1 <= midIndex) {
            help[i++] = arr[p1++];
        }

        // 左边越界  说明左边已经放完了  将右边剩余的放入help
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        System.arraycopy(help, 0, arr, l, help.length);
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
