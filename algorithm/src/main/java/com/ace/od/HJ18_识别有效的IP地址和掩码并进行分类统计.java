package com.ace.od;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class HJ18_识别有效的IP地址和掩码并进行分类统计 {


    private static Predicate<String> p = Pattern.compile("^1+0+$").asPredicate();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        // 错误(IP错或者掩码错)的数量
        int w = 0;
        // 私有ip数量
        int p = 0;
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] ips = input.split("~");
            String[] ip = ips[0].split("\\.");
            String[] mask = ips[1].split("\\.");
            //  类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
            if (shouldSkip(ip)) {
                continue;
            }
            // 非法的ip或者非法的掩码 w++
            if (!isLegalIp(ip) || !isLegalMask(mask)) {
                w++;
                continue;
            }
            // 是私有ip
            if (isPrivateIp(ip)) {
                p++;
            }
            // A类
            int ipe1 = Integer.parseInt(ip[0]);
            if (ipe1 >= 1 && ipe1 <= 126) {
                a++;
            } else if (ipe1 >= 128 && ipe1 <= 191) {
                b++;
            } else if (ipe1 >= 192 && ipe1 <= 223) {
                c++;
            } else if (ipe1 >= 224 && ipe1 <= 239) {
                d++;
            } else if (ipe1 >= 240 && ipe1 <= 255) {
                e++;
            }

        }

        System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + w + " " +
                p);
    }


    private static boolean shouldSkip(String[] str) {
        int first = Integer.parseInt(str[0]);
        return first == 0 || first == 127;
    }


    private static boolean isLegalIp(String[] ips) {
        if (ips.length != 4) {
            return false;
        }
        for (String e : ips) {
            int x = Integer.parseInt(e);
            if (x < 0 || x > 255) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLegalMask(String[] mask) {
        if (mask.length != 4) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (String e : mask) {
            sb.append(String.format("%8s", Integer.toBinaryString(Integer.parseInt(e))).replace(' ', '0'));
        }
        String m = sb.toString();

        return p.test(m);
//        return m.indexOf("0") - m.lastIndexOf("1") == 1;
    }

    private static boolean isPrivateIp(String[] ip) {
        int first = Integer.parseInt(ip[0]);
        int second = Integer.parseInt(ip[1]);
        return first == 10
                || (first == 172 && (second >= 16 && second <= 31))
                || (first == 192 && second == 168);
    }

}