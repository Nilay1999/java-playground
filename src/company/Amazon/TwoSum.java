package company.Amazon;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * Two Sum Algorithm (HashMap Approach):
     * Find two numbers in array that add up to target.
     * 
     * ALGORITHM:
     * 1. For each number, calculate complement = target - current_number
     * 2. Check if current number exists in HashMap (from previous iterations)
     * 3. If found: return indices [map.get(current), current_index]
     * 4. If not found: store complement with current index in HashMap
     * 
     * KEY INSIGHT: Store complement (not current number) to find it later
     * 
     * Example: nums=[2,7,11,15], target=9
     * i=0: complement=7, store {7:0}
     * i=1: current=7, found in map → return [0,1]
     * 
     * Time: O(n), Space: O(n) for HashMap
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(nums[i])) {
                return new int[] { map.get(nums[i]), i };
            }         
            map.put(sub, i);
        }
        return new int[] {0,0};
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        System.out.println(new TwoSum().twoSum(nums, target));
    }
}
