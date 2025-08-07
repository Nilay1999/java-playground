package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
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
