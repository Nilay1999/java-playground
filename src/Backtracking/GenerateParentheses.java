package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
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

