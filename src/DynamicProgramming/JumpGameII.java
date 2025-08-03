package DynamicProgramming;

public class JumpGameII {
    // Greedy approach
    public static int jump(int[] nums) {
        int jumps = 0;
        int far = 0;
        int next = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            far = Math.max(far, nums[i] + i);

            if (far >= n - 1) {
                jumps++;
                return jumps;
            }

            if (i == next) {
                next = far;
                jumps++;
            }
        }

        return jumps;
    }

    // Recursive approach (inefficient)
    public static int jumpRecursive(int[] nums) {
        int n = nums.length;

        return recursion(0, 0, nums, n);
    }

    private static int recursion(int idx, int jumps, int[] nums, int n) {
        if (idx >= n - 1) {
            return jumps;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= nums[idx]; i++) {
            int res = recursion(idx + i, jumps + 1, nums, n);
            min = Math.min(min, res);
        }

        return min;
    }

    private static boolean canJump(int[] nums) {
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println("Greedy: " + jump(nums));           // Output: 2
        System.out.println("Recursive: " + jumpRecursive(nums)); // Output: 2 (but slow for large inp

    }
}