package Stack;

import java.util.Stack;

/**
 * Longest Valid Parentheses Algorithm:
 * Find length of longest valid (well-formed) parentheses substring.
 * 
 * TWO APPROACHES:
 * 
 * 1. STACK APPROACH:
 *    - Push -1 initially as base
 *    - For '(': push index
 *    - For ')': pop and calculate length = i - stack.peek()
 *    - If stack becomes empty after pop, push current index as new base
 * 
 * 2. TWO-PASS COUNTER APPROACH:
 *    - Left-to-right pass: count '(' and ')'
 *      When left == right: valid substring found
 *      When right > left: reset counters
 *    - Right-to-left pass: count ')' and '('
 *      When left == right: valid substring found
 *      When left > right: reset counters
 *    - Return maximum from both passes
 * 
 * STACK ALGORITHM DETAILS:
 * - Stack stores indices of unmatched parentheses
 * - When ')' matches '(': pop and calculate valid length
 * - If stack empty after pop: push ')' index as new base
 * 
 * Example: "(()" 
 * i=0 '(': push 0 → [-1,0]
 * i=1 '(': push 1 → [-1,0,1]
 * i=2 ')': pop 1, length = 2-0 = 2 → [-1,0]
 * Result: 2
 * 
 * Example: ")()())"
 * i=0 ')': pop -1, push 0 → [0]
 * i=1 '(': push 1 → [0,1]
 * i=2 ')': pop 1, length = 2-0 = 2 → [0]
 * i=3 '(': push 3 → [0,3]
 * i=4 ')': pop 3, length = 4-0 = 4 → [0]
 * i=5 ')': pop 0, push 5 → [5]
 * Result: 4
 * 
 * Time: O(n), Space: O(n) for stack
 */
public class LongestValidBraces {
    private static Stack<Integer> stack;

    public static int stack(String s) {
        int n = s.length();
        stack.push(-1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

    public static int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int n = s.length();
        int leftMax = 0;
        int rightMax = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = 0;
                right = 0;
            } else if (left == right) {
                leftMax = Math.max(leftMax, left + right);
            }
        }

        left = 0;
        right = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                right++;
            } else {
                left++;
            }
            if (left > right) {
                left = 0;
                right = 0;
            } else if (left == right) {
                rightMax = Math.max(rightMax, left + right);
            }
        }

        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        String input = "()(()";
        stack = new Stack<Integer>();
        System.out.println(stack(input));
    }

}
