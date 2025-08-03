package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class FindSumPairs {
    private final int[] nums1;
    private final int[] nums2;
    private Map<Integer, Integer> map;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.map = new HashMap<>();

        for (int num : nums2) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int oldValue = nums2[index];
        int newValue = oldValue + val;

        // Decrease frequency of old value
        map.put(oldValue, map.get(oldValue) - 1);

        // Update nums2
        nums2[index] = newValue;

        // Increase frequency of new value
        map.put(newValue, map.getOrDefault(newValue, 0) + 1);
    }

    public int count(int total) {
        int count = 0;

        for (int num : nums1) {
            int complement = total - num;
            count += map.getOrDefault(complement, 0);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] num1 = {1, 1, 2, 2, 2, 3};
        int[] num2 = {1, 4, 5, 2, 5, 4};

        FindSumPairs prob = new FindSumPairs(num1, num2);

        System.out.println(prob.count(7)); // ➜ 6
        prob.add(3, 2);                    // nums2[3] becomes 4
        System.out.println(prob.count(8)); // ➜ 2
        System.out.println(prob.count(4)); // ➜ 1
        prob.add(0, 1);                    // nums2[0] becomes 2
        System.out.println(prob.count(7)); // ➜ 2
    }
}
