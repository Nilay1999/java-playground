package Graph;

import java.util.ArrayList;
import java.util.List;

public class DFSInGraph {

    public void addEdge(List<List<Integer>> adj, int source, int dest) {
        adj.get(source).add(dest);
        adj.get(dest).add(source);
    }

    public void dfs(List<Integer> result, List<List<Integer>> adj, boolean[] visited, int v) {
        visited[v] = true;
        result.add(v);

        for (int i : adj.get(v)) {
            if (!visited[i]) {
                dfs(result, adj, visited, i);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 0}, {2, 0}, {2, 3}, {2, 4}};
        int V = 5;
        boolean[] visited = new boolean[5];

        List<List<Integer>> adj = new ArrayList<>();
        DFSInGraph graph = new DFSInGraph();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            graph.addEdge(adj, e[0], e[1]);
        }

        List<Integer> result = new ArrayList<>();

        graph.dfs(result, adj, visited, 0);
        System.out.println(result);
    }
}
