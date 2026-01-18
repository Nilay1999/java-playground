package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Permutations II Algorithm (Backtracking with Duplicates):
 * Generate all unique permutations of an array that may contain duplicates.
 * 
 * PROBLEM DESCRIPTION:
 * - Array may contain duplicate numbers
 * - Generate all unique permutations (no duplicate permutations)
 * 
 * ALGORITHM APPROACH:
 * 1. Use HashMap to track frequency of each number
 * 2. For each position, try all available numbers (frequency > 0)
 * 3. Decrement frequency when using a number
 * 4. Increment frequency when backtracking
 * 5. Base case: permutation length equals array length
 * 
 * KEY INSIGHT:
 * - HashMap automatically handles duplicates
 * - Only use a number if its frequency > 0
 * - This ensures each permutation is unique
 * 
 * Example: [1,1,2] → [[1,1,2], [1,2,1], [2,1,1]]
 * - HashMap: {1: 2, 2: 1}
 * - Use first 1, then second 1, then 2
 * - Automatically avoids duplicate permutations
 * 
 * Time: O(N! × N), Space: O(N) for HashMap and recursion
 */
public class PermutationII {
    private static List<List<Integer>> answer;
    private static Map<Integer, Integer> hashMap;

    public static void backtracking(int[] candidates, List<Integer> temp) {
        // Base case: permutation is complete
        if (temp.size() == candidates.length) {
            answer.add(new ArrayList<>(temp));
            return;
        }

        // Try each unique number that's still available
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            // Skip if this number is exhausted
            if (entry.getValue() == 0) continue;

            // Use this number
            temp.add(entry.getKey());
            // Decrement its frequency
            hashMap.put(entry.getKey(), entry.getValue() - 1);
            // Recurse to fill next position
            backtracking(candidates, temp);
            // Backtrack: remove number
            temp.remove(temp.size() - 1);
            // Restore frequency
            hashMap.put(entry.getKey(), entry.getValue() + 1);
        }
    }

    public static List<List<Integer>> permuteUnique(int[] candidates) {
        answer = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        hashMap = new HashMap<>();
        
        // Build frequency map
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
