package Array;

public class KthFactorOfN {
    /**
     * Kth Factor Finding Algorithm:
     * Brute force approach - check all numbers from 1 to n for divisibility.
     * Count factors and return the kth one.
     * 
     * Optimization possible: Check only up to √n and collect factors,
     * then combine with their complements for O(√n) solution.
     * 
     * Example: n=12, k=3
     * Factors: 1, 2, 3, 4, 6, 12 → 3rd factor is 3
     * 
     * Time: O(n), Space: O(1)
     */
    public int kthFactor(int n, int k) {
        int counter = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                counter++;
                if (counter == k) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new KthFactorOfN().kthFactor(12, 3));
        System.out.println(new KthFactorOfN().kthFactor(7, 2));
        System.out.println(new KthFactorOfN().kthFactor(67, 34));
        System.out.println(new KthFactorOfN().kthFactor(4, 4));
    }
}
