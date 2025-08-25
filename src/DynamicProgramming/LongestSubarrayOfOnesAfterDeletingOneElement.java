package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayOfOnesAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;

        int left = 0;
        int[] frq = new int[2];

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) frq[0]++;

            while (frq[0] > 1) {
                if (nums[left] == 0) frq[0]--;
                left++;
            }

            max = Math.max(right - left, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(new LongestSubarrayOfOnesAfterDeletingOneElement().longestSubarray(arr));
    }
}
