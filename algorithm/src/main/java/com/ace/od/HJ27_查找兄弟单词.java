package com.ace.od;

import java.util.*;

public class HJ27_查找兄弟单词 {


    public static void main(String[] args) {
        f1();
    }

    static void f1() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String[] arr = in.nextLine().split(" ");
            int num = Integer.parseInt(arr[0]);
            String[] t = Arrays.stream(arr).skip(1).limit(num).toArray(String[]::new);
            String target = arr[1 + num];
            int k = Integer.parseInt(arr[1 + num + 1]);

            StringBuilder sb = new StringBuilder();
            target.chars().sorted().forEach(sb::append);
            String string = sb.toString();


            List<String> result = new ArrayList<>();

            for (int i = 0; i < t.length; i++) {
                StringBuilder temp = new StringBuilder();
                t[i].chars().sorted().forEach(c -> temp.append((char) c));
                if (!t[i].equals(target) && temp.toString().contentEquals(string)) {
                    result.add(t[i]);
                }
            }

            result.sort(String::compareTo);

            int size = result.size();
            System.out.println(size);

            if (k <= size) {
                System.out.println(result.get(k - 1));
            }


        }
    }
}
