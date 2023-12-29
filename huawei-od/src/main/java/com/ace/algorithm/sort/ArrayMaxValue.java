package com.ace.algorithm.sort;

public class ArrayMaxValue {

    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,1,2,33};
        System.out.println(maxValue(arr));
    }


    public static int maxValue(int[] arr) {
        return maxValueUserRecursion(arr, 0, arr.length - 1);
    }

    /**
     * arr[leftIndex,rightIndex] 最大值  递归解法
     *
     * master公式  计算时间复杂度为  O(n)
     */
    public static int maxValueUserRecursion(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return arr[leftIndex];
        }
        int midIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
        int leftMax = maxValueUserRecursion(arr, leftIndex, midIndex);
        int rightMax = maxValueUserRecursion(arr, midIndex + 1, rightIndex);
        return Math.max(leftMax, rightMax);
    }
}
