package Array;

import java.util.HashMap;
import java.util.Map;

public class MaximumGoodSubarraySum {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // key: value of nums[l], value: min prefix sum ending just before index l
        Map<Integer, Long> firstPrefix = new HashMap<>();
        firstPrefix.put(nums[0], 0L);

        long prefix = 0;
        long max = 0;

        for (int i = 0; i < n; i++) {
            prefix += nums[i];

            if (firstPrefix.containsKey(nums[i] - k)) {
                max = Math.max(max, prefix - firstPrefix.get(nums[i] - k));
            }
            if (firstPrefix.containsKey(nums[i] + k)) {
                max = Math.max(max, prefix - firstPrefix.get(nums[i] + k));
            }

            if (i + 1 < n) {
                int key = nums[i + 1];
                if (!firstPrefix.containsKey(key)) {
                    firstPrefix.put(key, prefix);
                } else {
                    firstPrefix.put(key, Math.min(firstPrefix.get(key), prefix));
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        System.out.println(new MaximumGoodSubarraySum().maximumSubarraySum(arr, 1));
    }
}
