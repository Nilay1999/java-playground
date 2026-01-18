package Graph;

import java.util.ArrayList;
import java.util.List;

public class DFSInGraph {
    /**
     * Depth-First Search (DFS) Algorithm:
     * Explore graph by going as deep as possible before backtracking.
     * 
     * Algorithm:
     * 1. Start from source node, mark as visited and add to result
     * 2. For each unvisited neighbor: recursively call DFS
     * 3. Backtrack when no more unvisited neighbors
     * 
     * Applications: Cycle detection, connected components, topological sort
     * 
     * Time: O(V + E), Space: O(V) for recursion stack and visited array
     */

    // Helper: Add undirected edge between source and destination
    public void addEdge(List<List<Integer>> adj, int source, int dest) {
        adj.get(source).add(dest); // add edge source → dest
        adj.get(dest).add(source); // add edge dest → source (undirected)
    }

    // DFS traversal starting from node v
    public void dfs(List<Integer> result, List<List<Integer>> adj, boolean[] visited, int v) {
        // Mark current node as visited
        visited[v] = true;
        result.add(v); // add to result

        // Recursively visit all unvisited neighbors
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                dfs(result, adj, visited, neighbor);
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
