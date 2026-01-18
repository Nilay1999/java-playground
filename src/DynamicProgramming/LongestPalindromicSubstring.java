package DynamicProgramming;

public class LongestPalindromicSubstring {

    /**
     * Expand Around Center Approach:
     * For each possible center, expand outward while characters match.
     * Check both odd-length (single center) and even-length (two centers) palindromes.
     * 
     * Time: O(n²), Space: O(1)
     */
    public String twoPointer(String str) {
        int max = 0;
        String res = "";
        int n = str.length();

        for (int i = 0; i < n; i++) {
            // Case 1: Odd length palindrome (single character center)
            int left = i;
            int right = i;
            while (left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                // Update result if longer palindrome found
                if (right - left + 1 > max) {
                    res = str.substring(left, right + 1);
                    max = right - left + 1;
                }
                // Expand outward
                left--;
                right++;
            }

            // Case 2: Even length palindrome (two character center)
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

    /**
     * Brute Force Approach:
     * Check all possible substrings for palindrome property.
     * 
     * Time: O(n³), Space: O(1)
     */
    public String bruteforce(String str) {
        int n = str.length();
        int max = Integer.MIN_VALUE;
        String ans = "";
        
        // Try all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Check if substring [i,j] is palindrome
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
        // Check if substring is palindrome using two pointers
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Dynamic Programming with Memoization:
     * Cache palindrome checks to avoid redundant computation.
     * memo[i][j]: 0 = not computed, 1 = palindrome, -1 = not palindrome
     * 
     * Time: O(n²), Space: O(n²)
     */
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
        // Base case: single char or empty substring
        if (i >= j) return true;
        
        // Return cached result if already computed
        if (memo[i][j] != 0) {
            return memo[i][j] == 1;
        }
        
        // Check: outer chars match AND inner substring is palindrome
        if (str.charAt(i) == str.charAt(j) && isPalindromeMemo(str, i + 1, j - 1, memo)) {
            memo[i][j] = 1; // mark as palindrome
            return true;
        }
        
        memo[i][j] = -1; // mark as not palindrome
        return false;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        System.out.println(solution.twoPointer("babada"));
    }
}
