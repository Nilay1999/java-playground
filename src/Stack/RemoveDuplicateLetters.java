package Stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Remove Duplicate Letters Algorithm (Greedy Stack):
 * Remove duplicate letters to get lexicographically smallest subsequence.
 * Each letter appears exactly once in result.
 * 
 * GREEDY STRATEGY:
 * 1. Keep track of last occurrence of each character
 * 2. Use stack to build result
 * 3. For each character:
 *    - If already in result: skip
 *    - If current < stack.top AND stack.top appears later: pop stack
 *    - Add current character to stack
 * 
 * KEY INSIGHT: Can remove larger character if it appears later
 * This allows us to place smaller character earlier
 * 
 * ALGORITHM:
 * 1. Build map of last occurrence index for each character
 * 2. Use visited set to track characters in current stack
 * 3. For each character at index i:
 *    - If already visited: skip
 *    - While stack not empty AND top > current AND top appears after i:
 *      - Pop stack and mark as unvisited
 *    - Add current to stack and mark visited
 * 4. Convert stack to string
 * 
 * Example: "cbacdcbc"
 * Last occurrence: c→7, b→6, a→2, d→5
 * 
 * i=0 'c': stack=[], add 'c' → ['c']
 * i=1 'b': 'b'<'c' and 'c' appears later → pop 'c', add 'b' → ['b']
 * i=2 'a': 'a'<'b' and 'b' appears later → pop 'b', add 'a' → ['a']
 * i=3 'c': 'c'>'a' → add 'c' → ['a','c']
 * i=4 'd': 'd'>'c' → add 'd' → ['a','c','d']
 * i=5 'c': already visited → skip
 * i=6 'b': 'b'<'d' and 'd' appears later → pop 'd', 'b'<'c' and 'c' appears later → pop 'c'
 *          'b'>'a' → add 'b' → ['a','b']
 * i=7 'c': 'c'>'b' → add 'c' → ['a','b','c']
 * Result: "abc"
 * 
 * Time: O(n), Space: O(1) - at most 26 characters
 */
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
