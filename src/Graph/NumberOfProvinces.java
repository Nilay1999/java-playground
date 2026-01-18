package Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Number of Provinces Algorithm (Union-Find / DFS - Graph Connectivity):
 * Count number of provinces (connected components) in a graph.
 * 
 * PROBLEM DESCRIPTION:
 * - n cities, some directly connected
 * - isConnected[i][j] = 1 if city i and j directly connected
 * - Province = group of cities all connected (directly or indirectly)
 * - Count total number of provinces
 * 
 * ALGORITHM APPROACH (DFS Method):
 * 1. Build adjacency matrix from isConnected
 * 2. Maintain visited array to track explored cities
 * 3. For each unvisited city, start DFS and increment province count
 * 4. DFS marks all cities in current province as visited
 * 5. Return total province count
 * 
 * ALGORITHM APPROACH (Union-Find Method):
 * 1. Initialize parent array where parent[i] = i
 * 2. For each connected pair (i,j), union them
 * 3. Count unique root parents (each root = one province)
 * 
 * KEY INSIGHTS:
 * - Province = connected component in undirected graph
 * - DFS approach: each DFS call explores one province
 * - Union-Find approach: each root parent represents one province
 * - isConnected is symmetric: if i-j connected, then j-i connected
 * - Can use either DFS or Union-Find; Union-Find more efficient for this
 * 
 * STEP-BY-STEP WALKTHROUGH (DFS):
 * Example: isConnected = [[1,1,0], [1,1,0], [0,0,1]]
 * 
 * Adjacency representation:
 * 0 ↔ 1
 * 1 ↔ 0
 * 2 (isolated)
 * 
 * Traversal:
 * i=0: not visited, start DFS
 *   - Visit 0, explore neighbors: 1
 *   - Visit 1, explore neighbors: 0 (visited)
 *   - Province 1 complete, count = 1
 * 
 * i=1: visited, skip
 * 
 * i=2: not visited, start DFS
 *   - Visit 2, explore neighbors: none
 *   - Province 2 complete, count = 2
 * 
 * Result: 2 provinces
 * 
 * STEP-BY-STEP WALKTHROUGH (Union-Find):
 * Example: isConnected = [[1,1,0], [1,1,0], [0,0,1]]
 * 
 * Initialize: parent = [0, 1, 2]
 * 
 * Process connections:
 * (0,1): union(0,1) → parent = [1, 1, 2]
 * (1,0): already connected
 * (2,2): already same
 * 
 * Find roots:
 * find(0) = 1
 * find(1) = 1
 * find(2) = 2
 * 
 * Unique roots: {1, 2} → 2 provinces
 * 
 * Time: O(n²) for both approaches
 * Space: O(n) for visited/parent array
 */
public class NumberOfProvinces {
    private int[] parent;
    private int[] rank;

    public static void main(String[] args) {
        int[][] nodes = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        System.out.println(new NumberOfProvinces().findCircleNum(nodes));
    }

    public int unionFind(int[][] isConnected) {
        int n = isConnected.length;
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            hashSet.add(find(i));
        }

        return hashSet.size();
    }

    private void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if (rank[n1] > rank[n2]) {
            parent[n2] = n1;
        } else if (rank[n2] > rank[n1]) {
            parent[n1] = n2;
        } else {
            parent[n1] = n2;
            rank[n2] += 1;
        }
    }

    private int find(int node) {
        if (node == parent[node]) {
            return node;
        }
        parent[node] = find(parent[node]);
        return parent[node];
    }

    private int findCircleNum(int[][] isConnected) {
        int provinces = 0;
        int n = isConnected.length;

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[city][i] == 1 && !visited[i]) {
                visited[city] = true;
                dfs(isConnected, visited, isConnected[city][i]);
            }
        }
    }
}
