package com.bomfim.interview;

import java.util.Arrays;

public class QuickSort {

        public static void main(String[] args) {
            int[] array = {6, 2, 8, 5, 1, 7, 4, 4, 3, 6};

            System.out.println("Original Array: " + Arrays.toString(array));

            quickSort(array);

            System.out.println("Sorted Array: " + Arrays.toString(array));
        }

        public static void quickSort(int[] array) {
            if (array == null || array.length == 0) {
                return; // Nothing to sort
            }
            int low = 0;
            int high = array.length - 1;
            quickSortRecursive(array, low, high);
        }

        private static void quickSortRecursive(int[] array, int low, int high) {
            if (low < high) {
                int pivotIndex = partition(array, low, high);

                quickSortRecursive(array, low, pivotIndex - 1);
                quickSortRecursive(array, pivotIndex + 1, high);
            }
        }

        private static int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int pivotIndex = low - 1;

            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    pivotIndex++;
                    swap(array, pivotIndex, j);
                }
            }
            pivotIndex++;

            swap(array, pivotIndex, high);

            return pivotIndex;
        }

        private static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }


}
