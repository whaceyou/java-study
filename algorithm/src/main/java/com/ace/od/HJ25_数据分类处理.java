package com.ace.od;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class HJ25_数据分类处理 {
    public static void main(String[] args) {
        self();
    }

    private static void top() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            if (str.isEmpty()) continue;
            String[] I = str.split(" ");
            String[] temp = br.readLine().split(" ");
            long[] R = new long[Integer.parseInt(temp[0])];
            for (int i = 0; i < R.length; i++) R[i] = Long.parseLong(temp[i + 1]);
            Arrays.sort(R);
            StringBuilder res = new StringBuilder();
            int count = 0;
            for (int i = 0; i < R.length; i++) {
                if (i > 0 && R[i] == R[i - 1]) continue;
                String pattern = R[i] + "";
                int num = 0;
                StringBuilder index = new StringBuilder();
                for (int j = 1; j < I.length; j++) {
                    if (I[j].contains(pattern)) {
                        num++;
                        index.append(" ").append(j - 1).append(" ").append(I[j]);
                    }
                }
                if (num > 0) {
                    res.append(" ").append(R[i]).append(" ").append(num).append(index);
                    count += num * 2 + 2;
                }
            }
            System.out.println(count + res.toString());
        }
    }

    private static void self() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String[] IStr = Arrays.stream(in.nextLine().split(" ")).skip(1).toArray(String[]::new);
            String[] RStr = Arrays.stream(in.nextLine().split(" ")).skip(1).distinct().sorted(Comparator.comparingInt(Integer::parseInt)).toArray(String[]::new);
            StringBuilder sb = new StringBuilder();
            int total = 0;
            for (String num : RStr) {
                int count = 0;
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < IStr.length; j++) {
                    if (IStr[j].contains(num)) {
                        count++;
                        temp.append(j);
                        total++;
                        temp.append(" ");
                        temp.append(IStr[j]);
                        total++;
                        temp.append(" ");
                    }
                }
                if (count > 0) {
                    sb.append(num);
                    total++;
                    sb.append(" ");
                    sb.append(count);
                    total++;
                    sb.append(" ");
                    sb.append(temp);
                }
            }

            if (total > 0) {
                System.out.println(total + " " + sb);
            }

        }
    }
}
