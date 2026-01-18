package Array;

public class RottingOranges {
    /**
     * Rotting Oranges Algorithm (Multi-source BFS):
     * 1. Find all initially rotten oranges and add to queue
     * 2. Use BFS to spread rot level by level (each minute)
     * 3. Track time and count fresh oranges that get rotten
     * 4. Return time if all fresh oranges rot, else -1
     * 
     * Grid values: 0=empty, 1=fresh orange, 2=rotten orange
     * 
     * Time: O(m*n), Space: O(m*n) for queue
     * Note: Implementation appears incomplete
     */

    private static class Pair {
        int row;
        int col;
        int tm;

        Pair(int row, int col, int tm) {
            this.row = row;
            this.col = col;
            this.tm = tm;
        }
    }

    public static void main(String[] args) {
        int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        new RottingOranges().orangesRotting(grid);
    }

    public void orangesRotting(int[][] grid) {

    }
}
