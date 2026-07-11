package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinGroup {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (!minHeap.isEmpty() && minHeap.peek() < start) {
                minHeap.poll();
            }

            minHeap.offer(end);
        }

        return minHeap.size();
    }

    public int minGroupbruteForce(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Integer> groupEnds = new ArrayList<>(); // end time of each group's last interval

        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            int reuseIdx = -1;

            for (int i = 0; i < groupEnds.size(); i++) {
                if (groupEnds.get(i) < start) {
                    reuseIdx = i;
                    break; // just need *any* free group, not necessarily earliest
                }
            }

            if (reuseIdx != -1) {
                groupEnds.set(reuseIdx, end);
            } else {
                groupEnds.add(end);
            }
        }

        return groupEnds.size();
    }
}
