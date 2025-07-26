package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfPhoneNumber {
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
     * @param digits The input string containing digits from '2' to '9'.
     * @return A list of all possible letter combinations.
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        int n = digits.length();
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
        System.out.println(letterCombinations(""));   // Output: []
        System.out.println(letterCombinations("2"));  // Output: [a, b, c]
    }
}