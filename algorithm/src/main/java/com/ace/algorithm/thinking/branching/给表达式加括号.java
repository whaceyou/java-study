package com.ace.algorithm.thinking.branching;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/different-ways-to-add-parentheses/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china">lieetcode 241</a>
 */
public class 给表达式加括号 {

    public static void main(String[] args) {
        System.out.println(differentWayToComputer("2*3-4*5"));
    }

    /**
     * @param input
     * @return
     */
    public static List<Integer> differentWayToComputer(String input) {
        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = differentWayToComputer(input.substring(0, i));
                List<Integer> right = differentWayToComputer(input.substring(i + 1));
                for (Integer l : left) {
                    for (Integer r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;

                        }
                    }
                }
            }
        }
        if (ways.isEmpty()) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }
}

