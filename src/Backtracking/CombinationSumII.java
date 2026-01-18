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
        if (sum >= target) {
            if (sum == target) {
                answer.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] <= target) {
                temp.add(candidates[i]);
                backtracking(candidates, temp, sum + candidates[i], target, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<Integer>> combinationSumII(int[] candidates, int target) {
        answer = new ArrayList<List<Integer>>();
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
