package Greedy;

import java.util.Arrays;

/**
 * Divide Players Into Teams of Equal Skill Algorithm (Greedy + Two Pointers):
 * Pair players such that each team has equal total skill. Return sum of
 * products.
 * 
 * KEY INSIGHT: After sorting, optimal pairing is smallest with largest.
 * This ensures all pairs have the same sum if a valid solution exists.
 * 
 * GREEDY STRATEGY:
 * 1. Sort the skill array
 * 2. Pair players from both ends (two pointers)
 * 3. Check if all pairs have same skill sum
 * 4. If yes, return sum of products; if no, return -1
 * 
 * ALGORITHM:
 * 1. Sort skill array
 * 2. Calculate expected pair sum = skill[0] + skill[n-1]
 * 3. Use two pointers (left=0, right=n-1)
 * 4. For each pair:
 * - Check if sum equals expected sum
 * - Add product to result
 * - Move pointers inward
 * 5. Return result or -1 if invalid
 * 
 * Example: [1,1,2,3] → sorted: [1,1,2,3]
 * Pairs: (1,3)=4, (1,2)=3 → Different sums → return -1
 * 
 * Example: [3,2,5,1,3,4] → sorted: [1,2,3,3,4,5]
 * Pairs: (1,5)=6, (2,4)=6, (3,3)=6 → All equal → return 1×5 + 2×4 + 3×3 = 22
 * 
 * Time: O(n log n), Space: O(1)
 */
public class DividePlayersIntoTeamsOfEqualSkill {
    public static void main(String[] args) {
        int[] skill = { 1, 1, 2, 3 };
        // 1 2 3 3 4 5
        System.out.println(new DividePlayersIntoTeamsOfEqualSkill().dividePlayers(skill));
    }

    public long dividePlayers(int[] skill) {
        int n = skill.length;
        if (n == 2) {
            return (long) skill[0] * skill[1];
        }
        Arrays.sort(skill);

        int left = 0;
        int right = n - 1;

        long skillSum = 0;
        int lastPairSum = skill[0] + skill[n - 1];
        while (left < right) {
            int power = skill[left] + skill[right];
            long product = (long) skill[left] * skill[right];
            if (lastPairSum != power) {
                return -1;
            }
            skillSum += product;
            left++;
            right--;
        }

        return skillSum;
    }
}
