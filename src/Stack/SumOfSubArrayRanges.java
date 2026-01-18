package Stack;

import java.util.Stack;

/**
 * Sum of Subarray Ranges Algorithm (Contribution Technique):
 * Calculate sum of (max - min) for all subarrays.
 * 
 * APPROACH:
 * 1. Sum of ranges = Sum of maximums - Sum of minimums
 * 2. For each element, calculate its contribution as max and as min
 * 3. Use monotonic stack to find boundaries
 * 
 * CONTRIBUTION AS MAXIMUM:
 * - Find previous greater element (PGE) and next greater element (NGE)
 * - Element is maximum in subarrays between PGE and NGE
 * - Count = (i - PGE) * (NGE - i)
 * - Contribution = element * count
 * 
 * CONTRIBUTION AS MINIMUM:
 * - Find previous smaller element (PSE) and next smaller element (NSE)
 * - Element is minimum in subarrays between PSE and NSE
 * - Count = (i - PSE) * (NSE - i)
 * - Contribution = element * count
 * 
 * MONOTONIC STACK PATTERNS:
 * - Next Greater: traverse right to left, pop smaller elements
 * - Prev Greater: traverse left to right, pop smaller/equal elements
 * - Next Smaller: traverse right to left, pop larger elements
 * - Prev Smaller: traverse left to right, pop larger/equal elements
 * 
 * Example: [6,8,0,1,3]
 * 
 * For element 6 (i=0):
 * - As max: PGE=-1, NGE=1 → count=(0-(-1))*(1-0)=1, contrib=6*1=6
 * - As min: PSE=-1, NSE=2 → count=(0-(-1))*(2-0)=2, contrib=6*2=12
 * 
 * For element 8 (i=1):
 * - As max: PGE=-1, NGE=5 → count=(1-(-1))*(5-1)=8, contrib=8*8=64
 * - As min: PSE=0, NSE=2 → count=(1-0)*(2-1)=1, contrib=8*1=8
 * 
 * Time: O(n), Space: O(n)
 */
public class SumOfSubArrayRanges {

    // Next Greater Element (index)
    private static int[] nextGreater(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return ans;
    }

    // Prev Greater Element (index)
    private static int[] prevGreater(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ans;
    }

    // Next Smaller Element (index)
    private static int[] nextSmaller(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return ans;
    }

    // Prev Smaller Element (index)
    private static int[] prevSmaller(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ans;
    }

    // Main method to compute sum of subarray ranges
    public static long subArrayRanges(int[] arr) {
        int n = arr.length;

        int[] nextGreater = nextGreater(arr);
        int[] prevGreater = prevGreater(arr);
        int[] nextSmaller = nextSmaller(arr);
        int[] prevSmaller = prevSmaller(arr);

        long sumMax = 0, sumMin = 0;

        for (int i = 0; i < n; i++) {
            // Contribution as maximum
            long left = i - prevGreater[i];
            long right = nextGreater[i] - i;
            sumMax += (long) arr[i] * left * right;

            // Contribution as minimum
            left = i - prevSmaller[i];
            right = nextSmaller[i] - i;
            sumMin += (long) arr[i] * left * right;
        }

        return sumMax - sumMin;
    }

    public static void main(String[] args) {
        int[] arr = {6, 8, 0, 1, 3};
        System.out.println("Sum of Subarray Ranges = " + subArrayRanges(arr));
    }
}
