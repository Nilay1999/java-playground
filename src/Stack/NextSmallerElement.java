package Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Next Smaller Element Algorithm (Monotonic Stack):
 * For each element, find the nearest smaller element to its right.
 * 
 * MONOTONIC STACK APPROACH:
 * 1. Traverse array from right to left
 * 2. Maintain stack in decreasing order
 * 3. For each element:
 *    - Pop elements >= current element (they can't be next smaller)
 *    - Top of stack is next smaller element (or -1 if empty)
 *    - Push current element to stack
 * 
 * WHY THIS WORKS:
 * - Stack maintains candidates for "next smaller"
 * - Larger elements are popped (they're not smaller)
 * - First element smaller than current is at stack top
 * 
 * ALGORITHM:
 * 1. Start from rightmost element
 * 2. While stack not empty and top >= current:
 *    - Pop stack (these elements are not smaller)
 * 3. If stack empty: no smaller element to right (-1)
 * 4. Else: stack.peek() is next smaller element
 * 5. Push current element to stack
 * 
 * Example: [4,8,5,2,25]
 * i=4 (25): stack=[] → -1, push 25 → [25]
 * i=3 (2): pop 25, stack=[] → -1, push 2 → [2]
 * i=2 (5): pop nothing, 2<5 → 2, push 5 → [2,5]
 * i=1 (8): pop 5, pop 2, stack=[] → -1, push 8 → [8]
 * i=0 (4): pop 8, stack=[] → -1, push 4 → [4]
 * Result: [-1, -1, 2, -1, -1]
 * 
 * Time: O(n), Space: O(n)
 */
public class NextSmallerElement {

    public static int[] nextSmallerElement(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] indexes = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] < stack.peek()) {
                stack.pop();
            }
            indexes[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return indexes;
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 5, 2, 25};
        System.out.println(Arrays.toString(nextSmallerElement(arr)));
    }
}
