package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pacific Atlantic Water Flow Algorithm (DFS - Reverse Thinking):
 * Find all cells where water can flow to both Pacific and Atlantic oceans.
 * 
 * PROBLEM DESCRIPTION:
 * - 2D grid with heights of land
 * - Pacific ocean touches top and left edges
 * - Atlantic ocean touches bottom and right edges
 * - Water flows from higher to lower elevation
 * - Find all cells where water can reach both oceans
 * 
 * ALGORITHM APPROACH:
 * 1. Use reverse thinking: instead of checking if water flows OUT,
 *    check if water can flow IN from ocean boundaries
 * 2. Start DFS from all Pacific boundary cells (top and left edges)
 * 3. Mark all cells reachable from Pacific (water can flow to Pacific)
 * 4. Start DFS from all Atlantic boundary cells (bottom and right edges)
 * 5. Mark all cells reachable from Atlantic (water can flow to Atlantic)
 * 6. Cells marked by both DFS calls can reach both oceans
 * 
 * KEY INSIGHTS:
 * - Reverse thinking: flow FROM ocean INTO land (easier to compute)
 * - Water flows from higher to lower, so reverse: can reach ocean if >= neighbor
 * - Two separate DFS passes: one for each ocean
 * - Intersection of both reachable sets = answer
 * - Boundary cells are starting points (they touch oceans)
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: heights = [[1,2,2,3,5], [3,2,3,4,4], [2,4,5,3,1], [6,7,1,4,5], [5,1,1,2,4]]
 * 
 * Pacific boundary: top row and left column
 * Atlantic boundary: bottom row and right column
 * 
 * DFS from Pacific (top-left):
 * Start from (0,0)=1, (0,1)=2, (0,2)=2, (0,3)=3, (0,4)=5
 * Start from (1,0)=3, (2,0)=2, (3,0)=6, (4,0)=5
 * 
 * From (0,4)=5: can reach (1,4)=4, (2,4)=1, (3,4)=5, (4,4)=4
 * From (4,0)=5: can reach (4,1)=1, (4,2)=1, (4,3)=2, (4,4)=4
 * ... (continue DFS marking all reachable cells)
 * 
 * DFS from Atlantic (bottom-right):
 * Start from (4,0)=5, (4,1)=1, (4,2)=1, (4,3)=2, (4,4)=4
 * Start from (0,4)=5, (1,4)=4, (2,4)=1, (3,4)=5
 * 
 * From (0,4)=5: can reach (0,3)=3, (0,2)=2, (0,1)=2, (0,0)=1
 * From (4,0)=5: can reach (3,0)=6, (2,0)=2, (1,0)=3, (0,0)=1
 * ... (continue DFS marking all reachable cells)
 * 
 * Intersection: cells marked by both DFS calls
 * Result: [[0,4], [1,3], [1,4], [2,2], [3,0], [3,1], [4,0]]
 * 
 * Time: O(m * n) where m = rows, n = columns (each cell visited twice)
 * Space: O(m * n) for visited arrays and recursion stack
 */
public class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] pac = new boolean[n][m];
        boolean[][] alt = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            dfs(pac, heights, i, 0, heights[i][0]);
            dfs(alt, heights, i, m - 1, heights[i][m - 1]);
        }
        for (int j = 0; j < m; j++) {
            dfs(pac, heights, 0, j, heights[0][j]);
            dfs(alt, heights, n - 1, j, heights[n - 1][j]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pac[i][j] && alt[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void dfs(boolean[][] visited, int[][] heights, int i, int j, int prev) {
        int n = heights.length;
        int m = heights[0].length;

        if (i >= n || i < 0 || j >= m || j < 0 || visited[i][j] || prev > heights[i][j]) {
            return;
        }

        int[][] direction = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        visited[i][j] = true;
        for (int[] dir : direction) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            dfs(visited, heights, row, col, heights[i][j]);
        }
        return;
    }

    public static void main(String[] args) {
        int[][] water = {
                { 1, 2, 2, 3, 5 },
                { 3, 2, 3, 4, 4 },
                { 2, 4, 5, 3, 1 },
                { 6, 7, 1, 4, 5 },
                { 5, 1, 1, 2, 4 }
        };
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(water));
    }
}
