package Array;

public class DecodeTheSlantedCiphertext {
    /**
     * Slanted Cipher Decoding Algorithm:
     * 1. Reconstruct 2D matrix from encoded string (row by row)
     * 2. Read diagonally from top-left to bottom-right for each column start
     * 3. Remove trailing spaces from decoded message
     * 
     * Visual Example (rows=4):
     * "iveo    eed   l te   olc" → Matrix:
     * i v e o     
     *   e e d    
     *     l   t e
     *       o l c
     * 
     * Read diagonals: i→ →e→l→o, v→e→ →l, e→e→t→c, o→d→e, etc.
     * 
     * Time: O(rows * cols), Space: O(rows * cols)
     */
    public String decodeCiphertext(String encodedText, int rows) {
        // Calculate number of columns based on total length and rows
        int cols = encodedText.length() / rows;
        
        // Edge case: empty string
        if (encodedText.isEmpty()) {
            return "";
        }
        
        // Step 1: Reconstruct 2D matrix from encoded string
        char[][] matrix = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Fill matrix row by row from encoded text
                matrix[i][j] = encodedText.charAt(k);
                k++;
            }
        }
        
        // Step 2: Read diagonally starting from each column in first row
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            // Start diagonal from position (0, i)
            int row = 0;
            int col = i;

            // Traverse diagonal: move down-right (row++, col++)
            while (col < cols && row < rows) {
                str.append(matrix[row][col]);
                row++;
                col++;
            }
        }

        // Step 3: Remove trailing spaces from decoded message
        int l = str.length() - 1;
        while (str.charAt(l) == ' ') {
            str.deleteCharAt(l);
            l--;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String encodedText = "iveo    eed   l te   olc";
        int row = 4;

        System.out.println(new DecodeTheSlantedCiphertext().decodeCiphertext(encodedText, row));
    }
}
