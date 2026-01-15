package company.Amazon;

public class FindFirstLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        // First element index = we will just go left as soon as we found our target
        // Last element index = we will just go right as soon as we found our target
        result[0] = findOccurrence(nums, target, true);
        if (result[0] == -1) {
            return result;
        }
        result[1] = findOccurrence(nums, target, false);
        return result;
    }

    public int findOccurrence(int[] nums, int target, boolean first) {
        int idx = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                idx = mid;
                if (first) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(new FindFirstLastPositionOfElementInSortedArray().searchRange(nums, 8));
    }
}
