package com.ace.od;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HJ26_字符串排序 {
    public static void main(String[] args) {
        f1();
    }


    private static void f1() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            List<Character> letters = str.chars()
                    .filter(Character::isLetter)
                    .mapToObj(o -> (char) o)
                    .sorted(Comparator.comparingInt(Character::toLowerCase))
                    .collect(Collectors.toList());

            StringBuilder sb = new StringBuilder();

            for (int i = 0, j = 0; i < str.length(); i++) {
                if (Character.isLetter(str.charAt(i))) {
                    sb.append(letters.get(j++));
                } else {
                    sb.append(str.charAt(i));
                }
            }

            System.out.println(sb);

        }
    }
}
