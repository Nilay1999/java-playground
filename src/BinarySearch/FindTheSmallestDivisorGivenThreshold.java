package BinarySearch;

/**
 * Find Smallest Divisor Given Threshold Algorithm:
 * Find the smallest divisor such that sum of ceil(nums[i]/divisor) <= threshold
 * 
 * BINARY SEARCH APPROACH:
 * 1. Search space: [1, max(nums)] - divisor must be at least 1
 * 2. For each divisor, calculate sum of ceil(nums[i]/divisor)
 * 3. If sum <= threshold: try smaller divisor (search left half)
 * 4. If sum > threshold: need larger divisor (search right half)
 * 
 * KEY INSIGHT: As divisor increases, sum decreases (monotonic property)
 * This allows binary search to find the boundary efficiently.
 * 
 * Example: nums=[1,2,5,9], threshold=6
 * divisor=5: ceil(1/5)+ceil(2/5)+ceil(5/5)+ceil(9/5) = 1+1+1+2 = 5 <= 6 ✓
 * divisor=4: ceil(1/4)+ceil(2/4)+ceil(5/4)+ceil(9/4) = 1+1+2+3 = 7 > 6 ✗
 * Answer: 5
 * 
 * Time: O(n * log(max)), Space: O(1)
 */
public class FindTheSmallestDivisorGivenThreshold {
    // Helper: Find maximum element in array
    private int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    // Helper: Calculate sum of ceil(nums[i]/divisor) for all elements
    private int getSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            // Add ceiling of division
            sum += (int) Math.ceil(((double) num / (double) divisor));
        }
        return sum;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        // Binary search on divisor value
        int left = 1;              // minimum possible divisor
        int right = findMax(nums); // maximum needed divisor

        while (left <= right) {
            int mid = left + (right - left) / 2; // current divisor to test
            int sum = getSum(nums, mid);         // calculate result sum

            // If sum is within threshold, try smaller divisor
            if (sum <= threshold) {
                right = mid - 1; // search left for smaller divisor
            } else {
                // Sum too large, need larger divisor
                left = mid + 1;
            }
        }

        // left is the smallest divisor that satisfies the constraint
        return left;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 5, 9 };
        int threshold = 6;

        System.out.println(new FindTheSmallestDivisorGivenThreshold().smallestDivisor(nums, threshold));
    }
}
