package com.ace.od;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 描述
 * 输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * 保证输入的整数最后一位不是 0 。
 * 数据范围：
 * 1≤n≤10^8
 * 输入描述：
 * 输入一个int型整数
 * 输出描述：
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 * 示例1
 * 输入：
 * 9876673
 * 复制
 * 输出：
 * 37689
 */
public class HJ9提取不重复的整数 {
    public static void main(String[] args) throws IOException {
//        v1();

        v2();
    }

    private static void v2() throws IOException {
        Scanner in = new Scanner(System.in);
        char[] str = in.nextLine().toCharArray();
        StringBuilder b = new StringBuilder();
        for (int i = str.length - 1; i >= 0; i--) {
            if (repeated(b, str[i])) {
                continue;
            }
            b.append(str[i]);
        }
        System.out.print(b);
    }

    private static boolean repeated(StringBuilder b, char str) {
        return b.lastIndexOf(String.valueOf(str)) != -1;
    }

    private static void v1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            Set<Integer> set = new HashSet<>();
            while (num != 0) {
                int x = num % 10;
                if (set.add(x)) {
                    System.out.print(x);
                }
                num = num / 10;
            }

        }
    }
}
