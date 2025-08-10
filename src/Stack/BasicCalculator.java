package Stack;

import java.util.Stack;

public class BasicCalculator {
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
