package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class NextGreaterElement {

    public static int[] nextGreaterElementI(int[] subarr, int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] nextGreater = new int[10001];

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < stack.peek()) {
                stack.pop();
            }
            nextGreater[arr[i]] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        for (int i = 0; i < subarr.length; i++) {
            subarr[i] = nextGreater[subarr[i]];
        }

        return subarr;
    }

    public static int[] nextGreaterElementII(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] answer = new int[n];
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i % n] >= stack.peek()) {
                stack.pop();
            }
            if (i < n) {
                answer[i] = stack.isEmpty() ? -1 : stack.peek();
            }
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
