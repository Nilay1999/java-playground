package BinarySearch;

/**
 * Peak Element Finding Algorithm (Binary Search):
 * Find any peak element in an array where nums[i] > nums[i-1] and nums[i] > nums[i+1].
 * 
 * PROBLEM DESCRIPTION:
 * - Array may have multiple peaks, return index of any one
 * - nums[-1] and nums[n] are considered -∞ (edges are always lower)
 * - Adjacent elements are never equal
 * 
 * ALGORITHM APPROACH (Binary Search):
 * 1. Compare mid element with its right neighbor
 * 2. If nums[mid] < nums[mid+1]: peak must be on right side (ascending slope)
 * 3. If nums[mid] > nums[mid+1]: peak is mid or on left side (descending slope)
 * 4. Converge to a peak element
 * 
 * KEY INSIGHTS:
 * - If we're on ascending slope, peak must be ahead
 * - If we're on descending slope, we might be at peak or it's behind us
 * - Binary search works because we always move toward higher ground
 * - Guaranteed to find a peak due to boundary conditions
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: nums = [1, 2, 3, 1]
 * 
 * Iteration 1: left=0, right=3, mid=1
 *   nums[1]=2 < nums[2]=3 → ascending, peak on right
 *   left = 2
 * 
 * Iteration 2: left=2, right=3, mid=2
 *   nums[2]=3 > nums[3]=1 → descending, peak at or left
 *   right = 2
 * 
 * Loop ends: left=right=2, return 2 (peak element is 3)
 * 
 * Time: O(log n), Space: O(1)
 */
public class PeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            // Compare mid with right neighbor
            if (nums[mid] < nums[mid + 1]) {
                // Ascending slope: peak must be on right side
                left = mid + 1;
            } else {
                // Descending slope: mid could be peak or peak is on left
                right = mid;
            }
        }

        // left == right, found a peak
        return left;
    }
}
