package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildArrayWithStackOperations {
    public List<String> buildArray(int[] target, int n) {
        Stack<Integer> stack = new Stack<>();
        List<String> res = new ArrayList<>();
        int len = target.length;

        for (int i = len - 1; i >= 0; i--) {
            stack.push(target[i]);
        }

        System.out.println(stack);

        for (int i = 1; i <= n; i++) {
            if (stack.isEmpty()) {
                return res;
            }
            if (i == stack.peek()) {
                res.add("Push");
                stack.pop();
            } else {
                res.add("Push");
                res.add("Pop");
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new BuildArrayWithStackOperations().buildArray(new int[] { 1, 2, 4 }, 4));
    }
}
