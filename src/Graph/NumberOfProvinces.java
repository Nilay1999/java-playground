package Graph;

import java.util.HashSet;
import java.util.Set;

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
