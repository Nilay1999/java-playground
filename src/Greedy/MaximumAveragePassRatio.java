package Greedy;

/**
 * Maximum Average Pass Ratio Algorithm (Greedy with Max Heap):
 * Distribute extra students to maximize average pass ratio across all classes.
 * 
 * PROBLEM DESCRIPTION:
 * - Each class has pass count and total count
 * - Pass ratio = pass / total
 * - Distribute extra students to classes to maximize average pass ratio
 * - Each extra student increases both pass and total by 1 in chosen class
 * 
 * ALGORITHM APPROACH:
 * 1. Use max heap to track classes by potential gain
 * 2. Gain = new_ratio - old_ratio = (p+1)/(t+1) - p/t
 * 3. Greedily assign each extra student to class with maximum gain
 * 4. After assignment, recalculate gain for that class and re-insert
 * 5. After all assignments, sum all ratios and divide by number of classes
 * 
 * KEY INSIGHTS:
 * - Greedy approach: always pick class with maximum marginal improvement
 * - Gain decreases as class gets more students (diminishing returns)
 * - Max heap efficiently finds class with best gain in O(log n)
 * - Gain formula: (p+1)/(t+1) - p/t = (t - p) / ((t+1) * t)
 * - Classes with lower pass ratio have higher gain (more room for improvement)
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: classes = [[2,4], [3,9], [4,5], [2,10]], extraStudents = 4
 * 
 * Initial gains:
 * Class 0: (3/5) - (2/4) = 0.6 - 0.5 = 0.1
 * Class 1: (4/10) - (3/9) = 0.4 - 0.333 = 0.067
 * Class 2: (5/6) - (4/5) = 0.833 - 0.8 = 0.033
 * Class 3: (3/11) - (2/10) = 0.273 - 0.2 = 0.073
 * 
 * Max heap: [0.1, 0.073, 0.067, 0.033]
 * 
 * Assign 1st student to class 0: [3,5], new gain = (4/6)-(3/5) = 0.067
 * Assign 2nd student to class 0: [4,6], new gain = (5/7)-(4/6) = 0.048
 * Assign 3rd student to class 0: [5,7], new gain = (6/8)-(5/7) = 0.036
 * Assign 4th student to class 0: [6,8], new gain = (7/9)-(6/8) = 0.028
 * 
 * Final ratios: 6/8 + 3/9 + 4/5 + 2/10 = 0.75 + 0.333 + 0.8 + 0.2 = 2.083
 * Average = 2.083 / 4 = 0.521
 * 
 * Time: O(k * log n) where k = extraStudents, n = number of classes
 * Space: O(n) for heap
 */
import java.util.PriorityQueue;

public class MaximumAveragePassRatio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        for (int[] aClass : classes) {
            double pass = aClass[0];
            double total = aClass[1];
            double gain = ((pass + 1) / (total + 1)) - (pass / total);
            maxHeap.offer(new double[] { gain, pass, total });
        }

        while (extraStudents > 0) {
            double[] current = maxHeap.poll();

            double pass = current[1] + 1;
            double total = current[2] + 1;

            double newGain = ((pass + 1) / (total + 1)) - (pass / total);
            maxHeap.offer(new double[] { newGain, pass, total });
            extraStudents--;
        }

        double average = 0;
        while (!maxHeap.isEmpty()) {
            double[] current = maxHeap.poll();
            average += current[1] / current[2];
        }

        return (double) average / n;
    }

    private double calculateGain(int[][] classes, int i) {
        return ((double) (classes[i][0] + 1) / (classes[i][1] + 1) - (double) classes[i][0] / classes[i][1]);
    }

    public static void main(String[] args) {
        int[][] classes = { { 2, 4 }, { 3, 9 }, { 4, 5 }, { 2, 10 } };
        int extra = 4;
        System.out.println(new MaximumAveragePassRatio().maxAverageRatio(classes, extra));
    }
}
