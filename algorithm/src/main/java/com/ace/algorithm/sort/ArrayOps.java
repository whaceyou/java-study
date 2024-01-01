package com.ace.algorithm.sort;

public class ArrayOps {
    public static void swap(int[] arr, int first, int next) {
        if (first != next) {
            arr[first] = arr[first] ^ arr[next];
            arr[next] = arr[first] ^ arr[next];
            arr[first] = arr[first] ^ arr[next];
        }
//        int temp = arr[first];
//        arr[first] = arr[next];
//        arr[next] = temp;

    }
}