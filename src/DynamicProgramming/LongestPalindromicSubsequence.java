package DynamicProgramming;

public class LongestPalindromicSubsequence {

    private int dfs(int i, int j, String s, int[][] dp) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 2 + dfs(i + 1, j - 1, s, dp);
        } else {
            int option1 = dfs(i, j - 1, s, dp);
            int option2 = dfs(i + 1, j, s, dp);
            dp[i][j] = Math.max(option1, option2);
        }

        return dp[i][j];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Initialize all values to -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return dfs(0, n - 1, s, dp);
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence solver = new LongestPalindromicSubsequence();
        String input = "bbbab";
        int result = solver.longestPalindromeSubseq(input);
        System.out.println("Longest Palindromic Subsequence Length: " + result);  // Output: 4
    }
}

