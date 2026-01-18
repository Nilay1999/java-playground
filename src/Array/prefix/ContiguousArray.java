package Array.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * Contiguous Array Algorithm (Prefix Sum with HashMap):
 * Find the maximum length of contiguous subarray with equal number of 0s and 1s.
 * 
 * PROBLEM DESCRIPTION:
 * - Given binary array with 0s and 1s
 * - Find longest contiguous subarray with equal count of 0s and 1s
 * 
 * ALGORITHM APPROACH:
 * 1. Convert 0s to -1s conceptually (treat as +1 and -1)
 * 2. Calculate prefix sum at each position
 * 3. If prefix sum repeats, subarray between those indices has equal 0s and 1s
 * 4. Use HashMap to store first occurrence of each prefix sum
 * 5. Track maximum length found
 * 
 * KEY INSIGHTS:
 * - Equal 0s and 1s means: count(0) = count(1)
 * - If we treat 0 as -1 and 1 as +1, equal counts means sum = 0
 * - Same prefix sum at two indices means subarray between them sums to 0
 * - Store first occurrence to maximize subarray length
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: nums = [0, 1, 1, 1, 1, 0, 0, 0, 1]
 * Index:    0  1  2  3  4  5  6  7  8
 * Convert:  -1 +1 +1 +1 +1 -1 -1 -1 +1
 * Prefix:   -1 0  1  2  3  2  1  0  1
 * 
 * At index 1: prefix = 0, length = 1 - (-1) = 2 (subarray [0,1])
 * At index 3: prefix = 2, first seen at index 4, length = 4 - 3 = 1
 * At index 4: prefix = 3, first occurrence
 * At index 5: prefix = 2, seen before at index 4, length = 5 - 4 = 1
 * At index 7: prefix = 0, seen before at index 1, length = 7 - 1 = 6
 * Maximum length = 6
 * 
 * Time: O(n), Space: O(n) for HashMap
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int max = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum += -1;
            } else {
                sum += 1;
            }
            if (map.get(sum) != null) {
                max = Math.max(max, i - map.get(sum));
            }
            map.put(sum, i);
        }

        return max;
    }

    public static void main(String[] args) {
        int nums[] = { 0, 1, 1, 1, 1, 0, 0, 0, 1 };
        System.out.println(new ContiguousArray().findMaxLength(nums));
    }
}
