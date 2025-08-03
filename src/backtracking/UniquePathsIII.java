package backtracking;

public class UniquePathsIII {
    public static int totalPaths;

    public static void backtrack(int[][] grid, int x, int y, int visited, int totalEmptyCell) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1) {
            return;
        }
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if (grid[x][y] == 2) {
            if (visited == totalEmptyCell) totalPaths++;
            return;
        }

        grid[x][y] = -1;
        for (int[] dir : direction) {
            int i = dir[0];
            int j = dir[1];
            backtrack(grid, x + i, y + j, visited + 1, totalEmptyCell);
        }
        grid[x][y] = 0;
    }

    public static int uniquePaths(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        totalPaths = 0;
        int totalEmptyCell = 1;
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                    continue;
                }
                if (grid[i][j] == 0) {
                    totalEmptyCell++;
                }
            }
        }
        backtrack(grid, startX, startY, 0, totalEmptyCell);
        return totalPaths;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        int[][] grid2 = {{0, 1}, {2, 0}};
        int[][] grid3 = {{1, 0, 0, -1}, {0, 0, 0, -1}, {0, 0, 0, 2}};

        System.out.printf("Answer: %d", uniquePaths(grid3));
    }
}
