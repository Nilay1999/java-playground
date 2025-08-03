package DynamicProgramming;

import java.util.Arrays;

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

