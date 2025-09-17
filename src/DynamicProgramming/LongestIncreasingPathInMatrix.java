package DynamicProgramming;

public class LongestIncreasingPathInMatrix {
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
