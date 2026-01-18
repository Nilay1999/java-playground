package DynamicProgramming;

public class LongestIncreasingPathInMatrix {
    /**
     * Longest Increasing Path in Matrix Algorithm (DFS + Memoization):
     * Find the longest strictly increasing path in a 2D matrix.
     * 
     * Strategy:
     * 1. Try starting from each cell using DFS
     * 2. From each cell, explore 4 directions if next cell value > current
     * 3. Use memoization to avoid recomputing paths from same cell
     * 4. memo[i][j] = longest path starting from cell (i,j)
     * 
     * Key insight: Since path must be strictly increasing, no cycles possible
     * → Safe to use memoization without visited array
     * 
     * Time: O(m×n) - each cell computed once, Space: O(m×n) for memoization
     */
    public static void main(String[] args) {
        int[][] matrix = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
        System.out.println(new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] start = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(dfs(matrix, start, i, j), ans);
            }
        }

        return ans;
    }

    public int dfs(int[][] matrix, int[][] start, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j > matrix[0].length) {
            return 0;
        }

        if (start[i][j] != 0) {
            return start[i][j];
        }

        int max = 1;

        // go to top and check top value > current value
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            max = Math.max(max, 1 + dfs(matrix, start, i + 1, j));
        }

        // go to below and check below > current value
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            max = Math.max(max, 1 + dfs(matrix, start, i - 1, j));
        }

        // go to right and check right > current value
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            max = Math.max(max, 1 + dfs(matrix, start, i, j + 1));
        }

        // go to below and check below > current value
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            max = Math.max(max, 1 + dfs(matrix, start, i, j - 1));
        }

        start[i][j] = max;
        return start[i][j];
    }

}
