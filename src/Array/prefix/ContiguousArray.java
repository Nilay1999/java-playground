package Array.prefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int max = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum += -1;
            } else {
                sum += 1;
            }
            if (map.get(sum) != null) {
                max = Math.max(max, i - map.get(sum));
            }
            map.put(sum, i);
            System.out.println(map);
            System.out.println(sum);
        }

        return max;
    }

    public static void main(String[] args) {
        int nums[] = { 0, 1, 1, 1, 1, 0, 0, 0, 1 };
        System.out.println(new ContiguousArray().findMaxLength(nums));
    }
}
