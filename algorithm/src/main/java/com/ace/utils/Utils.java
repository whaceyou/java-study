package com.ace.utils;

public class Utils {

    /**
     * 可视化打印二维数组
     *
     * @param array 二维数组
     */
    public static void print2DArray(int[][] array) {
        int maxDigits = 0;

        // 遍历数组以找出最长数字的位数
        for (int[] row : array) {
            for (int num : row) {
                // 计算并更新当前数字的位数（加1是因为至少有一位数字）
                int digits = String.valueOf(num).length();
                maxDigits = Math.max(maxDigits, digits);
            }
        }

        // 格式化输出二维数组
        for (int[] ints : array) {
            for (int anInt : ints) {
                // 使用format方法确保每个数字都有足够的空格来对齐
                System.out.printf("%" + (-maxDigits) + "d ", anInt);
            }
            // 每打印完一行后换行
            System.out.println();
        }

        System.out.println("-------------------------------------------------------------------------------------------");
    }


    public static String paddingBinary(String e) {
        // 使用 String.format 进行补零操作
        return String.format("%8s", Integer.toBinaryString(Integer.parseInt(e))).replace(' ', '0');
    }


    public static void main(String[] args) {
    }
}
