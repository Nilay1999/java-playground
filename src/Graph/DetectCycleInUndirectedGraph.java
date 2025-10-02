package Graph;

import java.util.ArrayList;
import java.util.List;

public class DetectCycleInUndirectedGraph {
    public static void main(String[] args) {
        int V = 5;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 }, { 3, 4 } };
        System.out.println(new DetectCycleInUndirectedGraph().detectCycle(edges, V));
    }

    public boolean detectCycle(int[][] edges, int n) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int source = edge[0];
            int dest = edge[1];

            adj.get(source).add(dest);
            adj.get(dest).add(source);
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(adj, visited, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(List<List<Integer>> adj, boolean[] visited, int node, int parent) {
        visited[node] = true;
        for (Integer neig : adj.get(node)) {
            if (!visited[neig]) {
                if (dfs(adj, visited, neig, node)) {
                    return true;
                }
            } else if (parent != neig) {
                return true;
            }
        }
        return false;
    }
}
