package com.bomfim.interview;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {6, 2, 8, 5, 1, 7, 4, 4, 3, 6};

        System.out.println("Original Array: " + Arrays.toString(array));

        mergeSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void mergeSort(int[] array) {
        if (array.length <= 1) {
            return; // Array is already sorted
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // Recursively sort the left and right halves
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int leftIndex = 0, rightIndex = 0, k = 0;

        // Compare elements from left and right and merge into the array
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                array[k] = left[leftIndex++];
            } else {
                array[k] = right[rightIndex++];
            }
            k++;
        }

        // Copy remaining elements from left (if any)
        while (leftIndex < left.length) {
            array[k++] = left[leftIndex++];
        }

        // Copy remaining elements from right (if any)
        while (rightIndex < right.length) {
            array[k++] = right[rightIndex++];
        }
    }


}
