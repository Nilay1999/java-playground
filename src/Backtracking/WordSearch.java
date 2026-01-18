package Backtracking;

/**
 * Word Search Algorithm (Backtracking with Grid Traversal):
 * Find if a word exists in a 2D board by connecting adjacent cells.
 * 
 * PROBLEM CONSTRAINTS:
 * - Word must be constructed from adjacent cells (horizontal or vertical)
 * - Same cell cannot be used more than once in a single word
 * - Can move in 4 directions: up, down, left, right
 * 
 * BACKTRACKING STRATEGY:
 * 1. Find all cells matching first character of word
 * 2. Start DFS from each matching cell
 * 3. Mark cell as visited to avoid reuse
 * 4. Try all 4 directions for next character
 * 5. If all characters matched → word found
 * 6. Backtrack: unmark cell as visited
 * 
 * PRUNING:
 * - Stop if out of bounds
 * - Stop if cell already visited
 * - Stop if character doesn't match
 * 
 * Example: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']]
 * Word "ABCCED" exists: A→B→C→C→E→D
 * 
 * Time: O(m*n * 4^L) where L=word length, Space: O(m*n) for visited array
 */
public class WordSearch {
    // 4 directions: down, right, up, left
    private int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        // Try starting from each cell
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // If first character matches, start DFS
                if (board[i][j] == word.charAt(0) && find(i, j, 0, word, board, visited)) {
                    return true; // word found
                }
            }
        }
        return false; // word not found
    }

    public boolean find(int i, int j, int idx, String word, char[][] board, boolean[][] visited) {
        // Base case: matched all characters
        if (idx == word.length()) {
            return true;
        }

        // Boundary checks and validation
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length 
            || visited[i][j] // already visited
            || board[i][j] != word.charAt(idx)) { // character doesn't match
            return false;
        }

        // Mark cell as visited
        visited[i][j] = true;
        
        // Try all 4 directions
        for (int d = 0; d < dir.length; d++) {
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            // Recursively search for next character
            if (find(x, y, idx + 1, word, board, visited)) {
                return true; // word found
            }
        }
        
        // Backtrack: unmark cell as visited
        visited[i][j] = false;
        return false; // word not found from this path
    }

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        System.out.println(new WordSearch().exist(board, "ABCCED"));
    }
}
