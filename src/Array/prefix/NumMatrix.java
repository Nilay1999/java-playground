package Array.prefix;

/**
 * 2D Prefix Sum Algorithm (Range Sum Query):
 * Efficiently compute sum of elements in any rectangular region of a matrix.
 * 
 * PROBLEM DESCRIPTION:
 * - Given a 2D matrix, support multiple range sum queries
 * - Query: sum of all elements in rectangle from (row1, col1) to (row2, col2)
 * - Optimize for multiple queries (precomputation is key)
 * 
 * ALGORITHM APPROACH:
 * 1. Build 2D prefix sum array during initialization
 * 2. prefix[i][j] = sum of all elements in rectangle from (0,0) to (i-1,j-1)
 * 3. Use inclusion-exclusion principle for range queries
 * 4. Formula: prefix[i][j] = matrix[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1]
 * 
 * KEY INSIGHTS:
 * - 2D prefix sum uses 1-indexed array to avoid boundary checks
 * - Inclusion-exclusion: add diagonal, subtract overlaps
 * - Range sum formula: prefix[r2+1][c2+1] - prefix[r2+1][c1] - prefix[r1][c2+1] + prefix[r1][c1]
 * - Precomputation trades space for query speed
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example matrix:
 * [3, 0, 1]
 * [5, 6, 3]
 * [1, 2, 0]
 * 
 * Prefix array (1-indexed):
 * [0, 0, 0, 0]
 * [0, 3, 3, 4]
 * [0, 8, 14, 18]
 * [0, 9, 17, 24]
 * 
 * Query sum from (1,1) to (2,2):
 * = prefix[3][3] - prefix[3][1] - prefix[1][3] + prefix[1][1]
 * = 24 - 9 - 4 + 0 = 11
 * (Elements: 6 + 3 + 2 + 0 = 11)
 * 
 * Time: O(n*m) preprocessing, O(1) per query
 * Space: O(n*m) for prefix array
 */
public class NumMatrix {
    private int[][] prefix;

    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // Create 1-indexed prefix array (extra row and column for easier calculation)
        this.prefix = new int[row + 1][col + 1];

        // Build 2D prefix sum array
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                // Inclusion-exclusion principle:
                // Current cell = matrix value + left prefix + top prefix - diagonal overlap
                this.prefix[i][j] = matrix[i - 1][j - 1]      // current cell value
                                  + this.prefix[i][j - 1]     // sum from left
                                  + this.prefix[i - 1][j]     // sum from top
                                  - this.prefix[i - 1][j - 1]; // subtract overlap (counted twice)
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // Calculate rectangle sum using inclusion-exclusion
        // Total area - left strip - top strip + top-left corner (added back)
        return prefix[row2 + 1][col2 + 1]    // total sum up to bottom-right
             - prefix[row2 + 1][col1]        // subtract left strip
             - prefix[row1][col2 + 1]        // subtract top strip
             + prefix[row1][col1];           // add back top-left corner (subtracted twice)
    }

    public static void main(String[] args) {
        int[][] mat = {
                { 3, 0, 1, 4, 2 },
                { 5, 6, 3, 2, 1 },
                { 1, 2, 0, 1, 5 },
                { 4, 1, 0, 1, 7 },
                { 1, 0, 3, 0, 5 }
        };
        int[][] points = { { 2, 1, 4, 3 }, { 1, 1, 2, 2 }, { 1, 2, 2, 4 } };
        NumMatrix matrix = new NumMatrix(mat);
        System.out.println(matrix.sumRegion(points[0][1], points[0][1], points[0][2], points[0][3]));
    }
}
