package com.ace.algorithm.sort;

/**
 * <a href="https://segmentfault.com/a/1190000041029315">归并排序：解决小和、逆序对问题</a>
 * <br/>
 * <p>
 * 小和问题:
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。例如：
 * 对于数组[1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 * </p>
 * <br/>
 * <p>
 * 逆数对问题:
 * 设有一个数组 [a1, a2, a3,... an]，对于数组中任意两个元素ai，aj，若i<j，ai>aj，则说明ai和aj是一对逆序对。求一个给定数组的逆序对个数。
 * 3 5 2 1 0 4 9
 * 所有逆序对是：(3,2)，(3,1)，(3,0)，(5,2)，(5,1)，(5,0)，(5,4)，(2,1)，(2,0)，(1,0)。逆序对个数为10。
 * 合并的时候，从右往左合并，(此时右组位置 - mid位置) 的累加和 即是逆序对个数。
 * 这又咋和归并排序联系上的呢，仔细想想，在左组和右组merge的时候，会比较数的大小，但是我要找到的是右边更小的，
 * 所以可以采用从右往左合并的方式；同时在处理相等的时候，需要先拷贝右组的，这样才能准确找出右组小的个数。
 * </p>
 */
public class 归并排序处理小和问题以及逆数对问题 {


    private static final int[] small1 = new int[]{1, 3, 4, 2, 5, 0, 8};
    private static final int[] small2 = new int[]{1, 3, 4, 2, 5, 0, 8};
    private static final int[] inversePare1 = new int[]{3, 5, 2, 1, 0, 4, 9};
    private static final StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
//        System.out.println(smallSumByMergeSort(small1));
//        System.out.println(smallSumUseViolence(small2));
        System.out.println(inversePareByMergeSort(inversePare1));
        System.out.println(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
    }

    public static int inversePareByMergeSort(int[] arr) {
        return inversePareByMergeSort(arr, 0, arr.length - 1);
    }

    public static int inversePareByMergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return inversePareByMergeSort(arr, l, mid) + inversePareByMergeSort(arr, mid + 1, r) + inversePareByMerge(arr, l, mid, r);

    }

    private static int inversePareByMerge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = help.length - 1;
        int left = m;
        int right = r;
        int sum = 0;
        while (left >= l && right >= (m + 1)) {
            if (arr[left] > arr[right]) {
                sum = sum + (right - m);
                for (int j = m + 1; j <= right; j++) {
                    stringBuilder.append("(").append(arr[left]).append(",").append(arr[j]).append("),");
                }
                help[i--] = arr[left--];
            } else {
                help[i--] = arr[right--];
            }
        }

        while (left >= l) {
            help[i--] = arr[left--];
        }
        while (right >= m + 1) {
            help[i--] = arr[right--];
        }

        System.arraycopy(help, 0, arr, l, help.length);


        return sum;
    }


    /**
     * 小和问题 暴力解法 O(n^2)
     */
    public static int smallSumUseViolence(int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    /**
     * 小和问题 归并排序 O(logN)
     */
    public static int smallSumByMergeSort(int[] arr) {

        return smallSumByMergeSort(arr, 0, arr.length - 1);
    }

    public static int smallSumByMergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return smallSumByMergeSort(arr, l, m) + smallSumByMergeSort(arr, m + 1, r) + smallSumMerge(arr, l, m, r);
    }

    private static int smallSumMerge(int[] arr, int l, int m, int r) {
        int sum = 0;
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p = l;
        int q = m + 1;

        while (p <= m && q <= r) {
            sum += arr[p] < arr[q] ? arr[p] * (r - q + 1) : 0;
            temp[i++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        }

        while (p <= m) {
            temp[i++] = arr[p++];
        }

        while (q <= r) {
            temp[i++] = arr[q++];
        }

//        for (int j = 0; j < temp.length; j++) {
//            arr[l + j] = temp[j];
//        }

        System.arraycopy(temp, 0, arr, l, temp.length);
        return sum;
    }

}
