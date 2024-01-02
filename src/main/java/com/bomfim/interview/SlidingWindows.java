package com.bomfim.interview;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SlidingWindows {

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.println("Length of Longest Substring (s1): " + lengthOfLongestSubstring(s1));
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        Set<Character> uniqueCharacters = new HashSet<>();

        while (right < s.length()) {
            if (!uniqueCharacters.contains(s.charAt(right))) {
                uniqueCharacters.add(s.charAt(right));
                right++;
                maxLength = Math.max(maxLength, right - left);
            } else {
                uniqueCharacters.remove(s.charAt(left));
                left++;
            }
        }

        return maxLength;
    }


    public static int maxSumSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        int maxSum = 0;
        int currentSum = 0;

        // Calculate the initial sum for the first window of size k
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        maxSum = currentSum;

        // Slide the window through the array to find the maximum sum
        for (int i = k; i < nums.length; i++) {
            currentSum = currentSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

}
