package SlidingWindow;

import java.util.ArrayList;
import java.util.List;

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
