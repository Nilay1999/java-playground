package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    /**
     * Generate Valid Parentheses Algorithm (Backtracking):
     * Generate all valid combinations of n pairs of parentheses.
     * 
     * Constraints for valid parentheses:
     * 1. At any point, number of '(' >= number of ')'
     * 2. Total '(' = Total ')' = n
     * 
     * Backtracking Strategy:
     * - Add '(' if we haven't used all n opening brackets
     * - Add ')' if it won't make string invalid (more ')' than '(')
     * - Base case: string length = 2*n
     * 
     * Example: n=3 → ["((()))", "(()())", "(())()", "()(())", "()()()"]
     * 
     * Time: O(4^n / √n) - Catalan number, Space: O(4^n / √n)
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(0, 0, "", n, res);
        return res;
    }

    private static void backtrack(int left, int right, String str, int n, List<String> res) {
        if (str.length() == n * 2) {
            res.add(str);
            return;
        }
        if (right < n) {
            backtrack(left, right + 1, str + "(", n, res);
        }
        if (left < right) {
            backtrack(left + 1, right, str + ")", n, res);
        }
    }

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        System.out.println(result);  // Output: all valid combinations for n = 3
    }
}

