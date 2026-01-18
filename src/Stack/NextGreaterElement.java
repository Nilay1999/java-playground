package Stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    /**
     * Next Greater Element Algorithms (Monotonic Stack):
     * 
     * PROBLEM I: Find next greater element for subset in another array
     * 1. Build next greater mapping for main array using monotonic stack
     * 2. Use mapping to answer queries for subset
     * 
     * PROBLEM II: Circular array - next greater element with wraparound
     * 1. Process array twice (2n iterations) to simulate circular behavior
     * 2. Use modulo to wrap indices: arr[i % n]
     * 3. Only store results in first n iterations
     * 
     * MONOTONIC STACK PATTERN:
     * - Traverse right to left
     * - Pop smaller/equal elements (maintain decreasing stack)
     * - Top of stack = next greater element
     * - Push current element
     * 
     * Time: O(n), Space: O(n)
     */

    // Next Greater Element I: Find next greater for subset in main array
    public static int[] nextGreaterElementI(int[] subarr, int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] nextGreater = new int[10001]; // mapping: value → next greater

        // Build next greater mapping for main array (right to left)
        for (int i = arr.length - 1; i >= 0; i--) {
            // Pop smaller or equal elements
            while (!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }
            // Top of stack is next greater, or -1 if stack empty
            nextGreater[arr[i]] = stack.isEmpty() ? -1 : stack.peek();
            // Push current element
            stack.push(arr[i]);
        }

        // Use mapping to answer queries for subset
        for (int i = 0; i < subarr.length; i++) {
            subarr[i] = nextGreater[subarr[i]];
        }

        return subarr;
    }

    // Next Greater Element II: Circular array with wraparound
    public static int[] nextGreaterElementII(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] answer = new int[n];
        
        // Process array twice (2n iterations) to simulate circular behavior
        for (int i = 2 * n - 1; i >= 0; i--) {
            // Pop smaller or equal elements
            while (!stack.isEmpty() && arr[i % n] >= stack.peek()) {
                stack.pop();
            }
            // Only store results for first n iterations
            if (i < n) {
                answer[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            // Push current element (use modulo for circular access)
            stack.push(arr[i % n]);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] num = {4, 1, 2};
        int[] array = {1, 3, 4, 2};

        int[] ans1 = nextGreaterElementI(num, array);
        int[] ans2 = nextGreaterElementII(array);

        System.out.println(Arrays.toString(ans1));
        System.out.println(Arrays.toString(ans2));
    }
}
