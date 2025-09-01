package Greedy;

import java.util.PriorityQueue;

public class MaximumAveragePassRatio {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        for (int[] aClass : classes) {
            double pass = aClass[0];
            double total = aClass[1];
            double gain = ((pass + 1) / (total + 1)) - (pass / total);
            maxHeap.offer(new double[]{gain, pass, total});
        }

        while (extraStudents > 0) {
            double[] current = maxHeap.poll();

            double pass = current[1] + 1;
            double total = current[2] + 1;

            double newGain = ((pass + 1) / (total + 1)) - (pass / total);
            maxHeap.offer(new double[]{newGain, pass, total});
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
        int[][] classes = {{2, 4}, {3, 9}, {4, 5}, {2, 10}};
        int extra = 4;
        System.out.println(new MaximumAveragePassRatio().maxAverageRatio(classes, extra));
    }
}
