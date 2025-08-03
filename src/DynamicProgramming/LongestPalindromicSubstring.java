package DynamicProgramming;

public class LongestPalindromicSubstring {
    // Helper method to check if a string is a palindrome
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
        // TODO: Implement Brute Force approach
        return "";
    }

    // Dynamic Programming placeholder
    public String dynamicProgramming(String str) {
        // TODO: Implement Dynamic Programming approach
        return "";
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        System.out.println(solution.twoPointer("babada"));
    }
}

