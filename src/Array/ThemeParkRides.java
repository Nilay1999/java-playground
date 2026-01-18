package Array;

public class ThemeParkRides {
    /**
     * Theme Park Ride Scheduling Algorithm:
     * Find minimum time to complete one land ride and one water ride.
     * 
     * Strategy: Try all combinations of (land_ride, water_ride) in both orders:
     * 1. Land first, then water: wait for water start time if needed
     * 2. Water first, then land: wait for land start time if needed
     * 
     * For each combination, calculate finish time and track minimum.
     * 
     * Time: O(n*m) where n=land rides, m=water rides
     * Space: O(1)
     */

    public static int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime,
            int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int minFinishTime = Integer.MAX_VALUE;

        // Try all combinations of land and water rides
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // Strategy 1: Do land ride first, then water ride
                // Calculate when land ride finishes
                int landFinish = landStartTime[i] + landDuration[i];
                // Water ride starts at its scheduled time or after land finishes (whichever is later)
                int waterStart = Math.max(waterStartTime[j], landFinish);
                // Calculate total finish time
                int waterFinish = waterStart + waterDuration[j];
                minFinishTime = Math.min(minFinishTime, waterFinish);

                // Strategy 2: Do water ride first, then land ride
                // Calculate when water ride finishes
                int waterFinishFirst = waterStartTime[j] + waterDuration[j];
                // Land ride starts at its scheduled time or after water finishes (whichever is later)
                int landStartSecond = Math.max(landStartTime[i], waterFinishFirst);
                // Calculate total finish time
                int landFinishSecond = landStartSecond + landDuration[i];
                minFinishTime = Math.min(minFinishTime, landFinishSecond);
            }
        }

        return minFinishTime;
    }

    public static void main(String[] args) {
        // Example test case
        int[] landStartTime = { 2, 8 };
        int[] landDuration = { 4, 1 };
        int[] waterStartTime = { 6 };
        int[] waterDuration = { 3 };

        int result = earliestFinishTime(landStartTime, landDuration, waterStartTime, waterDuration);
        System.out.println("Earliest possible finish time: " + result); // Should print 9

        // Complex test case example
        int[] landStartComplex = { 1, 5, 10, 15 };
        int[] landDurComplex = { 5, 6, 2, 8 };
        int[] waterStartComplex = { 3, 14, 7 };
        int[] waterDurComplex = { 10, 1, 8 };

        int complexResult = earliestFinishTime(landStartComplex, landDurComplex, waterStartComplex, waterDurComplex);
        System.out.println("Earliest finish time (complex case): " + complexResult);
    }
}
