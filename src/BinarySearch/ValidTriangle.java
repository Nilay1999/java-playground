package BinarySearch;

import java.util.Arrays;

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
