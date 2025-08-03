package DynamicProgramming;

import java.util.Arrays;

public class Triangle {
    // Bottom-up Tabulation (In-place)
    public static int minimumTotal(int[][] triangle) {
        int n = triangle.length;

        // Start from second-last row, move upwards
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                int takeFirst = triangle[i][j] + triangle[i + 1][j];
                int takeSecond = triangle[i][j] + triangle[i + 1][j + 1];
                triangle[i][j] = Math.min(takeFirst, takeSecond);
            }
        }

        return triangle[0][0];
    }

    // Top-down Memoized Recursion
    public static int minimumTotalMemo(int[][] triangle) {
        int n = triangle.length;
        Integer[][] memo = new Integer[n][n];

        return recursion(triangle, 0, 0, memo);
    }

    private static int recursion(int[][] triangle, int idx, int row, Integer[][] memo) {
        int n = triangle.length;

        if (row == n - 1) {
            return triangle[row][idx];
        }

        if (memo[row][idx] != null) {
            return memo[row][idx];
        }

        int down = recursion(triangle, idx, row + 1, memo);
        int diagonal = recursion(triangle, idx + 1, row + 1, memo);

        memo[row][idx] = triangle[row][idx] + Math.min(down, diagonal);
        return memo[row][idx];
    }

    public static void main(String[] args) {
        int[][] triangle = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};

        // Clone triangle if using both methods (since tabulation modifies it)
        int[][] triangleCopy = Arrays.stream(triangle).map(int[]::clone).toArray(int[][]::new);

        System.out.println("Bottom-Up (Tabulation): " + minimumTotal(triangleCopy)); // Output: 11
        System.out.println("Top-Down (Memoization): " + minimumTotalMemo(triangle)); // Output: 11
    }
}

