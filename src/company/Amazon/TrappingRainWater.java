package company.Amazon;

public class TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(height[i-1], left[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(height[i+1], right[i + 1]);
        }

        int total = 0;
        for (int i=0; i<n; i++) {
            int sum = Math.min(left[i], right[i]) - height[i];
            if (sum > 0) {
                total += sum;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        new TrappingRainWater().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
    }
}
