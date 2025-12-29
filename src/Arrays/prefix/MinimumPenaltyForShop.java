package Arrays.prefix;

public class MinimumPenaltyForShop {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int prefix = 0;
        int maxProfit = 0;
        int time = 0;

        for (int i = 0; i < n; i++) {
            prefix = customers.charAt(i) == 'Y' ? 1 : -1;
            if (prefix > maxProfit) {
                maxProfit = prefix;
                time = i;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumPenaltyForShop().bestClosingTime("NNNNN"));
    }
}
