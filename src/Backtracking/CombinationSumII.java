package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    /**
     * Combination Sum II Algorithm (Backtracking without Repetition):
     * Find all unique combinations where candidates sum to target.
     * Each number can be used only once, but input may contain duplicates.
     * 
     * Key differences from Combination Sum I:
     * 1. Sort array to group duplicates together
     * 2. Skip duplicate elements at same recursion level: if (i > idx && candidates[i] == candidates[i-1])
     * 3. Move to next index (i+1) instead of staying at same index
     * 
     * Duplicate handling: [1,1,2,5,6,7,10], target=8
     * At level 0: use first 1, skip second 1
     * At level 1: can use second 1 if first 1 was chosen
     * 
     * Time: O(2^N), Space: O(target/min_candidate) for recursion
     */
    private static List<List<Integer>> answer;

    public static void backtracking(int[] candidates, List<Integer> temp, int sum, int target, int idx) {
        // Base case: if sum reaches or exceeds target
        if (sum >= target) {
            // If exact match, add current combination to answer
            if (sum == target) {
                answer.add(new ArrayList<>(temp));
            }
            return; // Prune: stop exploring this branch
        }

        // Try each candidate starting from idx
        for (int i = idx; i < candidates.length; i++) {
            // Skip duplicates at same recursion level to avoid duplicate combinations
            // Example: [1,1,2] - at level 0, use first 1, skip second 1
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            // Only proceed if candidate doesn't exceed remaining target
            if (candidates[i] <= target) {
                // Include current candidate
                temp.add(candidates[i]);
                // Recurse with i+1 (each number used only once)
                backtracking(candidates, temp, sum + candidates[i], target, i + 1);
                // Backtrack: remove last added candidate
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<Integer>> combinationSumII(int[] candidates, int target) {
        answer = new ArrayList<List<Integer>>();
        // Sort to group duplicates together for easier skipping
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        backtracking(candidates, temp, 0, target, 0);
        return answer;
    }

    public static void main(String[] args) {
        int[] input = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(combinationSumII(input, target));
    }
}
