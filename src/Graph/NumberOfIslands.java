package Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of Islands Algorithm (Connected Components):
 * Count number of connected components of '1's in 2D grid.
 * Each connected component is one island.
 * 
 * PROBLEM: Given 2D grid with '1' (land) and '0' (water),
 * count number of islands (connected '1's).
 * 
 * DFS APPROACH:
 * 1. Iterate through each cell in grid
 * 2. If cell is '1' and unvisited: found new island
 * 3. Use DFS to mark all connected '1's as visited
 * 4. Increment island count
 * 5. Continue until all cells processed
 * 
 * DFS ALGORITHM:
 * - Mark current cell as visited
 * - Explore 4 neighbors (up, down, left, right)
 * - If neighbor is '1' and unvisited: recurse
 * 
 * BFS ALTERNATIVE:
 * - Use queue instead of recursion
 * - Mark as visited when adding to queue
 * - Process level by level
 * 
 * CONNECTIVITY: 4-directional (not diagonal)
 * 
 * Example: grid=[['1','1','0','0','0'],
 *                ['1','1','0','0','0'],
 *                ['0','0','1','0','0'],
 *                ['0','0','0','1','1']]
 * 
 * Island 1: (0,0)-(0,1)-(1,0)-(1,1)
 * Island 2: (2,2)
 * Island 3: (3,3)-(3,4)
 * Total: 3 islands
 * 
 * OPTIMIZATION: Can modify grid in-place instead of visited array
 * Change '1' to '0' as we visit (saves space)
 * 
 * Time: O(m×n), Space: O(m×n) for visited array + O(m×n) recursion stack
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        int island = 0; // count of islands

        // Iterate through each cell in grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // If cell is land ('1') and not visited, found new island
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, visited, i, j); // mark all connected land
                    island++; // increment island count
                }
            }
        }
        return island;
    }

    // DFS to mark all connected land cells as visited
    private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        // 4 directions: down, up, right, left
        int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        
        // Mark current cell as visited
        visited[i][j] = true;
        
        // Explore all 4 neighbors
        for (int[] dir : direction) {
            int row = i + dir[0];
            int col = j + dir[1];
            
            // Check if neighbor is within bounds
            if (row < grid.length && row >= 0 && col >= 0 && col < grid[0].length) {
                // If neighbor is land and not visited, recurse
                if (!visited[row][col] && grid[row][col] == '1') {
                    dfs(grid, visited, row, col);
                }
            }
        }
        return;
    }

    // BFS alternative approach
    private void bfs(char[][] grid, boolean[][] visited, int row, int col, int[][] directions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { row, col });
        visited[row][col] = true; // mark starting cell as visited

        while (!queue.isEmpty()) {
            int[] point = queue.poll(); // dequeue current cell

            // Explore all 4 neighbors
            for (int[] dir : directions) {
                int newX = point[0] + dir[0];
                int newY = point[1] + dir[1];

                // Check bounds, visited status, and if it's land
                if (newX >= 0 && newX < grid.length &&
                        newY >= 0 && newY < grid[0].length &&
                        !visited[newX][newY] &&
                        grid[newX][newY] == '1') {

                    visited[newX][newY] = true; // mark as visited
                    queue.offer(new int[] { newX, newY }); // enqueue neighbor
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' }
        };

        System.out.println(new NumberOfIslands().numIslands(grid));
    }
}
