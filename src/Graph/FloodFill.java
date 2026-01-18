package Graph;

import java.util.Arrays;

/**
 * Flood Fill Algorithm (DFS):
 * Fill connected region with new color (like paint bucket tool).
 * 
 * PROBLEM: Given starting cell (sr, sc) and new color, fill all connected cells
 * with same original color with the new color.
 * 
 * DFS APPROACH:
 * 1. Store original color at starting position
 * 2. Use DFS to explore all 4-directional neighbors
 * 3. For each cell:
 *    - If it has original color: change to new color
 *    - Recursively explore 4 neighbors
 * 4. Stop when reaching boundary or different color
 * 
 * ALGORITHM:
 * 1. Get original color from starting cell
 * 2. Call dfs(sr, sc, newColor, originalColor)
 * 3. In dfs(i, j, newColor, originalColor):
 *    - If board[i][j] == originalColor: change to newColor
 *    - For each 4-directional neighbor:
 *      - Check bounds and color
 *      - If valid and not already new color: recurse
 * 
 * BOUNDARY CONDITIONS:
 * - Out of bounds: stop
 * - Already new color: stop (avoid infinite loop)
 * - Different from original color: stop
 * 
 * Example: image=[[1,1,1],[1,1,0],[1,0,1]], sr=1, sc=1, color=2
 * 
 * Original:
 * 1 1 1
 * 1 1 0
 * 1 0 1
 * 
 * Start at (1,1) with color 1, fill with 2:
 * - (1,1): 1→2, explore neighbors
 * - (0,1): 1→2, explore neighbors
 * - (0,0): 1→2, explore neighbors
 * - (0,2): 1→2, explore neighbors
 * - (1,0): 1→2, explore neighbors
 * - (2,0): 1→2, explore neighbors
 * 
 * Result:
 * 2 2 2
 * 2 2 0
 * 2 0 1
 * 
 * Time: O(m×n), Space: O(m×n) for recursion stack
 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int start = image[sr][sc];
        dfs(image, sr, sc, color, start);
        return image;
    }

    private void dfs(int[][] board, int i, int j, int color, int start) {
        int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        if (board[i][j] == start)
            board[i][j] = color;
        for (int[] dir : direction) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] == color
                    || board[row][col] != start) {
                continue;
            }
            dfs(board, row, col, color, start);
        }
        return;
    }

    public static void main(String[] args) {
        int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
        int sr = 1;
        int sc = 1;
        int color = 2;

        int[][] ans = new FloodFill().floodFill(image, sr, sc, color);
        System.out.println(Arrays.deepToString(ans));
    }
}
