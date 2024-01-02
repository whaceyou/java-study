package com.ace.algorithm.sort;


import java.util.Arrays;
import java.util.PriorityQueue;

import static com.ace.algorithm.sort.ArrayOps.swap;
import static com.ace.algorithm.sort.HeapOps.heapify;

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
     * 插入排序  O(n^2)
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
     *
     * @see 归并排序处理小和问题以及逆数对问题
     */
    public void mergeSort(int[] arr) {
        if (shouldSkip(arr)) return;
        mergeSort(arr, 0, arr.length - 1);
        print(arr);
    }

    /**
     * 快速排序  (荷兰国旗问题)
     *
     * @see 荷兰国旗问题
     */
    public void quickSort(int[] arr) {
        if (shouldSkip(arr)) {return;}
        quickSort(arr, 0, arr.length - 1);
        print(arr);
    }

    /**
     * 堆排序  (荷兰国旗问题)
     *
     * @see 堆结构相关问题
     */
    public void heapSort(int[] arr) {
        if (shouldSkip(arr)) {return;}
        // 将数组变成大根堆
        // 方案一  O(NlogN)
//        for (int i = 0; i < arr.length; i++) { // O(N)
//            heapInsert(arr, i);// O(logN)
//        }

        // 方案二 O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize); // O(1)

        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }
        print(arr);
    }

    public void heapSortUseJdkSmall(int[] arr) {
        if (shouldSkip(arr)) {return;}
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int j : arr) {
            heap.add(j);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.remove();
        }
        print(arr);
    }

    private void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, (int) (l + Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0]);
            quickSort(arr, p[1], r);
        }

    }

    private int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;

        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, l, --more);
            } else {
                l++;
            }
        }
        swap(arr, r, more);
        return new int[]{less, more};
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

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    private static boolean shouldSkip(int[] arr) {
        return arr == null || arr.length < 2;
    }


}
