package com.ace.od;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 描述
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在 ASCII 码范围内( 0~127 ，包括 0 和 127 )，换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
 * 例如，对于字符串 abaca 而言，有 a、b、c 三种不同的字符，因此输出 3 。
 * 数据范围：
 * 1≤n≤500
 * 输入描述：
 * 输入一行没有空格的字符串。
 * 输出描述：
 * 输出 输入字符串 中范围在(0~127，包括0和127)字符的种数。
 * 示例1
 * 输入：
 * abc
 * 复制
 * 输出：
 * 3
 * 复制
 * 示例2
 * 输入：
 * aaa
 * 复制
 * 输出：
 * 1
 */
public class HJ10字符个数统计 {

    public static void main(String[] args) throws Exception{

        v2();
    }

    private static void v2() throws Exception{
        BufferedReader shuru = new BufferedReader(new InputStreamReader(System.in));
        String s = shuru.readLine();
        int[] a = new int[128];
        int count=0;
        for(int i=0;i<s.length();i++){
            char b = s.charAt(i);
            if(a[b]==0){
                count++;
                a[b]=1;
            }
        }
        System.out.println(count);
    }

    private static void v1() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean[] temp = new boolean[128];
        char c;
        while ((c = (char) reader.read()) != '\n') {
            temp[c] = true;
        }
        int r = 0;
        for (int i = 0; i < 128; i++) {
            if (temp[i]) r++;
        }
        System.out.println(r);
    }
}
