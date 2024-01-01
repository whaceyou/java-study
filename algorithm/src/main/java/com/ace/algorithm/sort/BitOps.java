package com.ace.algorithm.sort;

/**
 * 位运算
 * <p>
 * 异或: 相同为0,不同为1
 * 性质: 1: 满足交换律和结合律 2: 0^N = N 3: N^N = 0
 * 可以理解为: 无进位相加
 * </p>
 */
public class BitOps {

    /**
     * 异或: 一组数中 只有一种数是奇数个  其他的都为偶数个
     * 找出这种是奇数个的数
     * 例子: [1,2,2,3,3,4,4,4,4,1,1]
     * 则是1
     */
    public void bitOps1(int[] arr) {
        int result = 0;
        for (int j : arr) {
            result ^= j;
        }
        System.out.println(result);

    }

    /**
     * 异或: 一组数中 只有2种数是奇数个  其他的都为偶数个
     * 找出这2种是奇数个的数
     * 例子: [1,2,2,3,3,4,4,4,4,1,1,9]
     * 则是1,9
     */
    public void bitOps2(int[] arr) {
        int aAndB = 0;
        for (int j : arr) {
            aAndB ^= j;
        }
        // aAndB = a^b
        // 因为 a!=b 可以断定  aAndB != 0 则 aAndB的二进制上必定有一位1
        // java 位运算  把一个不等于0的数  最右侧的1提取出来 是  n & -n = n & (~n +1 )
        int rightOne = aAndB & -aAndB;

        int a = 0;
        for (int j : arr) {

            if ((j & rightOne) == 0) {
                a ^= j;
            }
        }
        System.out.println(a + "--" + (aAndB ^ a));
    }

    /**
     * 判断一个数是不是2的n次方
     *
     * @param num
     */
    public boolean bitOps3(long num) {
        return num > 0 && (num & num - 1) == 0;
    }
}
