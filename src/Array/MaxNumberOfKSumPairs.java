package Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sub = k - nums[i];
            if (map.containsKey(sub) && map.get(sub) > 0) {
                ans++;
                map.put(sub, map.get(sub) - 1);
            } else {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
        }
        return ans;
    }

    public int twoPointer(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 0, left = 0, right = nums.length - 1;

        while (left < right) {
            if (nums[left] + nums[right] == k) {
                left++;
                right--;
                ans++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9 };
        int k = 4;

        System.out.println(new MaxNumberOfKSumPairs().maxOperations(arr, k));
    }
}

/*
 * 4
 * 
 * 1, 1, 1, 2, 2, 2, 3, 4
 * 
 * 1:1
 * 1:2
 * 1:3
 * 1:3 2:1
 * 1:3 2:0
 */