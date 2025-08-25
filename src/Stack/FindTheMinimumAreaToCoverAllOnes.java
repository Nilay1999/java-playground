package Stack;

public class FindTheMinimumAreaToCoverAllOnes {
    public int minimumArea(int[][] grid) {
        int left = 10001;
        int right = 0;
        int top = 10001;
        int bottom = 0;
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }

        return (right - left + 1) * (bottom - top + 1);
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 1, 0}, {1, 0, 1}};
    }
}
