package BinarySearch;

import java.util.Arrays;

/**
 * Koko Eating Bananas Algorithm:
 * Find minimum eating speed k such that Koko can eat all bananas within h hours.
 * 
 * BINARY SEARCH ON ANSWER:
 * 1. Search space: [1, max(piles)] - minimum speed is 1, maximum needed is max pile
 * 2. For each speed k, calculate total hours = sum(ceil(pile[i]/k))
 * 3. If total_hours <= h: try slower speed (search left half)
 * 4. If total_hours > h: need faster speed (search right half)
 * 
 * KEY INSIGHT: As eating speed increases, total hours decreases (monotonic)
 * We want the minimum speed that satisfies the constraint.
 * 
 * Example: piles=[3,6,7,11], h=8
 * k=4: ceil(3/4)+ceil(6/4)+ceil(7/4)+ceil(11/4) = 1+2+2+3 = 8 <= 8 ✓
 * k=3: ceil(3/3)+ceil(6/3)+ceil(7/3)+ceil(11/3) = 1+2+3+4 = 10 > 8 ✗
 * Answer: 4
 * 
 * Time: O(n * log(max)), Space: O(1)
 */
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        // Binary search on eating speed
        int left = 1; // minimum possible speed
        int right = Arrays.stream(piles).max().getAsInt(); // maximum needed speed
        int ans = right;
        
        while (left <= right) {
            double hours = 0;
            int mid = left + (right - left) / 2; // current speed to test

            // Calculate total hours needed at speed mid
            for (int pile : piles) {
                // Each pile takes ceil(pile/speed) hours
                hours += Math.ceil((double)pile / mid);
            }

            // If we can finish in time, try slower speed
            if (hours <= h) {
                ans = mid; // update answer
                right = mid - 1; // search for slower speed
            } else {
                // Too slow, need faster speed
                left = mid + 1;
            }
        }

        return ans;
    }
}
