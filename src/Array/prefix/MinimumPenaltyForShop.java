package Array.prefix;

/**
 * Minimum Penalty For Shop Algorithm (Prefix Sum):
 * Find the best time to close the shop to minimize penalty.
 * 
 * PROBLEM DESCRIPTION:
 * - Shop receives customers represented by 'Y' (yes) and 'N' (no)
 * - Penalty for closing at hour i: (count of 'Y' after i) + (count of 'N' before i)
 * - Find hour that minimizes total penalty
 * 
 * ALGORITHM APPROACH:
 * 1. Use prefix sum to track cumulative profit/loss
 * 2. For each position, calculate gain if we close at that hour
 * 3. Gain = +1 for 'Y' (customer we serve), -1 for 'N' (customer we miss)
 * 4. Track maximum cumulative gain and its position
 * 5. Position with max gain is optimal closing time
 * 
 * KEY INSIGHTS:
 * - Penalty at hour i = (Y count after i) + (N count before i)
 * - Equivalently: total_Y - Y_before_i + N_before_i
 * - Profit at hour i = Y_before_i - N_before_i (customers served minus missed)
 * - Maximum profit position minimizes penalty
 * - Greedy approach: always pick position with highest cumulative gain
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: customers = "YYNY"
 * Index:    0  1  2  3
 * Char:     Y  Y  N  Y
 * Gain:     +1 +1 -1 +1
 * Prefix:   1  2  1  2
 * 
 * At index 0: prefix = 1, max = 1, time = 0
 * At index 1: prefix = 2, max = 2, time = 1
 * At index 2: prefix = 1, max stays 2
 * At index 3: prefix = 2, max stays 2
 * Best closing time = 1
 * 
 * Time: O(n), Space: O(1)
 */
public class MinimumPenaltyForShop {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int prefix = 0;
        int maxProfit = 0;
        int time = 0;

        for (int i = 0; i < n; i++) {
            prefix = customers.charAt(i) == 'Y' ? 1 : -1;
            if (prefix > maxProfit) {
                maxProfit = prefix;
                time = i;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumPenaltyForShop().bestClosingTime("NNNNN"));
    }
}
