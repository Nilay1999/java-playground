package stack;

import java.util.Stack;

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
