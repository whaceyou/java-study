package com.ace.algorithm.sort;

import java.util.Arrays;

/**
 * 给定一个整数数组和一个值M（存在于原数组中），
 * 要求把数组中小于M的元素放到数组的左边，等于M的元素放到数组的中间，大于M的元素放到数组的右边，
 * 最终返回一个整数数组，只有两个值，0位置是等于M的数组部分的左下标值、1位置是等于M的数组部分的右下标值。
 * 进一步抽象为更加通用的形式是下面这样的：
 * 给定数组arr，将[l, r]范围内的数（当然默认是 [ 0 - arr.length - 1 ]），
 * 小于arr[r]（这里直接取数组最右边的值进行比较）放到数组左边，等于arr[r]放到数组中间，
 * 大于arr[r]放到数组右边。最后返回等于arr[r]的左, 右下标值。
 */
public class 荷兰国旗问题 {

    static int[] arr = new int[]{99,55,63,21,78,97,2,3,18,6,5,1,0,101,5,999};
    public static void main(String[] args) {
        int[] solution = solution(arr, 5);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(solution));
    }


    public static int[] solution(int[] arr, int num) {
        int i = 0;
        int less = i - 1;
        int more = arr.length;
        while (i < more) {
            if (arr[i] < num) {
                SortAlgorithm.swap(arr, ++less, i++);
            } else if (arr[i] > num) {
                SortAlgorithm.swap(arr, --more, i);
            } else {
                i++;
            }
        }

        return new int[]{less + 1, more - 1};

    }
}
