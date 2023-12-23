package com.bomfim.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BinarySearch {

    public static void main(String[] args) {
        Book[] books = {new Book("1", "Clean code"),
                new Book("2", "Clean coder"),
                new Book("3", "Don Quixote"),
                new Book("4", "Hamlet"),
                new Book("5", "Lord of the Rings")
        };
        String targetElement = "Hamlet";

        int result = binarySearch(books, targetElement);

        // Displaying the result
        if (result != -1) {
            System.out.println("Book found ISBN: " + books[result]);
        } else {
            System.out.println("Book not found in the library.");
        }

    }

    // Binary Search algorithm
    private static int binarySearch(Book[] books, String target) {
        int left = 0; //first
        int right = books.length - 1; //last

        while (left != right) {
            int mid = left + (right - left) / 2;
            int comparison = target.compareTo(books[mid].title);

            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private static int binarySearchSmart(Book[] books, String targetElement) {
        return Collections.binarySearch(Arrays.asList(books), new Book("0", targetElement), Comparator.comparing(o -> o.title));
    }

    record Book(String isbn, String title) {
    }
}
