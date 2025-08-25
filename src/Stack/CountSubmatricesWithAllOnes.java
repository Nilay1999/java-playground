package Stack;

import java.util.Arrays;

public class CountSubmatricesWithAllOnes {
    public static int countForOneRow(int[] row) {
        int count = 0;
        int subArray = 0;

        for (int i : row) {
            if (i == 1) {
                count++;
            } else {
                count = 0;
            }
            subArray += count;
        }

        return subArray;
    }

    public static int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int ans = 0;
        for (int startRow = 0; startRow < n; startRow++) {
            int[] arr = new int[m];
            Arrays.fill(arr, 1);
            for (int endRow = startRow; endRow < m; endRow++) {
                for (int col = 0; col < m; col++) {
                    arr[col] = arr[col] & mat[endRow][col];
                }
                ans += countForOneRow(arr);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(numSubmat(mat));
    }
}
