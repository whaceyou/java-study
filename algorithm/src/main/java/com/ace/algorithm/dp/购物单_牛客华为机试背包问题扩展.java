package com.ace.algorithm.dp;

import com.ace.utils.Utils;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;

/**
 * <a href="https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&tqId=21239&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj&difficulty=undefined&judgeStatus=undefined&tags=&title=">购物单</a>
 * <br>
 */
public class 购物单_牛客华为机试背包问题扩展 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split(" ");

        int money = Integer.parseInt(strArr[0]);
        int count = Integer.parseInt(strArr[1]);
        Good[] goods = new Good[count + 1];
        for (int i = 1; i <= count; i++) {
            strArr = br.readLine().split(" ");
            int v = Integer.parseInt(strArr[0]);
            int p = Integer.parseInt(strArr[1]);
            int q = Integer.parseInt(strArr[2]);
            goods[i] = new Good(v, p, q);
        }

        for (int i = 1; i <= count; i++) {
            int q = goods[i].q;
            if (q > 0) {
                if (goods[q].a1 == -1) {
                    goods[q].a1 = i;
                } else {
                    goods[q].a2 = i;
                }
            }
        }


//        System.out.println(recursion(goods, 1, money));
        System.out.println(dp(goods, money));

    }

    public static int dp(Good[] goods, int money) {

        // 行 index       1 - goods.length
        // 列 restMoney   负 - money
        int n = goods.length;
        int[][] dp = new int[n + 1][money + 1];

        // base case
        // dp[n,...] = 0
        for (int index = n - 1; index >= 1; index--) {
            for (int restMoney = 0; restMoney <= money; restMoney++) {
                Good good = goods[index];
                if (!good.isMan()) { // 不是主件,填下一个
                    dp[index][restMoney] = dp[index + 1][restMoney];
                    continue;
                }
                int max;
                // 不要当前主件
                int p1 = dp[index + 1][restMoney];
                // 只要当前主件
                int p2 = 0;
                // 要当前主件 和 当前主件的附件1
                int p3 = 0;
                // 要当前主件 和 附件2
                int p4 = 0;
                // 要当前主件 和 当前主件的附件1,附件2
                int p5 = 0;
                // 附件1 index
                int a1 = good.a1;
                // 附件2 index
                int a2 = good.a2;
                // 只要当前主件
                // 检查下一个
                int next = (restMoney - good.v) < 0 ? -1 : dp[index + 1][restMoney - good.v];
                if (next != -1) {
                    p2 = good.p * good.v + next;
                }
                max = max(p1, p2);
                // 要当前主件 并且要当前主件的附件1(如果存在)
                if (a1 != -1) {
                    next = restMoney - good.v - goods[a1].v < 0 ? -1 : dp[index + 1][restMoney - good.v - goods[a1].v];
                    if (next != -1) {
                        p3 = good.p * good.v + next + goods[a1].p * goods[a1].v;
                    }
                }
                max = max(max, p3);
                // 要当前主件 和 附件2
                // 要当前主件 并且要当前主件的附件1(如果存在)
                if (a2 != -1) {
                    next = restMoney - good.v - goods[a2].v < 0 ? -1 : dp[index + 1][restMoney - good.v - goods[a2].v];
                    if (next != -1) {
                        p4 = good.p * good.v + next + goods[a2].p * goods[a2].v;
                    }
                }
                max = max(max, p4);
                if (a1 != -1 && a2 != -1) {
                    // 要当前主件 和 当前主件的附件1,附件2
                    next = restMoney - good.v - goods[a1].v - goods[a2].v < 0 ? -1 : dp[index + 1][restMoney - good.v - goods[a1].v - goods[a2].v];
                    if (next != -1) {
                        p5 = good.p * good.v + next + goods[a1].p * goods[a1].v + goods[a2].p * goods[a2].v;
                    }
                }

                dp[index][restMoney] = max(max, p5);

            }
        }
        Utils.print2DArray(dp);
        return dp[1][money];
    }

    /**
     * @param goods     货物
     * @param index     当前货物
     * @param restMoney 剩余的钱
     * @return 最大满意度
     */
    public static int recursion(Good[] goods, int index, int restMoney) {

        // base case
        if (restMoney < 0) {
            return -1;
        }

        if (index == goods.length) {
            return 0;
        }

        Good good = goods[index];

        if (!good.isMan()) { // 不是主件,选下一个
            return recursion(goods, index + 1, restMoney);
        }
        // 不要当前主件
        int p1 = recursion(goods, index + 1, restMoney);


        // 只要当前主件
        int p2 = 0;
        // 要当前主件 和 当前主件的附件1
        int p3 = 0;
        // 要当前主件 和 附件2
        int p4 = 0;
        // 要当前主件 和 当前主件的附件1,附件2
        int p5 = 0;
        // 附件1 index
        int a1 = good.a1;
        // 附件2 index
        int a2 = good.a2;
        // 只要当前主件
        // 检查下一个
        int next = recursion(goods, index + 1, restMoney - good.v);
        if (next != -1) {
            p2 = good.p * good.v + next;
        }

        // 要当前主件 并且要当前主件的附件1(如果存在)
        if (a1 != -1) {
            next = recursion(goods, index + 1, restMoney - good.v - goods[a1].v);
            if (next != -1) {
                p3 = good.p * good.v + goods[a1].p * goods[a1].v + next;
            }
        }

        // 要当前主件 和 附件2
        // 要当前主件 并且要当前主件的附件1(如果存在)
        if (a2 != -1) {
            next = recursion(goods, index + 1, restMoney - good.v - goods[a2].v);
            if (next != -1) {
                p4 = good.p * good.v + goods[a2].p * goods[a2].v + next;
            }
        }

        if (a1 != -1 && a2 != -1) {
            // 要当前主件 和 当前主件的附件1,附件2
            next = recursion(goods, index + 1, restMoney - good.v - goods[a1].v - goods[a2].v);
            if (next != -1) {
                p5 = good.p * good.v + goods[a1].p * goods[a1].v + goods[a2].p * goods[a2].v + next;
            }
        }


        return max(max(max(max(p1, p2), p3), p4), p5);
    }

    @Setter
    public static class Good {
        /**
         * 价格
         */
        int v;

        /**
         * 重要度
         */
        int p;

        /**
         * 是否为附件
         */
        int q;

        /**
         * 附件1
         */
        int a1 = -1;

        /**
         * 附件二
         */
        int a2 = -1;

        Good(int v, int p, int q) {
            this.v = v;
            this.p = p;
            this.q = q;
        }

        Good() {
        }


        public boolean isMan() {
            return q == 0;
        }

    }
}



