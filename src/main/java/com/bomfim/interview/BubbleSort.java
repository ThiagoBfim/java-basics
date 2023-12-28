package com.bomfim.interview;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {6, 2, 8, 5, 1, 7, 4, 4, 3, 6};

        System.out.println("Original Array: " + Arrays.toString(array));

        bubbleSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                var current = j;
                var next = j + 1;
                if (array[current] > array[next]) {
                    swap(array, current, next);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
