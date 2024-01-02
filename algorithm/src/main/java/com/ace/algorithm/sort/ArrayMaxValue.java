package com.ace.algorithm.sort;

import java.util.function.IntBinaryOperator;

public class ArrayMaxValue {

    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,1,2,33};
        int[] arr1 = new int[]{1,2,3,4,5,6};
        System.out.println(maxValue(arr));
        System.out.println(sumValue(arr1));
//        System.out.println(sumUserRecursion(arr1,0, arr.length - 1));
    }


    public static int maxValue(int[] arr) {
        return maxValueRecursion(arr, 0, arr.length - 1);
    }

    public static int sumValue(int[] arr) {
        return sumUseRecursion(arr, 0, arr.length - 1);
    }

    /**
     * arr[leftIndex,rightIndex] 最大值  递归解法
     *
     * master公式  计算时间复杂度为  O(n)
     */
    public static int maxValueRecursion(int[] arr, int leftIndex, int rightIndex) {
     return opsRecursion(arr,leftIndex,rightIndex,Math::max);
    }

    public static int sumUseRecursion(int[] arr, int leftIndex, int rightIndex) {
        return opsRecursion(arr,leftIndex,rightIndex,Integer::sum);
    }

    /**
     * arr[leftIndex,rightIndex] 最大值  递归解法
     * binaryOperator int 二元操作
     * master公式  计算时间复杂度为  O(n)
     */
    public static int opsRecursion(int[] arr, int leftIndex, int rightIndex, IntBinaryOperator binaryOperator) {
        if (leftIndex == rightIndex) {
            return arr[leftIndex];
        }
        int midIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
        int leftMax = opsRecursion(arr, leftIndex, midIndex,binaryOperator);
        int rightMax = opsRecursion(arr, midIndex + 1, rightIndex,binaryOperator);
        return binaryOperator.applyAsInt(leftMax,rightMax);
    }
}
