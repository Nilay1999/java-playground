package DynamicProgramming;

import java.util.Arrays;

/**
 * Counting Bits Algorithm (DP with Bit Manipulation):
 * For each number from 0 to n, count the number of 1's in its binary representation.
 * 
 * KEY INSIGHT: Use previously computed results to avoid recounting bits.
 * 
 * PATTERN OBSERVATION:
 * - Even numbers: Same bit count as number/2 (right shift removes trailing 0)
 * - Odd numbers: One more bit than previous even number (trailing 1 added)
 * 
 * ALGORITHM:
 * 1. Initialize result array with res[0] = 0
 * 2. For each number i from 1 to n:
 *    - If i is even: res[i] = res[i/2]
 *    - If i is odd: res[i] = res[i-1] + 1
 * 
 * WHY THIS WORKS:
 * - Even number: Binary ends with 0, removing it gives i/2
 * - Odd number: Binary ends with 1, previous number (i-1) is even
 * 
 * Example: n=5
 * 0: 000 → 0 bits
 * 1: 001 → 1 bit (0 + 1)
 * 2: 010 → 1 bit (same as 1)  
 * 3: 011 → 2 bits (2 + 1)
 * 4: 100 → 1 bit (same as 2)
 * 5: 101 → 2 bits (4 + 1)
 * 
 * Alternative: res[i] = res[i & (i-1)] + 1 (Brian Kernighan's algorithm)
 * 
 * Time: O(n), Space: O(n)
 */
public class CountingBits {

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                res[i] = res[i / 2];
            } else {
                res[i] = res[i - 1] + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] result = countBits(n);
        System.out.println("Counting Bits from 0 to " + n + ": " + Arrays.toString(result));
    }
}

