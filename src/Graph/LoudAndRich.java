package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loud and Rich Algorithm (DFS with Memoization):
 * For each person, find the quietest person who is richer than them.
 * 
 * PROBLEM DESCRIPTION:
 * - richer[i] = [a, b] means person a is richer than person b
 * - quiet[i] = quietness level of person i
 * - For each person i, find the quietest person among all richer than i
 * - Return array where answer[i] = quietest person richer than i
 * 
 * ALGORITHM APPROACH:
 * 1. Build reverse adjacency list: if a is richer than b, add edge b→a
 * 2. Use DFS with memoization to find answer for each person
 * 3. For person i, recursively find answer for all people richer than i
 * 4. Compare quietness levels and return the quietest
 * 5. Memoize results to avoid recalculating
 * 
 * KEY INSIGHTS:
 * - Reverse the richer relationship to build graph
 * - If person i has edge to person j, then j is richer than i
 * - DFS explores all people reachable (transitively richer)
 * - Memoization prevents redundant DFS calls
 * - answer[i] = person with minimum quiet value among all reachable from i
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: richer = [[1,0], [2,1], [3,1], [3,7], [4,3], [5,3], [6,3]]
 *          quiet = [3, 2, 5, 4, 6, 1, 7, 0]
 * 
 * Build reverse graph (who is richer than each person):
 * 0 → [1]        (1 is richer than 0)
 * 1 → [2, 3]     (2, 3 are richer than 1)
 * 2 → []
 * 3 → [4, 5, 6]  (4, 5, 6 are richer than 3)
 * 4 → []
 * 5 → []
 * 6 → []
 * 7 → [3]        (3 is richer than 7)
 * 
 * DFS for person 0:
 * - Explore person 1 (richer than 0)
 *   - Explore person 2: quiet[2] = 5, answer[2] = 2
 *   - Explore person 3: quiet[3] = 4
 *     - Explore person 4: quiet[4] = 6, answer[4] = 4
 *     - Explore person 5: quiet[5] = 1, answer[5] = 5
 *     - Explore person 6: quiet[6] = 7, answer[6] = 6
 *     - Min of {4, 6, 1, 7} = 1 (person 5), answer[3] = 5
 *   - Min of {5, 5} = 5, answer[1] = 5
 * - Min of {5, 3} = 3 (person 1), answer[0] = 1
 * 
 * Time: O(n + e) where n = people, e = richer relationships
 * Space: O(n) for memoization and recursion stack
 */
public class LoudAndRich {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;

        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] num : richer) {
            int source = num[1];
            int dest = num[0];
            adj.get(source).add(dest);
        }

        for (int i = 0; i < n; i++) {
            if (answer[i] == -1) {
                dfs(adj, answer, quiet, i);
            }
        }
        return answer;
    }

    public int dfs(List<List<Integer>> adj, int[] answer, int[] quiet, int node) {
        if (answer[node] != -1) {
            return answer[node];
        }
        int min = node;
        for (int child : adj.get(node)) {
            int cur = dfs(adj, answer, quiet, child);
            if (quiet[cur] < quiet[min]) {
                min = cur;
            }
        }
        answer[node] = min;
        return min;
    }

    public static void main(String[] args) {
        int[][] richer = { { 1, 0 }, { 2, 1 }, { 3, 1 }, { 3, 7 }, { 4, 3 }, { 5, 3 }, { 6, 3 } };
        int[] quiet = { 3, 2, 5, 4, 6, 1, 7, 0 };

        System.out.println(new LoudAndRich().loudAndRich(richer, quiet));
    }
}
