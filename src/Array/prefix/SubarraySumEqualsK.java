package Array.prefix;

import java.util.HashMap;
import java.util.Map;

/**
 * Subarray Sum Equals K Algorithm (Prefix Sum with HashMap):
 * Count number of subarrays whose sum equals a target value k.
 * 
 * PROBLEM DESCRIPTION:
 * - Given array of integers and target sum k
 * - Find count of contiguous subarrays with sum equal to k
 * - Array can contain negative numbers
 * 
 * ALGORITHM APPROACH:
 * 1. Use prefix sum technique with HashMap
 * 2. For each position, calculate cumulative sum
 * 3. Check if (current_sum - k) exists in HashMap
 * 4. If yes, we found subarrays ending at current position with sum k
 * 5. Store frequency of each prefix sum for future lookups
 * 
 * KEY INSIGHTS:
 * - If prefix_sum[i] - prefix_sum[j] = k, then subarray[j+1...i] sums to k
 * - Rearranged: if current_sum - k = previous_sum, we found a valid subarray
 * - HashMap stores frequency of prefix sums (multiple subarrays can have same
 * sum)
 * - Initialize map with {0: 1} to handle subarrays starting from index 0
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: nums = [1, -1, 0], k = 0
 * 
 * Index 0: num = 1
 * sum = 1, target = 1 - 0 = 1, not in map
 * map = {0: 1, 1: 1}
 * 
 * Index 1: num = -1
 * sum = 0, target = 0 - 0 = 0, found in map! count += 1
 * map = {0: 2, 1: 1}
 * 
 * Index 2: num = 0
 * sum = 0, target = 0 - 0 = 0, found in map! count += 2
 * map = {0: 3, 1: 1}
 * 
 * Result: 3 subarrays with sum 0: [1,-1], [-1,0,1], [0]
 * 
 * Time: O(n), Space: O(n) for HashMap
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int subarray = 0; // count of subarrays with sum k
        int sum = 0; // running prefix sum

        // Initialize: prefix sum 0 occurs once (empty subarray)
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            // Update running sum
            sum += nums[i];

            // Check if (sum - k) exists in map
            // If yes, there are subarrays ending at i with sum k
            Integer current = map.get(sum - k);
            if (current != null) {
                subarray += current; // add count of all such subarrays
            }

            // Store/update frequency of current prefix sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return subarray;
    }

    public int bruteForce(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 1, -1, 0 };
        int k = 3;

        System.out.println(new SubarraySumEqualsK().subarraySum(nums, k));
    }
}
