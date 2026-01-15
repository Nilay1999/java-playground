import java.util.*;

public class RearrangingFruits {
    public static long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> count1 = new HashMap<>();
        Map<Integer, Integer> count2 = new HashMap<>();

        long minVal = Long.MAX_VALUE;

        for (int num : basket1) {
            count1.put(num, count1.getOrDefault(num, 0) + 1);
            count.put(num, count.getOrDefault(num, 0) + 1);
            minVal = Math.min(minVal, num);
        }

        for (int num : basket2) {
            count2.put(num, count2.getOrDefault(num, 0) + 1);
            count.put(num, count.getOrDefault(num, 0) + 1);
            minVal = Math.min(minVal, num);
        }

        for (int value : count.values()) {
            if (value % 2 != 0)
                return -1;
        }

        List<Integer> extra1 = new ArrayList<>();
        List<Integer> extra2 = new ArrayList<>();

        for (int key : count.keySet()) {
            int c1 = count1.getOrDefault(key, 0);
            int c2 = count2.getOrDefault(key, 0);
            int diff = c1 - c2;

            if (diff > 0) {
                for (int i = 0; i < diff / 2; i++)
                    extra1.add(key);
            } else if (diff < 0) {
                for (int i = 0; i < -diff / 2; i++)
                    extra2.add(key);
            }
        }

        Collections.sort(extra1);
        extra2.sort(Collections.reverseOrder());

        long cost = 0;
        for (int i = 0; i < extra1.size(); i++) {
            int a = extra1.get(i);
            int b = extra2.get(i);
            cost += Math.min(Math.min(a, b), 2 * minVal);
        }

        return cost;
    }

    public static void main(String args[]) {
        int[] basket1 = { 84, 80, 43, 8, 80, 88, 43, 14, 100, 88 };
        int[] basket2 = { 32, 32, 42, 68, 68, 100, 42, 84, 14, 8 };

        System.out.printf("Answer: %d", minCost(basket1, basket2));
    }
}
