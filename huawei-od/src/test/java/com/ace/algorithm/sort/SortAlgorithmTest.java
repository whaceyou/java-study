package com.ace.algorithm.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortAlgorithmTest {

    SortAlgorithm sortAlgorithm = new SortAlgorithm();
    int[] arr = new int[]{99,55,63,21,78,97,2,3,18,6,5,1,0,101,999};


    @Test
    void selectSort() {
        sortAlgorithm.selectSort(arr);
    }

    @Test
    void shouldSkip() {
        sortAlgorithm.bubbleSort(arr);
    }
}