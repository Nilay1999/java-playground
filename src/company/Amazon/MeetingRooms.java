package company.Amazon;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {
    public int countRooms(int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(meetings[0][1]);

        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            while (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll();
            }

            minHeap.offer(end);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRooms().countRooms(new int[][] {
                { 0, 30 },
                { 5, 10 },
                { 15, 20 },
                { 15, 45 }
        }));
    }
}
