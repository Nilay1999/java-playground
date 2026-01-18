package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    /**
     * Permutations Algorithm (Backtracking):
     * Generate all possible arrangements of distinct integers.
     * 
     * Backtracking Strategy:
     * 1. For each position, try all unused numbers
     * 2. Mark number as used, recurse for next position
     * 3. Backtrack: unmark number and try next option
     * 4. Base case: permutation length equals input length
     * 
     * Example: [1,2,3] → [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * 
     * Optimization: Use boolean[] visited instead of contains() for O(1) lookup
     * 
     * Time: O(N! × N) - N! permutations, N to copy each
     * Space: O(N) for recursion depth + visited array
     */
    private static List<List<Integer>> answer;

    public static void backtracking(int[] candidates, List<Integer> temp) {
        // Base case: permutation is complete
        if (temp.size() == candidates.length) {
            answer.add(new ArrayList<>(temp)); // create copy
            return;
        }

        // Try each candidate number
        for (int candidate : candidates) {
            // Skip if already used in current permutation
            if (temp.contains(candidate)) continue;
            
            // Include candidate in current permutation
            temp.add(candidate);
            // Recurse to fill next position
            backtracking(candidates, temp);
            // Backtrack: remove candidate to try other options
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
