package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Detect Cycle in Undirected Graph Algorithm (DFS):
 * Determine if undirected graph contains a cycle.
 * 
 * CYCLE DEFINITION:
 * - In undirected graph, cycle exists if we can reach a visited node
 *   through a path other than the edge we came from
 * 
 * DFS APPROACH:
 * 1. Build adjacency list from edges
 * 2. For each unvisited node: start DFS
 * 3. During DFS:
 *    - Mark current node as visited
 *    - For each neighbor:
 *      - If not visited: recursively explore
 *      - If visited AND not parent: cycle found
 * 4. Return true if cycle detected
 * 
 * KEY INSIGHT: Parent tracking prevents false cycle detection
 * In undirected graph, edge (u,v) appears as u→v and v→u
 * We must ignore the edge we came from (parent)
 * 
 * ALGORITHM:
 * 1. Build adjacency list (undirected: add both directions)
 * 2. Maintain visited array
 * 3. For each node, if not visited: call dfs(node, -1)
 * 4. In dfs(node, parent):
 *    - Mark node as visited
 *    - For each neighbor:
 *      - If not visited: if dfs(neighbor, node) returns true → cycle
 *      - If visited and not parent: cycle found
 * 
 * Example: edges=[[0,1],[0,2],[0,3],[1,2],[3,4]], n=5
 * 
 * Graph:
 *     0
 *    /|\
 *   1-2 3
 *       |
 *       4
 * 
 * DFS from 0:
 * - Visit 0, explore neighbors [1,2,3]
 * - Visit 1, explore neighbors [0,2]
 *   - 0 is parent, skip
 *   - Visit 2, explore neighbors [0,1]
 *     - 0 is parent, skip
 *     - 1 is visited and not parent → CYCLE FOUND
 * 
 * Time: O(V + E), Space: O(V + E)
 */
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
