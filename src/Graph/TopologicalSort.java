package Graph;

import java.util.*;

public class TopologicalSort {

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
