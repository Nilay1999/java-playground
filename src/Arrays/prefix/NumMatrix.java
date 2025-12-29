package Arrays.prefix;

public class NumMatrix {
    private int[][] prefix;

    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        this.prefix = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                this.prefix[i][j] = matrix[i - 1][j - 1] + this.prefix[i][j - 1] + this.prefix[i - 1][j]
                        - this.prefix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1]
                - prefix[row1][col2 + 1] + prefix[row1][col1];
    }

    public static void main(String[] args) {
        int[][] mat = {
                { 3, 0, 1, 4, 2 },
                { 5, 6, 3, 2, 1 },
                { 1, 2, 0, 1, 5 },
                { 4, 1, 0, 1, 7 },
                { 1, 0, 3, 0, 5 }
        };
        int[][] points = { { 2, 1, 4, 3 }, { 1, 1, 2, 2 }, { 1, 2, 2, 4 } };
        NumMatrix matrix = new NumMatrix(mat);
        System.out.println(matrix.sumRegion(points[0][1], points[0][1], points[0][2], points[0][3]));
    }
}
