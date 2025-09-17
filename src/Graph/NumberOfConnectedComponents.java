package Graph;

import java.util.ArrayList;
import java.util.List;

public class NumberOfConnectedComponents {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {4, 5}};
        int n = 6;
        System.out.println(new NumberOfConnectedComponents().countComponents(edges, n));
    }

    public int countComponents(int[][] edges, int n) {
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int dest = edges[i][1];

            adj.get(source).add(dest);
            adj.get(dest).add(source);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, visited, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (Integer nei : adj.get(node)) {
            if (!visited[nei]) {
                dfs(adj, visited, nei);
            }
        }
    }
}
