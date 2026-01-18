package Stack;

import java.util.*;

/**
 * Sliding Window Maximum Algorithm (Monotonic Deque):
 * Find maximum element in each sliding window of size k.
 * 
 * THREE APPROACHES:
 * 
 * 1. BRUTE FORCE O(n*k):
 *    - For each window, find maximum by scanning all k elements
 * 
 * 2. MAX HEAP O(n log n):
 *    - Add elements to max heap
 *    - Remove elements outside window
 *    - Peek gives maximum
 * 
 * 3. MONOTONIC DEQUE O(n) - OPTIMAL:
 *    - Maintain deque of indices in decreasing order of values
 *    - Remove indices outside window from front
 *    - Remove smaller elements from back before adding new element
 *    - Front of deque always has maximum index
 * 
 * DEQUE ALGORITHM:
 * 1. For each element at index i:
 *    - Remove indices outside window (i - k)
 *    - Remove indices with smaller values from back
 *    - Add current index to back
 *    - If window complete (i >= k-1): add front element to result
 * 
 * WHY DEQUE WORKS:
 * - Stores indices in decreasing order of values
 * - Front always has maximum
 * - Removing smaller elements maintains order
 * 
 * Example: [1,3,-1,-3,5,3,6,7], k=3
 * 
 * i=0 (1): deque=[0]
 * i=1 (3): remove 0 (3>1), deque=[1]
 * i=2 (-1): deque=[1,2], window complete → max=3
 * i=3 (-3): deque=[1,2,3], window complete → max=3
 * i=4 (5): remove 1,2,3 (5>all), deque=[4], window complete → max=5
 * i=5 (3): deque=[4,5], window complete → max=5
 * i=6 (6): remove 4,5 (6>all), deque=[6], window complete → max=6
 * i=7 (7): remove 6 (7>6), deque=[7], window complete → max=7
 * 
 * Result: [3,3,5,5,6,7]
 * 
 * Time: O(n), Space: O(k)
 */
public class SlidingWindowMaximum {

    // Approach 1: Brute Force - O(n*k)
    public static int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }

        int numOfWindow = n - k + 1; // number of windows
        int[] result = new int[numOfWindow];

        // For each window, find maximum by scanning all k elements
        for (int start = 0; start < numOfWindow; ++start) {
            int end = start + k - 1;
            int maxVal = nums[start];
            // Find max in current window
            for (int i = start + 1; i <= end; ++i) {
                if (nums[i] > maxVal) {
                    maxVal = nums[i];
                }
            }
            result[start] = maxVal;
        }

        return result;
    }

    // Approach 2: Max Heap - O(n log n)
    public static int[] usingHeap(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int n = nums.length;
        List<Integer> res = new ArrayList<>();

        // Max heap to track maximum in window
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; ++i) {
            int start = i - k;
            // Remove element outside window
            if (start >= 0) {
                maxPQ.remove(nums[start]);
            }
            // Add current element
            maxPQ.offer(nums[i]);
            // If window complete, add maximum to result
            if (maxPQ.size() == k) {
                res.add(maxPQ.peek());
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    // Approach 3: Monotonic Deque - O(n) OPTIMAL
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> win = new ArrayDeque<>(); // stores indices
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside current window from front
            while (!win.isEmpty() && win.peekFirst() <= i - k) {
                win.pollFirst();
            }

            // Remove indices with smaller values from back
            // Maintains decreasing order of values
            while (!win.isEmpty() && nums[win.peekLast()] < nums[i]) {
                win.pollLast();
            }

            // Add current index to back
            win.offerLast(i);
            
            // If window complete, add maximum (front of deque) to result
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
