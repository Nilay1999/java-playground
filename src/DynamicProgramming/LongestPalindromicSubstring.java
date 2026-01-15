package DynamicProgramming;

public class LongestPalindromicSubstring {

    // Two Pointer (Expand Around Center) approach
    public String twoPointer(String str) {
        int max = 0;
        String res = "";
        int n = str.length();

        for (int i = 0; i < n; i++) {
            // Odd length palindrome
            int left = i;
            int right = i;
            while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > max) {
                    res = str.substring(left, right + 1);
                    max = right - left + 1;
                }
                left--;
                right++;
            }

            // Even length palindrome
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > max) {
                    res = str.substring(left, right + 1);
                    max = right - left + 1;
                }
                left--;
                right++;
            }
        }

        return res;
    }

    public String bruteforce(String str) {
        int n = str.length();
        int max = Integer.MIN_VALUE;
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindromic(i, j, str)) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        ans = str.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;
    }

    private boolean isPalindromic(int left, int right, String str) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Memoization approach (Top-Down DP)
    // memo[i][j]: 0 = not computed, 1 = palindrome, -1 = not palindrome
    public String dynamicProgramming(String str) {
        int n = str.length();
        if (n == 0) return "";
        
        int[][] memo = new int[n][n];
        int start = 0;
        int maxLen = 1;
        
        // Check all substrings using memoization
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindromeMemo(str, i, j, memo) && (j - i + 1) > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        
        return str.substring(start, start + maxLen);
    }
    
    private boolean isPalindromeMemo(String str, int i, int j, int[][] memo) {
        // Base case: single char or empty
        if (i >= j) return true;
        
        // Return cached result if already computed
        if (memo[i][j] != 0) {
            return memo[i][j] == 1;
        }
        
        // Check if palindrome: chars match and inner substring is also palindrome
        if (str.charAt(i) == str.charAt(j) && isPalindromeMemo(str, i + 1, j - 1, memo)) {
            memo[i][j] = 1;
            return true;
        }
        
        memo[i][j] = -1;
        return false;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        System.out.println(solution.twoPointer("babada"));
    }
}
