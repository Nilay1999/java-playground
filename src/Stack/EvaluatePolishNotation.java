package Stack;

import java.util.Stack;

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
