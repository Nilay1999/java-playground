package Stack;

import java.util.Stack;

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
