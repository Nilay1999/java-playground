package Backtracking;

/**
 * Unique Paths III Algorithm (Backtracking with Grid Traversal):
 * Count paths from start to end that visit every empty cell exactly once.
 * 
 * PROBLEM DESCRIPTION:
 * - Grid values: 1=start, 2=end, 0=empty, -1=obstacle
 * - Find paths that visit all empty cells exactly once
 * - Can move in 4 directions: up, down, left, right
 * 
 * BACKTRACKING STRATEGY:
 * 1. Count total empty cells (including start)
 * 2. Start DFS from starting position
 * 3. Mark visited cells as obstacles (-1) temporarily
 * 4. Try all 4 directions
 * 5. If reach end AND visited all empty cells → valid path
 * 6. Backtrack: restore cell to empty (0)
 * 
 * KEY INSIGHT:
 * - Track visited count to ensure all empty cells are covered
 * - Temporarily mark cells as -1 to avoid revisiting
 * - Restore cells when backtracking
 * 
 * Example: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Must visit all 11 empty cells (including start) to reach end
 * 
 * Time: O(4^(m*n)) worst case, Space: O(m*n) for recursion
 */
public class UniquePathsIII {
    public static int totalPaths;

    public static void backtrack(int[][] grid, int x, int y, int visited, int totalEmptyCell) {
        // Boundary check and obstacle check
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1) {
            return;
        }
        
        // 4 directions: right, down, left, up
        int[][] direction = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        
        // Reached end cell
        if (grid[x][y] == 2) {
            // Check if visited all empty cells
            if (visited == totalEmptyCell)
                totalPaths++; // found valid path
            return;
        }

        // Mark current cell as visited (temporarily make it obstacle)
        grid[x][y] = -1;
        
        // Try all 4 directions
        for (int[] dir : direction) {
            int i = dir[0];
            int j = dir[1];
            // Recurse to next cell, increment visited count
            backtrack(grid, x + i, y + j, visited + 1, totalEmptyCell);
        }
        
        // Backtrack: restore cell to empty
        grid[x][y] = 0;
    }

    public static int uniquePaths(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        totalPaths = 0;
        int totalEmptyCell = 1; // count start cell
        int startX = 0;
        int startY = 0;
        
        // Find start position and count empty cells
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    // Found start position
                    startX = i;
                    startY = j;
                    continue;
                }
                if (grid[i][j] == 0) {
                    // Count empty cells
                    totalEmptyCell++;
                }
            }
        }
        
        // Start backtracking from start position
        backtrack(grid, startX, startY, 0, totalEmptyCell);
        return totalPaths;
    }

    public static void main(String[] args) {
        int[][] grid = { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 2 } };
        int[][] grid2 = { { 0, 1 }, { 2, 0 } };
        int[][] grid3 = { { 1, 0, 0, -1 }, { 0, 0, 0, -1 }, { 0, 0, 0, 2 } };

        System.out.printf("Answer: %d", uniquePaths(grid));
        System.out.printf("Answer: %d", uniquePaths(grid2));
        System.out.printf("Answer: %d", uniquePaths(grid3));
    }
}
