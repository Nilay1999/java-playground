package Stack;

import java.util.Arrays;
import java.util.Stack;

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
