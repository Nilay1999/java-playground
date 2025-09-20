package Design;

import java.util.*;

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
