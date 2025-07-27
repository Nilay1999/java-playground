package stack;

import java.util.Stack;

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
                int top = stack.pop();
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
