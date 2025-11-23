package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
