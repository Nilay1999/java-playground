package Array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (right - left >= k) {
            if (Math.abs(x - arr[left]) <= Math.abs(x - arr[right])) {
                right--;
            } else {
                left++;
            }
        }

        return Arrays.stream(arr, left, right + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int k = 4, x = 3;
        System.out.println(new FindKClosestElements().findClosestElements(arr, k, x));
    }
}
