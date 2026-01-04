package Array;

import java.util.Arrays;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] ans = new int[m * n];
        int i = 0, j = 0;
        for (int k = 0; k < ans.length; k++) {
            ans[k] = mat[i][j];

            if ((i + j) % 2 == 0) {
                if (j == m - 1)
                    i++;
                else if (i == 0)
                    j++;
                else {
                    i--;
                    j++;
                }
            } else {
                if (i == n - 1)
                    j++;
                else if (j == 0)
                    i++;
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
