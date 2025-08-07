package Stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;

public class RemoveDuplicateLetters {
    public static String removeDuplicateLetter(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Integer> idx = new HashMap<Character, Integer>();
        HashSet<Character> visited = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            idx.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (visited.contains(c)) continue;
            while (!stack.isEmpty() && stack.peek() > c && idx.get(stack.peek()) > i) {
                visited.remove(stack.peek());
                stack.pop();
            }
            visited.add(c);
            stack.push(c);
        }

        return stack.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(removeDuplicateLetter(s));
    }
}
