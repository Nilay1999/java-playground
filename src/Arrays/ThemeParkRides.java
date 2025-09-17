package Arrays;

public class ThemeParkRides {

    public static int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime,
            int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int minFinishTime = Integer.MAX_VALUE;

        // Iterate over all land rides
        for (int i = 0; i < n; i++) {
            // Iterate over all water rides
            for (int j = 0; j < m; j++) {

                // Plan 1: Land ride first, then water ride
                int landFinish = landStartTime[i] + landDuration[i];
                int waterStart = Math.max(waterStartTime[j], landFinish);
                int waterFinish = waterStart + waterDuration[j];
                minFinishTime = Math.min(minFinishTime, waterFinish);

                // Plan 2: Water ride first, then land ride
                int waterFinishFirst = waterStartTime[j] + waterDuration[j];
                int landStartSecond = Math.max(landStartTime[i], waterFinishFirst);
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
