package General;

import java.util.*;

public class RangeProductQueriesOfPowers {
    public static int[] productQueries(int n, int[][] queries) {
        final int MOD = 1000000007;
        List<Integer> powers = new ArrayList<>();
        int power = 0;
        while (n > 0) {
            if ((n & 1) == 1) {  // If current bit is set
                powers.add(1 << power);  // Add 2^power
            }
            n >>= 1;  // Right shift
            power++;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            long product = 1;
            for (int j = x; j <= y; j++) {
                product = (product * powers.get(j)) % MOD;
            }
            ans[i] = (int) product;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 15;
        int[][] queries = {{0, 1}, {2, 2}, {0, 3}};
        System.out.println(Arrays.toString(productQueries(n, queries)));
    }
}
