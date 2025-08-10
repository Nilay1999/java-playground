package Stack;


import java.util.Arrays;

public class MaximumRectangle {
    public static int maximalRectangle(char[][] matrix) {
        int max = 0;
        int row = matrix.length, col = matrix[0].length;

        int[][] dp = new int[row][col];

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = matrix[i][j] - '0';
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dp[i - 1][j] == 1) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (dp[i][j - 1] == dp[i][j]) {
                    dp[i][j] += dp[i - 1][j] + 1;
                }
            }
        }
        return 8;
    }

    public static void main(String[] args) {
        char[][] box = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0',
                '0', '1', '0'}};
        System.out.println(maximalRectangle(box));
    }
}
