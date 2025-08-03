package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class NonDecreasingSubsequence {
    static List<List<Integer>> result;

    public static void backtrack(int[] nums, List<Integer> array, int idx) {
        if (array.size() > 1) {
            result.add(new ArrayList<>(array));
        }
        HashSet<Integer> set = new HashSet<>();

        for (int i = idx; i < nums.length; i++) {
            if ((array.isEmpty() || nums[i] >= array.get(array.size() - 1)) && !set.contains(nums[i])) {
                set.add(nums[i]);
                array.add(nums[i]);
                backtrack(nums, array, i + 1);
                array.remove(array.size() - 1);
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
