package Stack;

import java.util.Stack;

/**
 * Remove K Digits Algorithm (Greedy Stack):
 * Remove k digits to get smallest possible number.
 * 
 * GREEDY STRATEGY:
 * 1. Remove larger digits when smaller digit appears after them
 * 2. Use stack to maintain digits in increasing order
 * 3. If k digits remain after processing, remove from end
 * 4. Remove leading zeros
 * 
 * ALGORITHM:
 * 1. For each digit in number:
 *    - While k > 0 AND stack not empty AND top > current digit:
 *      - Pop stack (remove larger digit)
 *      - Decrement k
 *    - Push current digit
 * 2. If k > 0 after loop: remove k digits from end
 * 3. Remove leading zeros
 * 4. Return result or "0" if empty
 * 
 * WHY THIS WORKS:
 * - Smaller digits at front make smaller number
 * - Stack maintains increasing sequence
 * - Larger digits are removed when smaller ones available
 * 
 * Example: "1432219", k=3
 * 
 * Process digits:
 * '1': stack=[] → push '1' → ['1']
 * '4': '4'>'1' → push '4' → ['1','4']
 * '3': '3'<'4' and k>0 → pop '4', k=2, push '3' → ['1','3']
 * '2': '2'<'3' and k>0 → pop '3', k=1, push '2' → ['1','2']
 * '2': '2'='2' → push '2' → ['1','2','2']
 * '1': '1'<'2' and k>0 → pop '2', k=0, push '1' → ['1','2','1']
 * '9': '9'>'1' → push '9' → ['1','2','1','9']
 * 
 * Result: "1219"
 * 
 * Time: O(n), Space: O(n)
 */
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
