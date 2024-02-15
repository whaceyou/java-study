package com.ace.od;

import java.util.*;
import java.util.function.*;
import java.util.regex.*;


// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class HJ17坐标移动 {
    public static Predicate<String> p = Pattern.compile("^[ASWD][0-9]{1,2}$").asPredicate();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            String[] strs = str.split(";");
            int x = 0;
            int y = 0;
            for (String c : strs) {
                if (illegal(c)) {
                    continue;
                }
                String adws = c.substring(0, 1);
                int offset = Integer.parseInt(c.substring(1));
                switch (adws) {
                    case "A":
                        x = x - offset;
                        break;
                    case "D":
                        x = x + offset;
                        break;
                    case "S":
                        y = y - offset;
                        break;
                    case "W":
                        y = y + offset;
                        break;
                }
            }
            System.out.println("(" + x + "," + y + ")");
        }
    }


    private static boolean illegal(String str) {
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return true;
        }
        return !p.test(str);

    }
}