package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class WildCardMatching {
    private final Map<String, Boolean> memo;

    public WildCardMatching() {
        memo = new HashMap<>();
    }

    private boolean recursion(int i, int j, String str, String pat) {
        String key = i + "-" + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (i < 0 && j < 0) {
            return true;
        }
        if (j < 0) {
            return false;
        }
        if (i < 0) {
            for (int idx = j; idx >= 0; idx--) {
                if (pat.charAt(idx) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (str.charAt(i) == pat.charAt(j) || pat.charAt(j) == '?') {
            boolean result = recursion(i - 1, j - 1, str, pat);
            memo.put(key, result);
            return result;
        } else if (pat.charAt(j) == '*') {
            boolean considerStar = recursion(i, j - 1, str, pat);
            boolean notConsiderStar = recursion(i - 1, j, str, pat);
            boolean result = considerStar || notConsiderStar;
            memo.put(key, result);
            return result;
        } else {
            memo.put(key, false);
            return false;
        }
    }

    public boolean memoization(String str, String pat) {
        memo.clear();  // Important for fresh runs
        int n = str.length();
        int m = pat.length();
        return recursion(n - 1, m - 1, str, pat);
    }

    public boolean tabulation(String str, String pat) {
        int n = str.length();
        int m = pat.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for (int p = 1; p <= m; p++) {
            if (pat.charAt(p - 1) == '*') {
                dp[0][p] = dp[0][p - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (pat.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pat.charAt(j - 1) == '?' || pat.charAt(j - 1) == str.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        WildCardMatching wildCard = new WildCardMatching();
        String st1 = "ab";
        String st2 = "a*";
        System.out.println(wildCard.tabulation(st1, st2));  // Output: true
        System.out.println(wildCard.memoization(st1, st2)); // Output: true
    }
}

