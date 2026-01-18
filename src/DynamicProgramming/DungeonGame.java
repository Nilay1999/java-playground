package DynamicProgramming;

/**
 * Dungeon Game Algorithm (DP - Backward Approach):
 * Find minimum health needed at start to reach end without dying.
 * 
 * PROBLEM: Navigate dungeon from top-left to bottom-right.
 * Each cell has value (positive = heal, negative = damage).
 * Health must stay >= 1 at all times.
 * Find minimum starting health.
 * 
 * KEY INSIGHT: Work backwards from end to start
 * - Forward approach: hard to track minimum health needed
 * - Backward approach: calculate minimum health needed at each cell
 * 
 * DP DEFINITION:
 * dp[i][j] = minimum health needed at cell (i,j) to reach end safely
 * 
 * RECURRENCE:
 * 1. At destination (n-1, m-1):
 *    - If dungeon[i][j] <= 0: need (1 - dungeon[i][j]) health
 *    - If dungeon[i][j] > 0: need 1 health (heal covers it)
 * 
 * 2. At other cells:
 *    - Calculate minimum health needed for next cells (down, right)
 *    - min_next = min(dp[i+1][j], dp[i][j+1])
 *    - Health needed = min_next - dungeon[i][j]
 *    - But minimum is always 1 (can't have 0 or negative health)
 * 
 * ALGORITHM:
 * 1. Use memoization with recursion
 * 2. For each cell, calculate minimum health needed
 * 3. Base case: destination cell
 * 4. Recursive case: min of two paths - current damage
 * 5. Ensure result is at least 1
 * 
 * Example: dungeon=[[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * 
 * Working backwards:
 * (2,2): need 1 health (can survive -5 with 6 health)
 * (2,1): need 1 health (30 heals, so 1 is enough)
 * (2,0): need 1 health (10 heals, so 1 is enough)
 * (1,2): need 5 health (1 heals, need 4 more for next)
 * (1,1): need 11 health (-10 damage, need 11 to have 1 left)
 * (1,0): need 7 health (-5 damage, need 7 to have 2 left)
 * (0,2): need 1 health (3 heals, so 1 is enough)
 * (0,1): need 4 health (-3 damage, need 4 to have 1 left)
 * (0,0): need 7 health (-2 damage, need 7 to have 5 left)
 * 
 * Time: O(m×n), Space: O(m×n)
 */
public class DungeonGame {
    public static final int max = Integer.MAX_VALUE - 5;

    public static int recursion(int[][] dungeon, int[][] dp, int i, int j, int n, int m) {

        if (i >= n || j >= m) {
            return max;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (i == n - 1 && j == m - 1) {
            if (dungeon[i][j] <= 0) {
                return (-1 * dungeon[i][j]) + 1;
            }
            return 1;
        }

        int down = recursion(dungeon, dp, i + 1, j, n, m);
        int right = recursion(dungeon, dp, i, j + 1, n, m);

        int min = Math.min(down, right) + (-1 * dungeon[i][j]);
        dp[i][j] = min <= 0 ? 1 : min;
        return dp[i][j];

    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        return recursion(dungeon, dp, 0, 0, n, m);
    }

    public static void main(String[] args) {
        int[][] dungeon = { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } };

        System.out.println(calculateMinimumHP(dungeon));
    }
}
