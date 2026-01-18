package Stack;

import java.util.Stack;

/**
 * Basic Calculator II Algorithm (Stack for Operator Precedence):
 * Evaluate expression with +, -, *, / operators (no parentheses).
 * 
 * KEY INSIGHT: Handle operator precedence using stack:
 * - +, -: Push to stack (defer calculation)
 * - *, /: Calculate immediately with stack top
 * 
 * ALGORITHM:
 * 1. Process expression character by character
 * 2. Build numbers digit by digit
 * 3. When encountering operator (or end of string):
 *    - Apply previous operator with current number
 *    - +: push positive number to stack
 *    - -: push negative number to stack  
 *    - *: pop stack, multiply, push result
 *    - /: pop stack, divide, push result
 * 4. Sum all values in stack for final result
 * 
 * WHY THIS WORKS:
 * - Stack handles addition/subtraction naturally
 * - Multiplication/division processed immediately (higher precedence)
 * - No need to worry about operator precedence parsing
 * 
 * Example: "3+5*2-1"
 * - 3: prev='+' → stack=[3]
 * - 5: prev='*' → pop 3, push 3*5=15 → stack=[15]  
 * - 2: prev='-' → stack=[15, -2]
 * - End: sum = 15 + (-2) = 13
 * 
 * Time: O(n), Space: O(n)
 */
public class BasicCalculatorII {
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        char prev = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            if (curr == ' ') {
                continue;
            }

            if (Character.isDigit(curr)) {
                num = num * 10 + (curr - '0');
            }

            if (isOperator(curr)) {
                if (prev == '+') {
                    stack.push(num);
                } else if (prev == '-') {
                    stack.push(-num);
                } else if (prev == '*') {
                    int k = stack.pop();
                    stack.push(k * num);
                } else {
                    int k = stack.pop();
                    stack.push(k / num);
                }
                num = 0;
                prev = curr;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    private static boolean isOperator(char c) {
        return c == '-' || c == '+' || c == '*' || c == '/';
    }

    public static void main(String[] args) {
        String expr = " 3+5-6/2*7/2";
        System.out.println(calculate(expr));
    }
}
