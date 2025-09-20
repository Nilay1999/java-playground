package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponents {

    private void dfs(List<List<Integer>> adj, Stack<Integer> stack, boolean[] visited, int node) {
        visited[node] = true;

        for (int n : adj.get(node)) {
            if (!visited[n]) {
                dfs(adj, stack, visited, n);
            }
        }
        stack.push(node);
    }

    private void dfs3(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int n : adj.get(node)) {
            if (!visited[n]) {
                dfs3(adj, visited, n);
            }
        }
    }

    public int kosaraju(int V, int[][] edges) {
        boolean[] visited = new boolean[V];

        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> revAdj = new ArrayList<>();

        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            revAdj.get(edges[i][1]).add(edges[i][0]);
        }


        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adj, stack, visited, i);
            }
        }

        int scc = 0;
        visited = new boolean[V];
        while (!stack.isEmpty()) {
            int node = stack.peek();
            stack.pop();
            if (!visited[node]) {
                scc++;
                dfs3(revAdj, visited, node);
            }
        }
        return scc;

    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 4}, {4, 7}, {5, 7}};
        int v = 8;
        System.out.println(new StronglyConnectedComponents().kosaraju(v, edges));
    }
}
