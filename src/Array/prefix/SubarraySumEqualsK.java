package Array.prefix;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int subarray = 0;
        int sum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer current = map.get(sum - k);
            if (current != null) {
                subarray += current;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return subarray;
    }

    public static void main(String[] args) {
        int[] nums = { 1, -1, 0 };
        int k = 3;

        System.out.println(new SubarraySumEqualsK().subarraySum(nums, k));
    }
}
