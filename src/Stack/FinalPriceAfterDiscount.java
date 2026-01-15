package Stack;

import java.util.Arrays;
import java.util.Stack;

public class FinalPriceAfterDiscount {
    public int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = prices[i];
            } else {
                ans[i] = prices[i] - stack.peek();
            }
            stack.push(prices[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = { 8, 4, 6, 2, 3 };
        int[] ans = new FinalPriceAfterDiscount().finalPrices(prices);
        System.out.println(Arrays.toString(ans));
    }
}
