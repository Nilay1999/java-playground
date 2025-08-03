package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    private static List<List<Integer>> result;

    public static void backtrack(int idx, int k, List<Integer> temp, int sum, int target) {
        if (temp.size() == k && sum == target) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = idx; i >= 1; i--) {
            temp.add(i);
            backtrack(i - 1, k, temp, sum + i, target);
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtrack(9, k, temp, 0, n);
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> res = combinationSum3(2, 18);
        System.out.println(res);
    }
}
