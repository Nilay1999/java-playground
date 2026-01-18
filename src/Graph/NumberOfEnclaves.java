package Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of Enclaves Algorithm (BFS - Island Detection):
 * Count number of enclaves (isolated islands of 1s not touching boundary).
 * 
 * PROBLEM DESCRIPTION:
 * - 2D grid with 0s (water) and 1s (land)
 * - Enclave = connected region of 1s completely surrounded by 0s
 * - 1s touching grid boundary are NOT enclaves
 * - Count total number of enclaves
 * 
 * ALGORITHM APPROACH:
 * 1. First pass: mark all 1s connected to boundary using BFS
 * 2. Start BFS from all boundary 1s
 * 3. Mark visited cells to avoid revisiting
 * 4. Second pass: count remaining unvisited 1s (these are enclaves)
 * 5. Each unvisited 1 is part of an enclave
 * 
 * KEY INSIGHTS:
 * - Boundary-connected 1s are NOT enclaves (they can reach outside)
 * - Only interior 1s that don't connect to boundary are enclaves
 * - BFS efficiently explores all connected cells
 * - Two-pass approach: eliminate boundary-connected, then count remaining
 * - Can use DFS as alternative to BFS
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: grid = [[0,0,0,0], [1,0,1,0], [0,1,1,0], [0,0,0,0]]
 * 
 * Grid visualization:
 * 0 0 0 0
 * 1 0 1 0
 * 0 1 1 0
 * 0 0 0 0
 * 
 * Step 1: Find boundary 1s
 * - Row 0: no 1s
 * - Row 3: no 1s
 * - Col 0: (1,0) has 1
 * - Col 3: no 1s
 * 
 * Step 2: BFS from boundary 1s
 * - Start from (1,0), mark visited[1][0] = true
 * - Explore neighbors: (0,0), (2,0), (1,1) - all 0s or out of bounds
 * - BFS complete
 * 
 * Step 3: Count unvisited 1s
 * - (1,2): not visited, count = 1
 * - (2,1): not visited, count = 2
 * - (2,2): not visited, count = 3
 * 
 * Result: 3 enclaves (the connected region at (1,2), (2,1), (2,2))
 * 
 * Another example: grid = [[0,0,0,0], [1,1,1,0], [0,1,1,0], [0,0,0,0]]
 * 
 * Boundary 1s: (1,0), (1,1), (1,2)
 * BFS marks all connected 1s as visited
 * Remaining unvisited 1s: (2,1), (2,2)
 * Result: 1 enclave (the connected region at (2,1), (2,2))
 * 
 * Time: O(m * n) where m = rows, n = columns
 * Space: O(m * n) for visited array and queue
 */
public class NumberOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        int[][] direction = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        while (!queue.isEmpty()) {
            int[] box = queue.poll();
            int x = box[0];
            int y = box[1];

            for (int[] dir : direction) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[] { nx, ny });
                    visited[nx][ny] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        System.out.println(new NumberOfEnclaves().numEnclaves(grid));
    }
}
