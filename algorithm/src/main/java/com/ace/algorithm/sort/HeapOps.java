package com.ace.algorithm.sort;

import java.util.PriorityQueue;

import static com.ace.algorithm.sort.ArrayOps.swap;

/**
 * 堆 数据结构相关操作
 * 大根堆: 每个节点 比自己所有自己都要大   root节点必定是最大的
 * heapInsert  向上  大根堆化
 * heapify:    向下
 */

public class HeapOps {

    /**
     * 构建(调整)大根堆结构  往下操作
     */
    public static void heapify(int[] arr, int beginIndex, int heapSize) {
        int left = (beginIndex << 1) + 1;
        // 存在子节点的index  left < heapSize 说明存在左节点,亦可以理解为存在子节点
        while (left < heapSize) {
            // 选择 左子节点和右子节点(可能不存在) 中大的下标
            int largestIndex = (left + 1) < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 选择当前节点和子节点中大的
            largestIndex = arr[largestIndex] > arr[beginIndex] ? largestIndex : beginIndex;

            if (largestIndex == beginIndex) {
                break;
            }
            swap(arr, largestIndex, beginIndex);
            beginIndex = largestIndex;
            left = (beginIndex << 1) + 1;
        }
    }


    /**
     * 构建(调整)大根堆结构  往上操作
     * 某个数出现在 index处,网上异动
     */
    public static void heapInsert(int[] arr, int index) {
        int p;
        while (arr[index] > arr[p = ((Math.max((index - 1), 0)) >> 1)]) {
            swap(arr, index, p);
            index = p;
        }
    }

    /**
     *
     * @param args
     */


    public static void main(String[] args) {
        int i = (10 - 11) / 2;
        int j = ((10 - 11) >> 1);
        System.out.println(i);
        System.out.println(j);

//        PriorityQueue<Integer> integers = new PriorityQueue<>();
//        integers.offer(1);
//        integers.poll();
    }
}
