package com.ace.od;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class HJ8合并表记录 {
    public static void main(String[] args) {
        v1UseTreeMap();
    }

    // fix this
    private static void v2UseSelf() {
        Scanner scanner = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int count = scanner.nextInt();
        if (count > 500) {
            return;
        }
        int[] r = new int[11111112];
        for (int i = 0; i < count; i++) {
            int k = scanner.nextInt();
            int v = scanner.nextInt();
            r[k] = r[k] + v;
        }

        for (int i = 0; i < r.length; i++) {
            if (r[i] > 0) {
                System.out.println(i + " " + r[i]);
            }
        }
    }

    private static void v1UseTreeMap() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < count; i++) {
            int k = scanner.nextInt();
            int v = scanner.nextInt();
            map.merge(k, v, Integer::sum);
        }
        map.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
