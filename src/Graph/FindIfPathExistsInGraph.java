package Graph;

import java.util.ArrayList;
import java.util.List;

public class FindIfPathExistsInGraph {
    public boolean dfs(List<List<Integer>> adj, boolean[] visited, int v, int destination) {
        if (v == destination) {
            return true;
        }
        visited[v] = true;
        for (int neigbour : adj.get(v)) {
            if (!visited[neigbour]) {
                if (dfs(adj, visited, neigbour, destination)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return dfs(adj, visited, source, destination);
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] edges = {{4, 3}, {1, 4}, {4, 8}, {1, 7}, {6, 4}, {4, 2}, {7, 4}, {4, 0}, {0, 9}, {5, 4}};
        int source = 5;
        int destination = 9;
        System.out.println(new FindIfPathExistsInGraph().validPath(n, edges, source, destination));
    }
}
