package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets Algorithm (Backtracking - Include/Exclude Pattern):
 * Generate all possible subsets (power set) of an array.
 * 
 * PROBLEM DESCRIPTION:
 * - Given array of distinct integers
 * - Generate all possible subsets (including empty set)
 * - Total subsets = 2^n
 * 
 * BACKTRACKING STRATEGY:
 * 1. For each element, make binary choice: include or exclude
 * 2. Include: add element to current subset, recurse
 * 3. Exclude: don't add element, recurse
 * 4. Base case: processed all elements → add subset to result
 * 
 * DECISION TREE:
 * Example: [1,2,3]
 *                    []
 *          /                  \
 *       [1]                    []
 *      /    \                /    \
 *   [1,2]   [1]           [2]     []
 *   /  \    /  \          /  \    /  \
 * [1,2,3][1,2][1,3][1] [2,3][2] [3] []
 * 
 * Result: [[], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
 * 
 * Time: O(2^n × n), Space: O(n) for recursion depth
 */
public class Subsets {
    private static List<List<Integer>> result;

    public static void backtrack(int idx, int[] nums, List<Integer> temp) {
        // Base case: processed all elements
        if (idx == nums.length) {
            result.add(new ArrayList<Integer>(temp)); // add current subset
            return;
        }

        // Choice 1: Include current element
        temp.add(nums[idx]);
        backtrack(idx + 1, nums, temp);
        
        // Backtrack: remove element
        temp.remove(temp.size() - 1);
        
        // Choice 2: Exclude current element
        backtrack(idx + 1, nums, temp);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        backtrack(0, nums, temp);
        return result;
    }

    public static void main(String args[]) {
        int[] array = {1, 2, 3, 4};
        result = Subsets.subsets(array);
        System.out.println(result);

    }
}
