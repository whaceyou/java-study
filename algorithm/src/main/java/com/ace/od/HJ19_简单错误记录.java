package com.ace.od;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class HJ19_简单错误记录 {
    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String str;
        LinkedHashMap<String, Integer> data = new LinkedHashMap<>();
        while ((str = buffer.readLine()) != null) {
            int idx1 = str.lastIndexOf(" ");
            int idx2 = str.lastIndexOf("\\");
            String key = (idx1 - idx2) > 16 ? str.substring(idx1 - 16) : str.substring(idx2 + 1);
            data.merge(key,1,Integer::sum);
        }

        int count = 0;
        for (String key : data.keySet()) {
            count++;
            if (count > (data.size() - 8)) {
                System.out.println(key + " " + data.get(key));
            }
        }
    }


}