package Graph;

/**
 * Battleships in Board Algorithm (Connected Components with DFS):
 * Count number of battleships on board. Battleships are connected 'X' cells.
 * 
 * PROBLEM INTERPRETATION: Count connected components of 'X' cells.
 * Each battleship is a connected group of 'X' cells (horizontally or vertically).
 * 
 * DFS APPROACH:
 * 1. Iterate through each cell in the board
 * 2. When finding unvisited 'X', start DFS to mark entire battleship
 * 3. DFS explores all connected 'X' cells (4-directional)
 * 4. Increment battleship count for each new connected component
 * 
 * ALGORITHM:
 * 1. For each cell (i,j):
 *    - If board[i][j] == 'X' and not visited: found new battleship
 *    - Run DFS to mark all connected 'X' cells as visited
 *    - Increment total count
 * 2. DFS marks current cell visited and recursively visits neighbors
 * 
 * OPTIMIZATION: Can solve in O(1) space by only counting top-left corners
 * of battleships (cells with no 'X' above or to the left).
 * 
 * Example: [['X','X','.','X'],['.','.','.','X'],['.','.','.','X']]
 * Battleship 1: Connected 'X' at (0,0)-(0,1)
 * Battleship 2: Connected 'X' at (0,3)-(1,3)-(2,3)
 * Total: 2 battleships
 * 
 * Time: O(n*m), Space: O(n*m) for visited array
 */
public class BattleshipsInBoard {
    public int countBattleships(char[][] board) {
        int total = 0;
        int n = board.length;
        int m = board[0].length;

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X' && !visited[i][j]) {
                    dfs(board, visited, i, j);
                    total++;
                }
            }
        }
        return total;
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j) {
        int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        visited[i][j] = true;
        for (int[] dir : direction) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] == '.'
                    || visited[row][col]) {
                continue;
            }
            dfs(board, visited, row, col);
        }
        return;
    }

    public static void main(String[] args) {
        char[][] board = { { 'X', 'X', '.', 'X' },
                { '.', '.', '.', 'X' },
                { '.', '.', '.', 'X' } };
        System.out.println(new BattleshipsInBoard().countBattleships(board));
    }
}