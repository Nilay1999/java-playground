package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    private static List<List<Integer>> result;

    public static void backtrack(int idx, int[] nums, List<Integer> temp) {
        if (idx == nums.length) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        temp.add(nums[idx]);
        backtrack(idx + 1, nums, temp);
        temp.remove(temp.size() - 1);
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
