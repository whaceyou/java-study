package com.ace.algorithm.sort;

import org.junit.jupiter.api.Test;

class BitOpsTest {

    BitOps ops = new BitOps();

    @Test
    void bitOps1() {
        int[] arr = new int[]{1, 2, 2, 3, 3, 4, 4, 4, 4, 1, 9};
        ops.bitOps1(arr);
    }

    @Test
    void bitOps2() {
        int[] arr = new int[]{1,2,2,3,3,4,4,4,4,1,1,9};
        ops.bitOps2(arr);
    }

    @Test
    void bitOps3() {
        System.out.println(ops.bitOps3(2 * 3));
        System.out.println(ops.bitOps3(2 * 5));
        System.out.println(ops.bitOps3(2 * 6));
        System.out.println(ops.bitOps3((long) Math.pow(2,8)));
        System.out.println(ops.bitOps3((long) Math.pow(2,1)));
        System.out.println(ops.bitOps3((long) Math.pow(2,3)));
        System.out.println(ops.bitOps3((long) Math.pow(2,62)));
    }
}