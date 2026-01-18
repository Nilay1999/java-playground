package BinarySearch;

import java.util.Arrays;

/**
 * Valid Triangle Number Algorithm:
 * Count number of valid triangles that can be formed from array elements.
 * Triangle inequality: sum of any two sides > third side
 * 
 * TWO APPROACHES:
 * 
 * 1. BRUTE FORCE O(n³):
 *    - Try all triplets (i,j,k) where i < j < k
 *    - Check if nums[i] + nums[j] > nums[k]
 * 
 * 2. OPTIMIZED TWO-POINTER O(n²):
 *    - Sort array first
 *    - Fix largest side (nums[k]) and use two pointers for other two sides
 *    - If nums[left] + nums[right] > nums[k]: valid triangle found
 *      All pairs (left, left+1, ..., right-1) with right also form valid triangles
 *    - If sum <= nums[k]: need larger left side, so left++
 * 
 * KEY INSIGHT: After sorting, if nums[i] + nums[j] > nums[k] where i < j < k,
 * then nums[i+1] + nums[j] > nums[k], nums[i+2] + nums[j] > nums[k], etc.
 * 
 * Example: [2,2,3,4] → sorted: [2,2,3,4]
 * k=3 (nums[k]=4): left=0, right=2
 * nums[0]+nums[2] = 2+3 = 5 > 4 ✓ → count += (2-0) = 2 triangles
 * 
 * Time: O(n²), Space: O(1)
 */
public class ValidTriangle {

    public int triangleNumberBruteForce(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        total += 1;
                    }
                }
            }
        }
        return total;
    }

    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int triplets = 0;

        Arrays.sort(nums);

        for (int k = n - 1; k >= 0; k--) {
            int left = 0;
            int right = k - 1;

            while (left < right) {
                if (nums[left] + nums[right] > nums[k]) {
                    triplets += 1;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        int[] sides = {2, 2, 3, 4};
        System.out.println(new ValidTriangle().triangleNumberBruteForce(sides));
        System.out.println(new ValidTriangle().triangleNumber(sides));
    }
}
