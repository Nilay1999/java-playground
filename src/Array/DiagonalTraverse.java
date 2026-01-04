package Array;

import java.util.Arrays;

public class DiagonalTraverse {
    /**
     * Matrix Diagonal Traversal Algorithm:
     * Traverse matrix diagonally, alternating direction on each diagonal.
     * 
     * Pattern:
     * - Even diagonals (i+j even): go up-right ↗
     * - Odd diagonals (i+j odd): go down-left ↙
     * 
     * Boundary handling:
     * - Up-right: hit top edge → move right, hit right edge → move down
     * - Down-left: hit bottom edge → move right, hit left edge → move down
     * 
     * Visual for 3x3:
     * 1 → 2 4
     * ↓ ↗ ↙ ↑
     * 3 5 7
     * ↓ ↙ ↗ ↑
     * 6 ← 8 9
     * 
     * Time: O(m*n), Space: O(1) excluding output
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length; // number of rows
        int m = mat[0].length; // number of columns

        int[] ans = new int[m * n];
        int i = 0, j = 0; // current position in matrix

        for (int k = 0; k < ans.length; k++) {
            // Add current element to result
            ans[k] = mat[i][j];

            // Even diagonal (i+j is even): move up-right ↗
            if ((i + j) % 2 == 0) {
                // Hit right edge: move down
                if (j == m - 1)
                    i++;
                // Hit top edge: move right
                else if (i == 0)
                    j++;
                // Normal case: move up-right
                else {
                    i--;
                    j++;
                }
            }
            // Odd diagonal (i+j is odd): move down-left ↙
            else {
                // Hit bottom edge: move right
                if (i == n - 1)
                    j++;
                // Hit left edge: move down
                else if (j == 0)
                    i++;
                // Normal case: move down-left
                else {
                    i++;
                    j--;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        System.out.println(Arrays.toString(new DiagonalTraverse().findDiagonalOrder(matrix)));
    }
}
