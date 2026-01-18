package Stack;

/**
 * Maximum Rectangle Algorithm (DP + Histogram):
 * Find largest rectangle containing only 1's in binary matrix.
 * 
 * ALGORITHM OVERVIEW:
 * 1. Treat each row as base of histogram
 * 2. For each row, calculate height of consecutive 1's above
 * 3. Find maximum rectangle in histogram for each row
 * 4. Return overall maximum
 * 
 * HEIGHT CALCULATION:
 * - dp[i][j] = height of consecutive 1's ending at row i, column j
 * - If matrix[i][j] == '1': dp[i][j] = dp[i-1][j] + 1
 * - If matrix[i][j] == '0': dp[i][j] = 0
 * 
 * HISTOGRAM APPROACH:
 * - For each row, heights form a histogram
 * - Find maximum rectangle in histogram using stack
 * - Track maximum across all rows
 * 
 * Example: Matrix
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Heights at each row:
 * Row 0: [1, 0, 1, 0, 0]
 * Row 1: [2, 0, 2, 1, 1]
 * Row 2: [3, 1, 3, 2, 2]
 * Row 3: [4, 0, 0, 3, 0]
 * 
 * Maximum rectangle: 6 (3×2 at row 2, columns 0-2)
 * 
 * Time: O(m*n), Space: O(n)
 */
public class MaximumRectangle {
    public static int maximalRectangle(char[][] matrix) {
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
        char[][] box = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
                { '1', '0',
                        '0', '1', '0' } };
        System.out.println(maximalRectangle(box));
    }
}
