package Stack;

import java.util.Stack;

public class BasicCalculator {
    /**
     * Basic Calculator Algorithm (Stack for Parentheses):
     * Evaluate mathematical expression with +, -, (, ), and spaces.
     * 
     * ALGORITHM:
     * 1. Process character by character
     * 2. Build numbers digit by digit
     * 3. Apply operations immediately when encountering +/-
     * 4. Handle parentheses with stack:
     *    - '(': Push current result and sign to stack, reset for sub-expression
     *    - ')': Complete sub-expression, pop sign and previous result, combine
     * 
     * STACK USAGE:
     * - Push: [previous_result, sign_before_parentheses]
     * - Pop: Apply sign to sub-expression result, add to previous result
     * 
     * Example: "1-(2+3)" → 1 - 5 = -4
     * 
     * Time: O(n), Space: O(n) for stack in worst case (nested parentheses)
     */
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        int sign = 1;
        int num = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == ' ') {
                continue;
            }
            if (Character.isDigit(curr)) {
                num = num * 10 + (curr - '0');
            } else if (curr == '+') {
                res += num * sign;
                num = 0;
                sign = 1;
            } else if (curr == '-') {
                res += num * sign;
                num = 0;
                sign = -1;
            } else if (curr == '(') {
                stack.push(res);
                stack.push(sign);
                num = 0;
                res = 0;
                sign = 1;
            }
            if (curr == ')') {
                res += num * sign;

                res *= stack.pop();
                res += stack.pop();

                num = 0;
            }
        }

        return res + num;
    }

    private static boolean isOperator(char c) {
        return c == '-' || c == '+';
    }

    public static void main(String[] args) {
        String expr = "1 + 2";
        System.out.println(calculate(expr));
    }
}
