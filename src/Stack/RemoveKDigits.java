package Stack;

import java.util.Stack;

class RemoveKDigits {
    public static String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<Character>();

        for (char digit : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()) {
            str.append(stack.pop());
        }
        str.reverse();
        while (!str.isEmpty() && str.charAt(0) == '0') {
            str.deleteCharAt(0);
        }
        return !str.isEmpty() ? str.toString() : "0";
    }

    public static void main(String[] args) {
        String str = "1432219";
        int k = 3;

        System.out.println(removeKdigits(str, k));
    }
}
