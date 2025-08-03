package DynamicProgramming;

public class EditDistance {
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

