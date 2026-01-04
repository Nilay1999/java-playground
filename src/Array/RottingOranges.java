package Array;

public class RottingOranges {

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
