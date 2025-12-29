package Arrays.easy;

import java.util.HashSet;
import java.util.Set;

public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int expectedTotal = n * (n + 1) / 2;

        int currentTotal = 0;
        int duplicate = -1;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            currentTotal += num;
            if (!set.add(num)) {
                duplicate = num;
            }
        }

        int missing = expectedTotal - (currentTotal - duplicate);
        return new int[] { duplicate, missing };
    }
}
