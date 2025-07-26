package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    private static List<List<Integer>> answer;

    public static void backtracking(int[] candidates, List<Integer> temp, int sum, int target, int idx) {
        if (sum >= target) {
            if (sum == target) {
                answer.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                temp.add(candidates[i]);
                backtracking(candidates, temp, sum + candidates[i], target, i);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        answer = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        backtracking(candidates, temp, 0, target, 0);
        return answer;
    }

    public static void main(String[] args) {
        int[] input = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(input, target));
    }
}
