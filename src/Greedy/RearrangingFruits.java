package Greedy;

/**
 * Rearranging Fruits Algorithm (Greedy with Sorting):
 * Minimize cost to make two fruit baskets identical by swapping fruits.
 * 
 * PROBLEM DESCRIPTION:
 * - Two baskets with different fruits
 * - Can swap fruits between baskets (cost = min of two fruit types)
 * - Goal: make both baskets identical (same multiset of fruits)
 * - Find minimum cost to achieve this
 * 
 * ALGORITHM APPROACH:
 * 1. Check if rearrangement is possible (each fruit count must be even)
 * 2. Calculate difference for each fruit type between baskets
 * 3. Collect fruits that need to move from basket1 to basket2 (extra1)
 * 4. Collect fruits that need to move from basket2 to basket1 (extra2)
 * 5. Sort extra1 ascending, extra2 descending
 * 6. Pair them up and calculate cost: min(fruit_a, fruit_b, 2*minVal)
 * 7. Sum all costs
 * 
 * KEY INSIGHTS:
 * - Each fruit must appear even number of times total (to split equally)
 * - For each fruit type, calculate how many need to move
 * - Greedy pairing: match smallest from extra1 with largest from extra2
 * - Cost of swap: min(a, b) if direct swap, or 2*minVal if using intermediary
 * - Using minimum fruit as intermediary can be cheaper than direct swap
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: basket1 = [84,80,43,8], basket2 = [32,32,42,68]
 * 
 * Count frequencies:
 * basket1: {84:1, 80:1, 43:1, 8:1}
 * basket2: {32:2, 42:1, 68:1}
 * total: {84:1, 80:1, 43:1, 8:1, 32:2, 42:1, 68:1}
 * 
 * Check even: 84(1-odd) → return -1
 * 
 * Another example: basket1 = [1,1,2], basket2 = [2,3,3]
 * total: {1:2, 2:2, 3:2} → all even ✓
 * 
 * Differences:
 * 1: basket1 has 2, basket2 has 0 → extra1 gets 1
 * 2: basket1 has 1, basket2 has 1 → balanced
 * 3: basket1 has 0, basket2 has 2 → extra2 gets 3
 * 
 * extra1 = [1], extra2 = [3]
 * minVal = 1
 * Cost = min(1, 3, 2*1) = min(1, 3, 2) = 1
 * 
 * Time: O(n log n) for sorting
 * Space: O(n) for hash maps and lists
 */
import java.util.*;

public class RearrangingFruits {
    public static long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> count1 = new HashMap<>();
        Map<Integer, Integer> count2 = new HashMap<>();

        long minVal = Long.MAX_VALUE;

        for (int num : basket1) {
            count1.put(num, count1.getOrDefault(num, 0) + 1);
            count.put(num, count.getOrDefault(num, 0) + 1);
            minVal = Math.min(minVal, num);
        }

        for (int num : basket2) {
            count2.put(num, count2.getOrDefault(num, 0) + 1);
            count.put(num, count.getOrDefault(num, 0) + 1);
            minVal = Math.min(minVal, num);
        }

        for (int value : count.values()) {
            if (value % 2 != 0)
                return -1;
        }

        List<Integer> extra1 = new ArrayList<>();
        List<Integer> extra2 = new ArrayList<>();

        for (int key : count.keySet()) {
            int c1 = count1.getOrDefault(key, 0);
            int c2 = count2.getOrDefault(key, 0);
            int diff = c1 - c2;

            if (diff > 0) {
                for (int i = 0; i < diff / 2; i++)
                    extra1.add(key);
            } else if (diff < 0) {
                for (int i = 0; i < -diff / 2; i++)
                    extra2.add(key);
            }
        }

        Collections.sort(extra1);
        extra2.sort(Collections.reverseOrder());

        long cost = 0;
        for (int i = 0; i < extra1.size(); i++) {
            int a = extra1.get(i);
            int b = extra2.get(i);
            cost += Math.min(Math.min(a, b), 2 * minVal);
        }

        return cost;
    }

    public static void main(String args[]) {
        int[] basket1 = { 84, 80, 43, 8, 80, 88, 43, 14, 100, 88 };
        int[] basket2 = { 32, 32, 42, 68, 68, 100, 42, 84, 14, 8 };

        System.out.printf("Answer: %d", minCost(basket1, basket2));
    }
}
