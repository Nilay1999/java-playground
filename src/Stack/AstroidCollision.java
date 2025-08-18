package Stack;

import java.util.Arrays;
import java.util.Stack;

public class AstroidCollision {

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty() || asteroid > 0) {
                stack.push(asteroid);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -asteroid) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroid);
                }
                if (stack.peek() == -asteroid) {
                    stack.pop();
                }
            }
            System.out.println(stack);
        }

        int[] ans = new int[stack.size()];
        for (int j = ans.length - 1; j >= 0; j--) {
            ans[j] = stack.pop();
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, -5, 10, -4, 6, 1, -14, -4};
        System.out.println(Arrays.toString(asteroidCollision(arr)));
    }
}
