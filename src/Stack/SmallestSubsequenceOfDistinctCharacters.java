package Stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class SmallestSubsequenceOfDistinctCharacters {
    public String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> visited = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (visited.contains(current))
                continue;

            while (!stack.isEmpty() && stack.peek() > current && map.get(stack.peek()) > i) {
                visited.remove(stack.peek());
                stack.pop();
            }
            stack.push(current);
            visited.add(current);
        }

        return stack.stream().map(Object::toString).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String s = "bcabc";
        System.out.println(new SmallestSubsequenceOfDistinctCharacters().smallestSubsequence(s));
    }
}
