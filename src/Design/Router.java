package Design;

import java.util.*;

/**
 * Router Packet Management System Design:
 * Manage packet queue with memory limit, forward packets, and query statistics.
 * 
 * DATA STRUCTURES:
 * 1. packetQueue: Deque<int[]>
 *    - FIFO queue for packets (source, destination, timestamp)
 * 2. uniquePackets: Set<String>
 *    - Prevent duplicate packets (key = source#destination#timestamp)
 * 3. destinationTimestamps: Map<destination, List<timestamp>>
 *    - Track all timestamps for each destination
 * 4. processedCount: Map<destination, count>
 *    - Track how many packets processed for each destination
 * 
 * ALGORITHM:
 * 
 * addPacket(source, destination, timestamp):
 * - Check if packet already exists (return false if duplicate)
 * - If queue full: forward oldest packet
 * - Add new packet to queue
 * - Record timestamp for destination
 * 
 * forwardPacket():
 * - Remove oldest packet from queue (FIFO)
 * - Remove from uniquePackets set
 * - Increment processedCount for destination
 * - Return packet details
 * 
 * getCount(destination, startTime, endTime):
 * - Get all timestamps for destination
 * - Skip already processed packets (using processedCount)
 * - Binary search for timestamps in [startTime, endTime]
 * - Return count of packets in range
 * 
 * BINARY SEARCH OPTIMIZATION:
 * - lowerBound: find first timestamp >= startTime
 * - upperBound: find first timestamp > endTime
 * - Count = upperBound - lowerBound
 * 
 * Example: memoryLimit=3
 * addPacket(1,1,1): queue=[(1,1,1)], unique={(1,1,1)}
 * addPacket(1,2,2): queue=[(1,1,1),(1,2,2)], unique={(1,1,1),(1,2,2)}
 * addPacket(1,3,3): queue=[(1,1,1),(1,2,2),(1,3,3)], unique={(1,1,1),(1,2,2),(1,3,3)}
 * addPacket(1,4,4): queue full, forward (1,1,1)
 *                   queue=[(1,2,2),(1,3,3),(1,4,4)]
 * 
 * Time: O(1) add/forward, O(log n) getCount
 * Space: O(n)
 */
class Router {
    private final int memoryLimit;
    private final Deque<int[]> packetQueue;
    private final Set<String> uniquePackets;
    private final Map<Integer, List<Integer>> destinationTimestamps;
    private final Map<Integer, Integer> processedCount;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.packetQueue = new ArrayDeque<>();
        this.uniquePackets = new HashSet<>();
        this.destinationTimestamps = new HashMap<>();
        this.processedCount = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String key = source + "#" + destination + "#" + timestamp;
        if (uniquePackets.contains(key)) return false;

        if (packetQueue.size() == memoryLimit) {
            forwardPacket();
        }

        packetQueue.offerLast(new int[]{source, destination, timestamp});
        uniquePackets.add(key);
        destinationTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

        return true;
    }

    public List<Integer> forwardPacket() {
        if (packetQueue.isEmpty()) return Collections.emptyList();

        int[] p = packetQueue.pollFirst();
        String key = p[0] + "#" + p[1] + "#" + p[2];
        uniquePackets.remove(key);

        processedCount.put(p[1], processedCount.getOrDefault(p[1], 0) + 1);

        return Arrays.asList(p[0], p[1], p[2]);
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destinationTimestamps.containsKey(destination)) return 0;

        List<Integer> timestamps = destinationTimestamps.get(destination);
        int startIndex = processedCount.getOrDefault(destination, 0);

        int left = lowerBound(timestamps, startIndex, startTime);
        int right = upperBound(timestamps, left, endTime);

        return right - left;
    }

    private int lowerBound(List<Integer> list, int start, int target) {
        int left = start, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int upperBound(List<Integer> list, int start, int target) {
        int left = start, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }
}
