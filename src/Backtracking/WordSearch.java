package Backtracking;

public class WordSearch {
    private int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0) && find(i, j, 0, word, board, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find(int i, int j, int idx, String word, char[][] board, boolean[][] visited) {
        if (idx == word.length()) {
            return true;
        }

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]
                || board[i][j] != word.charAt(idx)) {
            return false;
        }

        visited[i][j] = true;
        for (int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            if (find(x, y, idx + 1, word, board, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        System.out.println(new WordSearch().exist(board, "ABCCED"));
    }
}
