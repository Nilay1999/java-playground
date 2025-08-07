package Stack;

import java.util.Stack;

public class SimplifyPath {

    public static String simplifyPath(String path) {
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String part : parts) {
            if (part.isBlank() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }

        }

        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String path = "/../";
        System.out.println(simplifyPath(path));
    }
}
