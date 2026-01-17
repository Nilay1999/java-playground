package company.Amazon;

public class Minesweeper {
    private static int[][] directions = {
            { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 },
            { 1, 1 }, { -1, 1 }, { -1, -1 }, { -1, 1 }
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        dfs(board, x, y);
        return board;
    }

    public void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'E') {
            return;
        }

        int count = getCount(board, row, col);
        if (count > 0) {
            board[row][col] = (char) ('0' + count);
            return;
        }
        board[row][col] = 'B';
        for (int[] dir : directions) {
            int newX = dir[0] + row;
            int newY = dir[1] + col;
            dfs(board, newX, newY);
        }
    }

    public int getCount(char[][] board, int x, int y) {
        int count = 0;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                if (board[newX][newY] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }

}
