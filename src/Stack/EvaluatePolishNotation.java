package Stack;

import java.util.Stack;

/**
 * Evaluate Reverse Polish Notation (RPN) Algorithm:
 * Evaluate mathematical expression in postfix notation using stack.
 * 
 * POSTFIX NOTATION (RPN):
 * - Operands come before operators
 * - Example: "3 4 +" means 3 + 4 = 7
 * - No parentheses needed, unambiguous
 * 
 * STACK-BASED ALGORITHM:
 * 1. Scan tokens from left to right
 * 2. If token is number: push to stack
 * 3. If token is operator:
 *    - Pop two operands (b = top, a = second)
 *    - Apply operation: a operator b
 *    - Push result back to stack
 * 4. Final result is the only element left in stack
 * 
 * KEY INSIGHT: Stack naturally handles operand ordering
 * For subtraction/division, order matters: (b - a) not (a - b)
 * 
 * Example: ["2","1","+","3","*"]
 * - Push 2: [2]
 * - Push 1: [2,1]
 * - "+": pop 1,2 → 2+1=3 → [3]
 * - Push 3: [3,3]
 * - "*": pop 3,3 → 3*3=9 → [9]
 * Result: 9
 * 
 * Time: O(n), Space: O(n)
 */
public class EvaluatePolishNotation {
    class Solution {
        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();
            for (String exp : tokens) {
                if (exp.equals("+")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                } else if (exp.equals("-")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                } else if (exp.equals("*")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b * a);
                } else if (exp.equals("/")) {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                } else {
                    stack.push(Integer.parseInt(exp));
                }
            }
            return stack.peek();
        }
    }
}
