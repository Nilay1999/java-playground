package Stack;

import java.util.Stack;

/**
 * Sum of Subarray Minimums Algorithm (Monotonic Stack + Contribution):
 * Calculate sum of minimum elements across all subarrays.
 * 
 * BRUTE FORCE O(n²):
 * - For each starting position i, find minimum in all subarrays starting at i
 * - Sum all minimums
 * 
 * OPTIMIZED APPROACH O(n) - CONTRIBUTION TECHNIQUE:
 * 1. For each element, calculate how many subarrays it's the minimum of
 * 2. Contribution = element * count_of_subarrays
 * 3. Sum all contributions
 * 
 * FINDING CONTRIBUTION:
 * - For element at index i, find:
 *   - Previous Smaller Element Index (PSEI): nearest smaller element to left
 *   - Next Smaller or Equal Index (NSEI): nearest smaller/equal element to right
 * - Left boundary: PSEI + 1
 * - Right boundary: NSEI - 1
 * - Count = (i - PSEI) * (NSEI - i)
 * 
 * MONOTONIC STACK FOR PSEI:
 * - Traverse left to right
 * - Pop elements >= current (they're not smaller)
 * - Stack top is PSEI (or -1 if empty)
 * 
 * MONOTONIC STACK FOR NSEI:
 * - Traverse right to left
 * - Pop elements > current (use >= to handle duplicates)
 * - Stack top is NSEI (or n if empty)
 * 
 * Example: [3,1,2,4]
 * 
 * PSEI: [-1, -1, 0, 1]
 * NSEI: [1, 4, 4, 4]
 * 
 * Element 3 (i=0): left=(0-(-1))=1, right=(1-0)=1, count=1*1=1, contrib=3*1=3
 * Element 1 (i=1): left=(1-(-1))=2, right=(4-1)=3, count=2*3=6, contrib=1*6=6
 * Element 2 (i=2): left=(2-0)=2, right=(4-2)=2, count=2*2=4, contrib=2*4=8
 * Element 4 (i=3): left=(3-1)=2, right=(4-3)=1, count=2*1=2, contrib=4*2=8
 * 
 * Total: 3+6+8+8 = 25
 * 
 * Time: O(n), Space: O(n)
 */
public class SumOfSubarrayMinimums {
    public static final int MOD = 1_000_000_007;

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] nsei = new int[n];
        int[] psei = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            psei[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nsei[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - nsei[i];
            long right = psei[i] - i;

            sum = sum + (left * right * arr[i]);
        }

        return (int) (sum % MOD);
    }

    public static int sumSubarrayMinsBruteForce(int[] arr) {
        int n = arr.length;
        int min, sum = 0;
        for (int i = 0; i < n; i++) {
            min = arr[i];
            for (int j = i; j < n; j++) {
                min = Math.min(min, arr[j]);
                sum += min;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println(sumSubarrayMins(arr));
        System.out.println(sumSubarrayMinsBruteForce(arr));
    }
}
