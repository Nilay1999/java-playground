package company.Amazon;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
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
