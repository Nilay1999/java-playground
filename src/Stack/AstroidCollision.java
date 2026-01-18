package Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Asteroid Collision Algorithm (Stack Simulation):
 * Simulate asteroid collisions where positive = moving right, negative = moving left.
 * 
 * COLLISION RULES:
 * - Same direction: no collision
 * - Opposite direction: collision occurs
 * - Larger asteroid survives, smaller explodes
 * - Equal size: both explode
 * 
 * STACK APPROACH:
 * 1. Stack stores asteroids moving right (positive values)
 * 2. For each asteroid:
 *    - If positive or stack empty: add to stack
 *    - If negative: check for collisions with stack top
 * 3. Handle collision cases:
 *    - Current asteroid larger: pop stack, continue checking
 *    - Stack asteroid larger: current asteroid explodes
 *    - Equal size: both explode (pop stack)
 * 
 * KEY INSIGHT: Only right-moving asteroids can collide with left-moving ones.
 * Left-moving asteroids never collide with each other.
 * 
 * Example: [5,10,-5,10,-4,6,1,-14,-4]
 * - 5,10: moving right → stack=[5,10]
 * - -5: collides with 10, 10 wins → stack=[5,10]
 * - 10: moving right → stack=[5,10,10]
 * - -4: collides with 10, 10 wins → stack=[5,10,10]
 * - 6,1: moving right → stack=[5,10,10,6,1]
 * - -14: destroys 1,6,10,10, survives → stack=[5,-14]
 * - -4: moving left, no collision → stack=[5,-14,-4]
 * 
 * Time: O(n), Space: O(n)
 */
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
