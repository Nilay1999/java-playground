package Array;

public class KthFactorOfN {
    public int kthFactor(int n, int k) {
        int counter = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                counter++;
                if (counter == k) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new KthFactorOfN().kthFactor(12, 3));
        System.out.println(new KthFactorOfN().kthFactor(7, 2));
        System.out.println(new KthFactorOfN().kthFactor(67, 34));
        System.out.println(new KthFactorOfN().kthFactor(4, 4));
    }
}
