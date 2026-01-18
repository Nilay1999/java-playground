package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Palindrome Partitioning Algorithm (Backtracking):
 * Partition a string into all possible palindromic substrings.
 * 
 * PROBLEM DESCRIPTION:
 * - Given string, find all ways to partition it into palindromes
 * - Each partition must consist entirely of palindromic substrings
 * 
 * BACKTRACKING STRATEGY:
 * 1. Try all possible partitions starting from current index
 * 2. For each position, check if substring [idx, i] is palindrome
 * 3. If palindrome, add to current partition and recurse
 * 4. Base case: reached end of string → valid partition found
 * 5. Backtrack: remove last substring and try next partition
 * 
 * PALINDROME CHECK:
 * - Use two pointers from both ends
 * - Compare characters moving inward
 * 
 * Example: "aab" → [["a","a","b"], ["aa","b"]]
 * - Partition 1: "a" + "a" + "b" (all palindromes)
 * - Partition 2: "aa" + "b" (all palindromes)
 * 
 * Time: O(n × 2^n) - 2^n partitions, n to check palindrome
 * Space: O(n) for recursion depth
 */
public class PalindromePartition {

    private static List<List<String>> result;

    // Helper: Check if substring from index l to r is palindrome
    private static boolean isPalindrome(String st, int l, int r) {
        while (l < r) {
            if (st.charAt(l) != st.charAt(r)) {
                return false; // characters don't match
            }
            l++;
            r--;
        }
        return true; // all characters matched
    }

    private static void backtrack(int idx, List<String> temp, String str) {
        // Base case: reached end of string, found valid partition
        if (idx == str.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // Try all possible substrings starting from idx
        for (int i = idx; i < str.length(); i++) {
            // Check if substring [idx, i] is palindrome
            if (isPalindrome(str, idx, i)) {
                // Include this palindromic substring
                temp.add(str.substring(idx, i + 1));
                // Recurse for remaining string
                backtrack(i + 1, temp, str);
                // Backtrack: remove last substring
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<String>> partition(String s) {
        List<String> arr = new ArrayList<>();
        result = new ArrayList<>();
        backtrack(0, arr, s);
        return result;
    }

    public static void main(String[] arg) {
        String s = "aab";
        List<List<String>> ans = partition(s);
        System.out.println(ans);
    }
}
