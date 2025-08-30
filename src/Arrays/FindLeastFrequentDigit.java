package Arrays;

import java.util.HashMap;
import java.util.Map;

public class FindLeastFrequentDigit {
    public int getLeastFrequentDigit(int n) {
        Map<Integer, Integer> frq = new HashMap<>();
        while (n != 0) {
            int digit = n % 10;
            frq.put(digit, frq.getOrDefault(digit, 0) + 1);
            n = n / 10;
        }

        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : frq.entrySet()) {
            if (entry.getValue() < min) {
                ans = entry.getKey();
                min = entry.getValue();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 1553322;
        System.out.println(new FindLeastFrequentDigit().getLeastFrequentDigit(n));
    }
}
