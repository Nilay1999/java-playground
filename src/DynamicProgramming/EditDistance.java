package DynamicProgramming;

public class EditDistance {
    /**
     * Edit Distance Algorithm (Levenshtein Distance):
     * Find minimum operations to transform word1 into word2.
     * Operations: insert, delete, replace
     * 
     * DP State: dp[i][j] = min operations to transform word2[0..i-1] to word1[0..j-1]
     * 
     * Recurrence:
     * - If chars match: dp[i][j] = dp[i-1][j-1] (no operation needed)
     * - Else: dp[i][j] = 1 + min(
     *     dp[i-1][j],     // delete from word2
     *     dp[i][j-1],     // insert into word2  
     *     dp[i-1][j-1]    // replace in word2
     *   )
     * 
     * Base cases: dp[i][0] = i (delete all), dp[0][j] = j (insert all)
     * 
     * Time: O(m×n), Space: O(m×n)
     */
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int up = dp[i - 1][j];     // delete
                int left = dp[i][j - 1];   // insert
                int cross = dp[i - 1][j - 1]; // replace or match

                if (word2.charAt(i - 1) == word1.charAt(j - 1)) {
                    dp[i][j] = cross;  // No operation
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(up, left), cross);  // Minimum of insert, delete, replace
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Edit Distance: " + minDistance(word1, word2));  // Output: 3
    }
}

