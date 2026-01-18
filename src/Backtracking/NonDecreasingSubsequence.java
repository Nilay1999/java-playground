package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Non-Decreasing Subsequences Algorithm (Backtracking with Duplicate Handling):
 * Find all non-decreasing subsequences of length >= 2 from an array.
 * 
 * PROBLEM CONSTRAINTS:
 * - Subsequence must be non-decreasing: nums[i] <= nums[i+1]
 * - Minimum length: 2 elements
 * - Array may contain duplicates
 * - Cannot sort array (must preserve original order)
 * 
 * BACKTRACKING STRATEGY:
 * 1. For each element, decide to include or skip
 * 2. Include only if: array is empty OR element >= last element
 * 3. Use HashSet at each level to avoid duplicate subsequences
 * 4. Add to result when length >= 2
 * 
 * DUPLICATE HANDLING:
 * - HashSet prevents using same value multiple times at same recursion level
 * - Example: [4,6,7,7] - at level 2, use first 7, skip second 7
 * 
 * Example: [4,6,7,7] → [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * 
 * Time: O(2^n), Space: O(n) for recursion depth
 */
public class NonDecreasingSubsequence {
    static List<List<Integer>> result;

    public static void backtrack(int[] nums, List<Integer> array, int idx) {
        // Add to result if subsequence has at least 2 elements
        if (array.size() > 1) {
            result.add(new ArrayList<>(array));
        }
        
        // Use HashSet to track used values at this recursion level
        // Prevents duplicate subsequences
        HashSet<Integer> set = new HashSet<>();

        // Try including each remaining element
        for (int i = idx; i < nums.length; i++) {
            // Include element if:
            // 1. Array is empty (first element) OR
            // 2. Element >= last element (maintains non-decreasing order) AND
            // 3. Element not used at this level (avoids duplicates)
            if ((array.isEmpty() || nums[i] >= array.get(array.size() - 1)) && !set.contains(nums[i])) {
                set.add(nums[i]); // mark as used at this level
                array.add(nums[i]); // include element
                backtrack(nums, array, i + 1); // recurse
                array.remove(array.size() - 1); // backtrack
            }
        }
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(nums, temp, 0);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 7, 7};
        System.out.println(findSubsequences(nums));
    }
}
