package Array;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public List<Integer> majorityElement(int[] nums) {
        int firstCandidate = 0, secondCandidate = 1;
        int firstCount = 0, secondCount = 0;

        for (int num : nums) {
            if (firstCount > 0 && num == firstCandidate) {
                firstCount++;
            } else if (secondCount > 0 && num == secondCandidate) {
                secondCount++;
            } else if (firstCount == 0) {
                firstCandidate = num;
                firstCount = 1;
            } else if (secondCount == 0) {
                secondCandidate = num;
                secondCount = 1;
            } else {
                firstCount--;
                secondCount--;
            }
        }

        firstCount = 0;
        secondCount = 0;
        for (int num : nums) {
            if (num == firstCandidate) firstCount++;
            else if (num == secondCandidate) secondCount++;
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3;
        if (firstCount > threshold) result.add(firstCandidate);
        if (secondCount > threshold) result.add(secondCandidate);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 3, 4, 3 };
        System.out.println(new MajorityElementII().majorityElement(arr));
    }
}
