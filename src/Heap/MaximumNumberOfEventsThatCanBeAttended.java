package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumNumberOfEventsThatCanBeAttended {
    /**
     * Greedy algorithm using min-heap to maximize events attended:
     * 1. Sort events by start day (if same start, sort by end day)
     * 2. For each day, add all events starting that day to heap (by end day)
     * 3. Remove expired events (end day < current day) from heap
     * 4. Attend the event ending earliest (greedy choice) - poll from heap
     * 5. Continue until no more events or heap is empty
     * 
     * Time: O(n log n), Space: O(n)
     */
    public int maxEvents(int[][] events) {
        // Min-heap to store end days of available events
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        // Sort by start day, then by end day if start days are equal
        Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        int n = events.length;
        int ans = 0;
        int day = 1;
        int i = 0;
        
        // Continue while there are events to process or events in heap
        while (i < n || !heap.isEmpty()) {
            // Add all events starting on current day to heap
            while (i < n && day == events[i][0]) {
                heap.offer(events[i][1]); // Add end day to heap
                i++;
            }

            // Remove expired events (events that ended before current day)
            while (!heap.isEmpty() && heap.peek() < day) {
                heap.poll();
            }

            // Attend the event ending earliest (greedy choice)
            if (!heap.isEmpty()) {
                heap.poll(); // Remove the attended event
                ans++;
            }
            day++; // Move to next day
        }
        return ans;
    }
}
