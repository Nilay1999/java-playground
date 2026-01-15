package company.Amazon;

public class BestTimeToSellStock {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int max = 0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                max = Math.max(prices[i] - buy, max);
            }
        }

        return max;
    }
    
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(new BestTimeToSellStock().maxProfit(prices));
    }
}
