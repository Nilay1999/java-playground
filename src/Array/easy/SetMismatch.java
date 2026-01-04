package Array.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Set Mismatch Algorithm (Array with HashSet):
 * Given an array containing numbers from 1 to n, find the duplicate and missing
 * number.
 * The array has exactly one duplicate and one missing number.
 * 
 * PROBLEM DESCRIPTION:
 * - Array of size n contains numbers from 1 to n
 * - One number appears twice, one number is missing
 * - Find both the duplicate and missing numbers
 * 
 * ALGORITHM APPROACH:
 * 1. Calculate expected sum: n * (n + 1) / 2
 * 2. Traverse array and track current sum
 * 3. Use HashSet to detect duplicate number
 * 4. Calculate missing number using: missing = expected - (current - duplicate)
 * 
 * KEY INSIGHTS:
 * - Expected sum formula: sum of 1 to n = n * (n + 1) / 2
 * - If duplicate is d and missing is m: current_sum = expected_sum - m + d
 * - Therefore: m = expected_sum - (current_sum - d)
 * - HashSet efficiently detects first duplicate in O(1) time
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: nums = [1, 2, 2, 4], n = 4
 * 1. Expected sum = 4 * 5 / 2 = 10
 * 2. Traverse: sum = 1 + 2 + 2 + 4 = 9, duplicate = 2
 * 3. Missing = 10 - (9 - 2) = 10 - 7 = 3
 * 4. Return [2, 3]
 * 
 * Time: O(n), Space: O(n) for HashSet
 */
public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        // Calculate expected sum of numbers 1 to n
        int expectedTotal = n * (n + 1) / 2;

        int currentTotal = 0;
        int duplicate = -1;
        Set<Integer> set = new HashSet<>();

        // Traverse array to find duplicate and calculate current sum
        for (int num : nums) {
            currentTotal += num;
            // If add() returns false, number already exists (duplicate)
            if (!set.add(num)) {
                duplicate = num;
            }
        }

        // Calculate missing number: expected - (current - duplicate)
        // This works because: current = expected - missing + duplicate
        int missing = expectedTotal - (currentTotal - duplicate);
        return new int[] { duplicate, missing };
    }
}
