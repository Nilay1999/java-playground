package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationII {
    private static List<List<Integer>> answer;
    private static Map<Integer, Integer> hashMap;

    public static void backtracking(int[] candidates, List<Integer> temp) {
        if (temp.size() == candidates.length) {
            answer.add(new ArrayList<>(temp));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 0) continue;

            temp.add(entry.getKey());
            hashMap.put(entry.getKey(), entry.getValue() - 1);
            backtracking(candidates, temp);
            temp.remove(temp.size() - 1);
            hashMap.put(entry.getKey(), entry.getValue() + 1);
        }
    }

    public static List<List<Integer>> permuteUnique(int[] candidates) {
        answer = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        hashMap = new HashMap<>();
        for (int candidate : candidates) {
            hashMap.put(candidate, hashMap.getOrDefault(candidate, 0) + 1);
        }

        backtracking(candidates, temp);
        return answer;
    }

    public static void main(String[] args) {
        int[] input = {1, 1, 2};
        // answer
        // [1,1,2]
        // [1,2,1]
        // [2,1,1]
        System.out.println(permuteUnique(input));
    }
}
