package Array;

import java.util.HashMap;
import java.util.Map;

public class CountGoodMeals {
    public int countPairs(int[] deliciousness) {
        final int MOD = 1_000_000_007;

        Map<Integer, Integer> freq = new HashMap<>();
        long ans = 0;

        for (int num : deliciousness) {
            int power = 1;
            for (int p = 0; p <= 21; p++) {
                if (freq.containsKey(power - num)) {
                    ans += freq.get(power - num);
                    ans %= MOD;
                }
                power *= 2;
            }

            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9 };
        System.out.println(new CountGoodMeals().countPairs(arr));
    }
}
