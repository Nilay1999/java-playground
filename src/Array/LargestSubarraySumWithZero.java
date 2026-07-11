package Array;

import java.util.HashMap;
import java.util.Map;

public class LargestSubarraySumWithZero {
    public int maxLength(int arr[]) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefix = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            prefix += arr[i];

            if (map.containsKey(prefix)) {
                int prev = map.get(prefix);
                int len = i - prev;
                max = Math.max(max, len);
            } else {
                map.put(prefix, i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LargestSubarraySumWithZero().maxLength(new int[] { 15, -2, 2, -8, 1, 7, 10, 23 }));
    }
}
