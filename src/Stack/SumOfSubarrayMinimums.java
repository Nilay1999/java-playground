package Stack;

import java.util.Stack;

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
