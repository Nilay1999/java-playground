package Graph;

import java.util.*;

public class TopologicalSort {
    /**
     * Topological Sort Algorithms:
     * Linear ordering of vertices in DAG such that for every edge u→v, u comes before v.
     * 
     * METHOD 1 - KAHN'S ALGORITHM (BFS):
     * 1. Calculate in-degree for all vertices
     * 2. Add vertices with in-degree 0 to queue
     * 3. Process queue: remove vertex, decrease neighbors' in-degree
     * 4. Add vertices with in-degree 0 to queue
     * 
     * METHOD 2 - DFS APPROACH:
     * 1. Perform DFS from each unvisited vertex
     * 2. After processing all neighbors, push vertex to stack
     * 3. Pop stack to get topological order
     * 
     * Applications: Course scheduling, build systems, dependency resolution
     * 
     * Time: O(V + E), Space: O(V)
     */

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = { { 2, 3 }, { 3, 1 }, { 4, 0 }, { 4, 1 }, { 5, 0 }, { 5, 2 } };
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] ints : edges) {
            adj.get(ints[0]).add(ints[1]);
        }
        System.out.println(Arrays.toString(new TopologicalSort().topologicalSortDfs(adj, n)));
        System.out.println(Arrays.toString(new TopologicalSort().topologicalSortBfs(adj, n)));
    }

    // Kahn's Algorithm
    public int[] topologicalSortBfs(List<List<Integer>> adj, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[n];

        for (int u = 0; u < n; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            topo.add(node);
            for (Integer nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    queue.add(nei);
                }
            }
        }

        return topo.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] topologicalSortDfs(List<List<Integer>> adj, int n) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, stack, visited, i);
            }
        }

        int[] result = new int[n];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i] = stack.pop();
            i++;
        }
        return result;
    }

    public void dfs(List<List<Integer>> adj, Stack<Integer> stack, boolean[] visited, int node) {
        visited[node] = true;
        for (Integer nei : adj.get(node)) {
            if (!visited[nei]) {
                dfs(adj, stack, visited, nei);
            }
        }
        stack.push(node);
    }
}
