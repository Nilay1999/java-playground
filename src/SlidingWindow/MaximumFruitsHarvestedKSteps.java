package SlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Maximum Fruits Harvested in K Steps Algorithm:
 * Given fruits at positions, start position, and k steps, find maximum fruits harvestable.
 * 
 * KEY INSIGHT: Two optimal strategies exist:
 * 1. Go left d steps, return to start, then go right (k-2d) steps
 * 2. Go right d steps, return to start, then go left (k-2d) steps
 * 
 * ALGORITHM:
 * 1. Build prefix sum array for efficient range sum queries
 * 2. Try both strategies for all possible values of d (0 to k/2)
 * 3. For each strategy, calculate reachable range and sum fruits
 * 4. Use binary search to find fruit positions within reachable range
 * 5. Return maximum sum across all strategies
 * 
 * BINARY SEARCH OPTIMIZATION:
 * - findLeftBound: leftmost position >= target
 * - findRightBound: rightmost position <= target
 * - Enables O(log n) range finding instead of O(n) linear search
 * 
 * Example: fruits=[[2,8],[6,3],[8,6]], start=5, k=4
 * Strategy 1 (d=1): left 1 step to pos 4, return, right 2 steps to pos 7
 * Reachable range: [4,7] → fruits at positions 6 → sum = 3
 * 
 * Time: O(k log n), Space: O(n) for prefix sum
 */
public class MaximumFruitsHarvestedKSteps {
    public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
        // Build prefix sum array
        List<Integer> prefixSum = new ArrayList<>();
        prefixSum.add(0); // Add 0 at beginning for easier calculation

        for (int i = 0; i < fruits.length; i++) {
            prefixSum.add(prefixSum.get(i) + fruits[i][1]);
        }

        int maxFruits = 0;

        // Strategy 1: Go left first (d steps), then right (k-2d steps)
        for (int d = 0; d <= k; d++) {
            int left = startPos - d;
            int right = startPos + (k - 2 * d);

            if (k - 2 * d < 0) break; // Not enough steps

            int leftIdx = findLeftBound(fruits, left);
            int rightIdx = findRightBound(fruits, right);

            if (leftIdx <= rightIdx) {
                int sum = prefixSum.get(rightIdx + 1) - prefixSum.get(leftIdx);
                maxFruits = Math.max(maxFruits, sum);
            }
        }

        // Strategy 2: Go right first (d steps), then left (k-2d steps)
        for (int d = 0; d <= k; d++) {
            int right = startPos + d;
            int left = startPos - (k - 2 * d);

            if (k - 2 * d < 0) break; // Not enough steps

            int leftIdx = findLeftBound(fruits, left);
            int rightIdx = findRightBound(fruits, right);

            if (leftIdx <= rightIdx) {
                int sum = prefixSum.get(rightIdx + 1) - prefixSum.get(leftIdx);
                maxFruits = Math.max(maxFruits, sum);
            }
        }

        return maxFruits;
    }

    // Find leftmost index where fruits[i][0] >= target
    private static int findLeftBound(int[][] fruits, int target) {
        int left = 0, right = fruits.length - 1, result = fruits.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (fruits[mid][0] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // Find rightmost index where fruits[i][0] <= target
    private static int findRightBound(int[][] fruits, int target) {
        int left = 0, right = fruits.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (fruits[mid][0] <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] fruits = {{2, 8}, {6, 3}, {8, 6}};
        int start = 5;
        int k = 4;

        System.out.printf("Answer: %d", maxTotalFruits(fruits, start, k));
    }
}
