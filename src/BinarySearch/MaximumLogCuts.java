package BinarySearch;

/**
 * Q1. There are N poles of various heights, and you have a machine whose saw
 * blade can be set at a specific height "h"
 * and it cuts all poles till that height, such that all of them have height "h"
 * after the cut.
 * (Poles with height less than "h" remain uncut). You take away the cut
 * portions of all poles with you.
 * 
 * Your task is to take at least M length of poles with you in total after the
 * cut.
 * What is the maximum height 'h' where you can set your blade to achieve this.
 * 
 * N = 4
 * M = 7
 * arr = [20, 15, 10, 17]
 */

public class MaximumLogCuts {
    public int findHeight(int[] arr, int h) {
        int n = arr.length;
        // 4, 42, 40, 26, 46
        int left = 0;
        int right = 0;
        for (int i : arr) {
            if (i > right) {
                right = i;
            }
        }
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int total = 0;
            for (int i = 0; i < n; i++) {
                total += mid < arr[i] ? arr[i] - mid : 0;
            }
            if (total >= h) {
                ans = mid;
                left = mid + 1;
            } else if (total < h) {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 20, 15, 10, 17 };
        int h = 7;
        System.out.println(new MaximumLogCuts().findHeight(arr, h));
    }
}
