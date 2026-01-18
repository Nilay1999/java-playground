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
    // Find next smaller element index for each bar (right to left)
    private static int[] nextSmaller(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] ans = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
            // Pop taller or equal bars
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If stack empty, no smaller element on right (use n as boundary)
            ans[i] = stack.isEmpty() ? n : stack.peek();
            // Push current index
            stack.push(i);
        }
        return ans;
    }

    // Find previous smaller element index for each bar (left to right)
    private static int[] prevSmaller(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            // Pop taller or equal bars
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If stack empty, no smaller element on left (use -1 as boundary)
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            // Push current index
            stack.push(i);
        }
        return ans;
    }

    // Optimized single-pass approach
    private static int optimizedFunction(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            // When current bar is shorter, calculate area for taller bars
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int nse = i; // current index is next smaller
                int height = heights[stack.pop()]; // height of popped bar
                int pse = stack.isEmpty() ? -1 : stack.peek(); // previous smaller
                
                // Calculate area: height × width
                int width = nse - pse - 1;
                max = Math.max(height * width, max);
            }
            stack.push(i);
        }
        
        // Process remaining bars in stack
        while (!stack.isEmpty()) {
            int nse = n; // no smaller element on right
            int height = heights[stack.pop()];
            int pse = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(height * (nse - pse - 1), max);
        }
        
        return max;
    }

    // Two-pass approach using helper arrays
    public static int largestRectangleArea(int[] heights) {
        int[] pse = prevSmaller(heights); // previous smaller indices
        int[] nse = nextSmaller(heights);  // next smaller indices

        int max = 0;
        // For each bar, calculate maximum area with that bar as shortest
        for (int i = 0; i < heights.length; i++) {
            int width = nse[i] - pse[i] - 1; // width of rectangle
            int curr = heights[i] * width;    // area
            max = Math.max(curr, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(optimizedFunction(heights));
    }
}
