package Array;

public class MaxConsecutiveOnes {
    public int longestOnes(int[] nums, int k) {
        int ans = 0, zero = 0, left = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zero++;

            while (zero > k) {
                if (nums[left] == 0) {
                    zero--;
                }
                left++;
            }

            ans = Math.max(ans, i - left + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1 };
        int k = 2;
        System.out.println(new MaxConsecutiveOnes().longestOnes(nums, k));
    }
}
