package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Combination Sum III Algorithm (Backtracking with Constraints):
 * Find all combinations of k numbers that sum to n, using only numbers 1-9.
 * Each number can be used at most once.
 * 
 * PROBLEM CONSTRAINTS:
 * - Use only numbers 1 through 9
 * - Each number used at most once
 * - Exactly k numbers in combination
 * - Sum must equal n
 * 
 * BACKTRACKING STRATEGY:
 * 1. Start from 9 and work backwards (or 1 to 9)
 * 2. For each number, decide to include or skip
 * 3. Base case: k numbers selected AND sum equals target
 * 4. Prune: if sum exceeds target or too many numbers selected
 * 
 * Example: k=3, n=7 → [[1,2,4]]
 * Example: k=3, n=9 → [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * Time: O(C(9,k)) - combinations of k from 9 numbers
 * Space: O(k) for recursion depth
 */
public class CombinationSumIII {
    private static List<List<Integer>> result;

    public static void backtrack(int idx, int k, List<Integer> temp, int sum, int target) {
        // Base case: found valid combination
        if (temp.size() == k && sum == target) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        // Try numbers from idx down to 1
        for (int i = idx; i >= 1; i--) {
            // Include current number
            temp.add(i);
            // Recurse with next smaller number
            backtrack(i - 1, k, temp, sum + i, target);
            // Backtrack: remove last number
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        // Start from 9 (largest single digit)
        backtrack(9, k, temp, 0, n);
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = combinationSum3(2, 18);
        System.out.println(res);
    }
}
