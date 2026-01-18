package Stack;

import java.util.Stack;

/**
 * Simplify Unix Path Algorithm (Stack):
 * Convert absolute Unix path to simplified canonical path.
 * 
 * PROBLEM DESCRIPTION:
 * - Given absolute Unix path with special characters
 * - "." = current directory (ignore)
 * - ".." = parent directory (go up one level)
 * - "/" = directory separator
 * - Simplify to canonical form (shortest absolute path)
 * 
 * ALGORITHM APPROACH:
 * 1. Split path by "/" to get directory names
 * 2. Use stack to build canonical path
 * 3. For each part:
 *    - If empty or ".": skip (current directory)
 *    - If "..": pop from stack (go to parent)
 *    - Otherwise: push to stack (enter directory)
 * 4. Build result by joining stack contents with "/"
 * 5. Return "/" if stack empty, else "/dir1/dir2/..."
 * 
 * KEY INSIGHTS:
 * - Stack naturally handles directory navigation
 * - ".." pops previous directory (go up)
 * - "." and empty strings are ignored
 * - Result always starts with "/" (absolute path)
 * - Can't go above root (pop when stack empty does nothing)
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: path = "/a/./b/../../c/"
 * 
 * Split: ["", "a", ".", "b", "..", "..", "c", ""]
 * 
 * Process:
 * "": empty, skip
 * "a": push → stack = [a]
 * ".": current dir, skip
 * "b": push → stack = [a, b]
 * "..": pop → stack = [a]
 * "..": pop → stack = []
 * "c": push → stack = [c]
 * "": empty, skip
 * 
 * Result: "/c"
 * 
 * Example: path = "/../"
 * 
 * Split: ["", "..", ""]
 * 
 * Process:
 * "": empty, skip
 * "..": pop (stack empty, nothing to pop)
 * "": empty, skip
 * 
 * Result: "/" (can't go above root)
 * 
 * Time: O(n) where n = length of path
 * Space: O(n) for stack
 */
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
