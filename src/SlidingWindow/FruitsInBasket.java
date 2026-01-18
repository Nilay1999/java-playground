package SlidingWindow;

import java.util.*;

/**
 * Fruits in Basket Algorithm (Sliding Window):
 * Find maximum fruits you can collect with 2 baskets (each basket holds one fruit type).
 * 
 * PROBLEM TRANSLATION: Find longest subarray with at most 2 distinct elements.
 * 
 * SLIDING WINDOW APPROACH:
 * 1. Use HashMap to track fruit types and their counts in current window
 * 2. Expand window by moving right pointer
 * 3. If more than 2 fruit types, shrink window from left
 * 4. Track maximum window size seen so far
 * 
 * ALGORITHM:
 * - right pointer: adds fruits to window
 * - left pointer: removes fruits when constraint violated
 * - map.size() > 2: too many fruit types, shrink window
 * - Update max length after each valid window
 * 
 * Example: [3,3,3,1,2,1,1,2,3,3,4]
 * Window [1,2,1,1,2] has 2 types, length = 5
 * 
 * Time: O(n), Space: O(1) - at most 3 entries in map
 */
public class FruitsInBasket {
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < n; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) map.remove(fruits[left]);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] trees = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.printf("Answer: %d", totalFruit(trees));
    }
}
