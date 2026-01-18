package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Number of Connected Components Algorithm (DFS - Graph Connectivity):
 * Count the number of connected components in an undirected graph.
 * 
 * PROBLEM DESCRIPTION:
 * - Given n nodes (0 to n-1) and edges connecting them
 * - Find number of connected components (isolated subgraphs)
 * - Two nodes are in same component if connected by path
 * 
 * ALGORITHM APPROACH:
 * 1. Build adjacency list from edges (undirected graph)
 * 2. Maintain visited array to track explored nodes
 * 3. For each unvisited node, start DFS and increment component count
 * 4. DFS marks all nodes in current component as visited
 * 5. Return total component count
 * 
 * KEY INSIGHTS:
 * - Each DFS call explores exactly one connected component
 * - Number of DFS calls = number of connected components
 * - Undirected graph: add edges in both directions
 * - Visited array prevents revisiting nodes
 * - Can use BFS as alternative to DFS
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: n = 6, edges = [[0,1], [1,2], [2,3], [4,5]]
 * 
 * Build adjacency list:
 * 0 → [1]
 * 1 → [0, 2]
 * 2 → [1, 3]
 * 3 → [2]
 * 4 → [5]
 * 5 → [4]
 * 
 * Traversal:
 * i=0: not visited, start DFS
 *   - Visit 0, explore neighbors: 1
 *   - Visit 1, explore neighbors: 0 (visited), 2
 *   - Visit 2, explore neighbors: 1 (visited), 3
 *   - Visit 3, explore neighbors: 2 (visited)
 *   - Component 1 complete, count = 1
 * 
 * i=1: visited, skip
 * i=2: visited, skip
 * i=3: visited, skip
 * 
 * i=4: not visited, start DFS
 *   - Visit 4, explore neighbors: 5
 *   - Visit 5, explore neighbors: 4 (visited)
 *   - Component 2 complete, count = 2
 * 
 * i=5: visited, skip
 * 
 * Result: 2 connected components
 * 
 * Time: O(n + e) where n = nodes, e = edges
 * Space: O(n + e) for adjacency list and visited array
 */
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
