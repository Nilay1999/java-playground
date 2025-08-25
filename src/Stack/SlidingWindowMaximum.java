package Stack;

import java.util.*;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }

        int numOfWindow = n - k + 1;
        int[] result = new int[numOfWindow];

        for (int start = 0; start < numOfWindow; ++start) {
            int end = start + k - 1;
            int maxVal = nums[start];
            for (int i = start + 1; i <= end; ++i) {
                if (nums[i] > maxVal) {
                    maxVal = nums[i];
                }
            }
            result[start] = maxVal;
        }

        return result;
    }

    public static int[] usingHeap(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int n = nums.length;
        List<Integer> res = new ArrayList<>();

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; ++i) {
            int start = i - k;
            if (start >= 0) {
                maxPQ.remove(nums[start]);
            }
            maxPQ.offer(nums[i]);
            if (maxPQ.size() == k) {
                res.add(maxPQ.peek());
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> win = new ArrayDeque<>();
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (!win.isEmpty() && win.peekFirst() <= i - k) {
                win.pollFirst();
            }

            while (!win.isEmpty() && nums[win.peekLast()] < nums[i]) {
                win.pollLast();
            }

            win.offerLast(i);
            if (i >= k - 1 && win.peekFirst() != null) {
                result.add(nums[win.peekFirst()]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(arr, k)));
    }
}
