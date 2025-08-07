package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    private static List<List<Integer>> answer;

    public static void backtracking(int[] candidates, List<Integer> temp) {
        if (temp.size() == candidates.length) {
            answer.add(new ArrayList<>(temp));
            return;
        }

        for (int candidate : candidates) {
            if (temp.contains(candidate)) continue;
            temp.add(candidate);
            backtracking(candidates, temp);
            temp.remove(temp.size() - 1);
        }
        return;
    }

    public static List<List<Integer>> permute(int[] candidates) {
        answer = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        backtracking(candidates, temp);
        return answer;
    }

    public static void main(String[] args) {
        int[] input = {1, 2};
        System.out.println(permute(input));
    }
}
