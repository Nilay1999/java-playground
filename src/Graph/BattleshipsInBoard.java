package Graph;

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