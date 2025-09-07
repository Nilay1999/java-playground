package Stack;

import java.util.Stack;

public class InfixToPostfix {

    static boolean isOperator(char x) {
        return (x == '+' || x == '-' || x == '*' || x == '/' || x == '^' || x == '%');
    }

    static int prec(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    public static String infixToPostfix(String exp) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c))
                result.append(c);

            else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && prec(c) <= prec(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static String postfixToInfix(String exp) {
        int n = exp.length();

        Stack<String> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            char curr = exp.charAt(i);

            if (isOperator(curr)) {
                String c1 = stack.pop();
                String c2 = stack.pop();
                String s = String.valueOf('(' + c1 + curr + c2 + ')');
                stack.push(s);
            } else {
                stack.push(curr + "");
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String exp = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPostfix(exp));
        System.out.println(postfixToInfix("*-A/BC-/AKL\""));
    }
}
