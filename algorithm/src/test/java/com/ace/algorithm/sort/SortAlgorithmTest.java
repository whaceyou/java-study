package com.ace.algorithm.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SortAlgorithmTest {

    SortAlgorithm sortAlgorithm = new SortAlgorithm();
    int[] arr = new int[]{99,55,63,21,78,97,2,3,18,6,5,1,0,101,999};


    @Test
    @DisplayName("selectSort")
    void selectSort() {
        sortAlgorithm.selectSort(arr);
    }

    @Test
    @DisplayName("bubbleSort")
    void bubbleSort() {
        sortAlgorithm.bubbleSort(arr);
    }

    @Test
    @DisplayName("insertSort")
    void insertSort() {
        sortAlgorithm.insertSort(arr);
    }

    @Test
    @DisplayName("mergeSort")
    void mergeSort() {
        sortAlgorithm.mergeSort(arr);
    }

    @Test
    @DisplayName("quickSort")
    void quickSort() {
        sortAlgorithm.quickSort(arr);
    }

    @Test
    @DisplayName("heapSort")
    void heapSort() {
        sortAlgorithm.heapSort(arr);
    }

    @Test
    @DisplayName("heapSortUseJdkSmall")
    void heapSortUseJdkSmall() {
        sortAlgorithm.heapSortUseJdkSmall(arr);
    }
}