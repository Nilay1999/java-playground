package DynamicProgramming;

/**
 * Cherry Pickup Algorithm (3D DP):
 * Two people start from (0,0) and reach (n-1,m-1), collect maximum cherries.
 * Can only move right or down. After pickup, cell becomes 0.
 * 
 * KEY INSIGHT: Instead of two separate trips, simulate both people moving simultaneously.
 * This avoids the complexity of tracking grid state changes.
 * 
 * STATE REPRESENTATION:
 * dp[i1][j1][i2] = max cherries when person1 at (i1,j1) and person2 at (i2,j2)
 * where j2 = i1 + j1 - i2 (both people take same number of steps)
 * 
 * ALGORITHM:
 * 1. Both people start at (0,0) simultaneously
 * 2. At each step, try all 4 combinations of moves:
 *    - Person1: right/down, Person2: right/down
 * 3. Collect cherries from both positions (avoid double counting if same cell)
 * 4. Use memoization to avoid recalculating subproblems
 * 5. Handle obstacles (-1 cells) by returning invalid result
 * 
 * OPTIMIZATION: j2 = i1 + j1 - i2 reduces 4D to 3D DP
 * 
 * Example: [[0,1,-1],[1,0,-1],[1,1,1]]
 * Optimal path: Person1 and Person2 take different routes to collect all cherries
 * 
 * Time: O(n³), Space: O(n³)
 */
public class CherryPickup {

    public static int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // dp[i1][j1][i2] where j2 = i1 + j1 - i2
        int[][][] dp = new int[n][m][n];

        // Initialize with -1 (unvisited)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        int result = solve(grid, 0, 0, 0, dp);
        return Math.max(0, result);
    }

    private static int solve(int[][] grid, int i1, int j1, int i2, int[][][] dp) {
        int n = grid.length;
        int m = grid[0].length;
        int j2 = i1 + j1 - i2;

        // Boundary checks
        if (i1 >= n || j1 >= m || i2 >= n || j2 >= m || grid[i1][j1] == -1 || grid[i2][j2] == -1) {
            return Integer.MIN_VALUE;
        }

        // Memoization check
        if (dp[i1][j1][i2] != -1) {
            return dp[i1][j1][i2];
        }

        // Base case: both reach destination
        if (i1 == n - 1 && j1 == m - 1) {
            return grid[i1][j1];
        }

        // Calculate cherries at current positions
        int cherries = grid[i1][j1];
        if (i1 != i2 || j1 != j2) { // Different positions
            cherries += grid[i2][j2];
        }

        // Try all possible moves for both persons
        int maxCherries = Integer.MIN_VALUE;

        // Person 1 goes right, Person 2 goes right
        maxCherries = Math.max(maxCherries, solve(grid, i1, j1 + 1, i2, dp));

        // Person 1 goes right, Person 2 goes down
        maxCherries = Math.max(maxCherries, solve(grid, i1, j1 + 1, i2 + 1, dp));

        // Person 1 goes down, Person 2 goes right
        maxCherries = Math.max(maxCherries, solve(grid, i1 + 1, j1, i2, dp));

        // Person 1 goes down, Person 2 goes down
        maxCherries = Math.max(maxCherries, solve(grid, i1 + 1, j1, i2 + 1, dp));

        if (maxCherries != Integer.MIN_VALUE) {
            cherries += maxCherries;
        } else {
            cherries = Integer.MIN_VALUE;
        }

        return dp[i1][j1][i2] = cherries;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        System.out.println(cherryPickup(grid)); // Output: 5
    }
}
