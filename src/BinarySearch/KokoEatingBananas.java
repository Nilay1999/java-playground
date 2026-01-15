package BinarySearch;

import java.util.Arrays;

public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        int ans = right;
        while (left <= right) {
            double hours = 0;
            int mid = left + (right - left) / 2;

            for (int pile : piles) {
                hours += Math.ceil((double)pile / mid);
            }

            if (hours <= h) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }
}
