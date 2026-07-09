package Array;

import java.util.Arrays;
import java.util.Random;

public class WiggleSort {
    // Simpler O(n log n) time, O(n) space solution: sort, then interleave
    // low/high halves into a temp array.
    public void wiggleSortWithExtraSpace(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] temp = new int[n];
        int mid = (n - 1) / 2;
        int end = n - 1;

        for (int i = 0; i < n; i++) {
            temp[i] = (i % 2 == 0) ? nums[mid--] : nums[end--];
        }

        System.arraycopy(temp, 0, nums, 0, n);
    }

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int median = findKthLargest(nums, (n + 1) / 2);

        int left = 0, i = 0, right = n - 1;
        while (i <= right) {
            int idx = mapIndex(i, n);
            if (nums[idx] > median) {
                swap(nums, mapIndex(left, n), idx);
                left++;
                i++;
            } else if (nums[idx] < median) {
                swap(nums, idx, mapIndex(right, n));
                right--;
            } else {
                i++;
            }
        }
    }

    // Maps a "virtual" wiggle-order index back to a real array index,
    // so the 3-way partition below can rearrange in place with no aux array.
    private int mapIndex(int idx, int n) {
        return (2 * idx + 1) % (n | 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // Quickselect: finds the k-th largest value in-place, O(1) extra space.
    private int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        Random random = new Random();
        while (true) {
            int pivotIndex = left + random.nextInt(right - left + 1);
            pivotIndex = partition(nums, left, right, pivotIndex);
            if (pivotIndex == nums.length - k) {
                return nums[pivotIndex];
            } else if (pivotIndex < nums.length - k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    public static void main(String[] args) {
        WiggleSort solution = new WiggleSort();

        int[] nums = { 1, 5, 1, 1, 6, 4 };
        solution.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = { 1, 5, 1, 1, 6, 4 };
        solution.wiggleSortWithExtraSpace(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
