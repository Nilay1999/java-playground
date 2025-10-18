package Graph;

public class DetectCyclesIn2DGrid {
    class DSU {
        int[] parent;
        int[] rank;

        public DSU(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        DSU dsu = new DSU(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[][] dirs = { { 1, 0 }, { 0, 1 } };
                int idx1 = i * n + j;
                for (int[] dir : dirs) {
                    int row = i + dir[0];
                    int col = j + dir[1];
                    int idx2 = row * n + col;
                    if (row < m && col < n && grid[i][j] == grid[row][col]) {
                        if (!dsu.union(idx1, idx2)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean containsCycleDFS(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited, int i, int j, int pi, int pj, char color) {
        visited[i][j] = true;

        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int[] dir : directions) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni < 0 || nj < 0 || ni >= grid.length || nj >= grid[0].length)
                continue;
            if (grid[ni][nj] != color)
                continue;

            // Skip the cell we came from
            if (ni == pi && nj == pj)
                continue;

            if (visited[ni][nj]) {
                // Found a visited cell (not parent) → cycle
                return true;
            }

            if (dfs(grid, visited, ni, nj, i, j, color))
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] grid = {
                { 'a', 'c', 'a', 'a' },
                { 'a', 'b', 'b', 'a' },
                { 'a', 'b', 'c', 'a' },
                { 'a', 'a', 'a', 'a' }
        };

        System.out.println(new DetectCyclesIn2DGrid().containsCycle(grid));
        System.out.println(new DetectCyclesIn2DGrid().containsCycleDFS(grid));
    }
}
