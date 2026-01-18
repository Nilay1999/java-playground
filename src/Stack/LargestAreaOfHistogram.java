package Stack;

import java.util.Stack;

public class LargestAreaOfHistogram {
    /**
     * Largest Rectangle in Histogram Algorithm (Monotonic Stack):
     * Find the largest rectangular area in a histogram.
     * 
     * KEY INSIGHT: For each bar, find the largest rectangle with that bar as the shortest.
     * Rectangle width = (next_smaller_index - prev_smaller_index - 1)
     * Rectangle area = height[i] × width
     * 
     * APPROACH 1 - Two Pass:
     * 1. Find previous smaller element index for each bar
     * 2. Find next smaller element index for each bar  
     * 3. Calculate area for each bar and track maximum
     * 
     * APPROACH 2 - Single Pass Optimized:
     * Use monotonic increasing stack. When current bar is smaller:
     * - Pop taller bars and calculate their areas
     * - Current bar becomes their "next smaller"
     * - Top of stack becomes their "previous smaller"
     * 
     * Time: O(n), Space: O(n) for stack
     */
    // Next smaller Element (index)
    private static int[] nextSmaller(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return ans;
    }

    // Prev Smaller Element (index)
    private static int[] prevSmaller(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return ans;
    }

    private static int optimizedFunction(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;

        int max = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int nse = i;
                stack.pop();
                int pse = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(heights[i] * (pse - nse - 1), max);
            }
            stack.push(i);
        }
        return max;
    }


    public static int largestRectangleArea(int[] heights) {
        int[] pse = prevSmaller(heights);
        int[] nse = nextSmaller(heights);

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int curr = heights[i] * (nse[i] - pse[i] - 1);
            max = Math.max(curr, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(optimizedFunction(heights));
    }
}
