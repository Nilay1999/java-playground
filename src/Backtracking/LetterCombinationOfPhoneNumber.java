package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfPhoneNumber {
    /**
     * Phone Number Letter Combinations Algorithm (Backtracking):
     * Generate all possible letter combinations from phone keypad digits.
     * 
     * Phone mapping: 2→abc, 3→def, 4→ghi, 5→jkl, 6→mno, 7→pqrs, 8→tuv, 9→wxyz
     * 
     * Backtracking Strategy:
     * 1. For each digit, try all possible letters
     * 2. Recursively build combinations for remaining digits
     * 3. Base case: processed all digits → add combination to result
     * 4. Backtrack: remove last character and try next option
     * 
     * Example: "23" → ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 
     * Time: O(3^N × 4^M) where N=digits with 3 letters, M=digits with 4 letters
     * Space: O(3^N × 4^M) for storing results
     */
    private static final Map<Integer, String> MAP = new HashMap<>();

    static {
        MAP.put(2, "abc");
        MAP.put(3, "def");
        MAP.put(4, "ghi");
        MAP.put(5, "jkl");
        MAP.put(6, "mno");
        MAP.put(7, "pqrs");
        MAP.put(8, "tuv");
        MAP.put(9, "wxyz");
    }

    /**
     * Generates all possible letter combinations for a given string of digits.
     *
     * @param digits The input string containing digits from '2' to '9'.
     * @return A list of all possible letter combinations.
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        backtrack(res, digits, 0, new StringBuilder());
        return res;
    }

    private static void backtrack(List<String> res, String digits, int idx, StringBuilder str) {
        if (idx == digits.length()) {
            res.add(str.toString());
            return;
        }
        int key = Character.getNumericValue(digits.charAt(idx));
        String letters = MAP.getOrDefault(key, "");
        for (char s : letters.toCharArray()) {
            str.append(s);
            backtrack(res, digits, idx + 1, str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println(letterCombinations("23")); // Output: [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(letterCombinations("")); // Output: []
        System.out.println(letterCombinations("2")); // Output: [a, b, c]
    }
}