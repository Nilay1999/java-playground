package company.Amazon;

class MaximumFrequencySubarrayOperation {
    public int maxFrequency(int[] nums, int k) {
        int frq = 0, ans = 0;
        for (int num : nums) {
            if (num == k) {
                frq++;
            }
        }

        for (int s = 1; s <= 50; s++) {
            if (s == k)
                continue;
            int curr = 0, maxCurr = 0;
            for (int num : nums) {
                if (num == s) {
                    curr++;
                } else if (num == k) {
                    curr--;
                }
                curr = Math.max(curr, 0);
                maxCurr = Math.max(maxCurr, curr);
            }
            ans = Math.max(maxCurr, ans);
        }

        return ans + frq;
    }

    public static void main(String[] args) {
        int[] nums = { 10, 2, 3, 4, 5, 5, 4, 3, 2, 2 };
        System.out.println(new MaximumFrequencySubarrayOperation().maxFrequency(nums, 10));
    }
}