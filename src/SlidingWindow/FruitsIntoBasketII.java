package SlidingWindow;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class FruitsIntoBasketII {
    public static int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int ans = 0;
        for (int fruit : fruits) {
            for (int j = 0; j < baskets.length; j++) {
                if (baskets[j] >= fruit) {
                    baskets[j] = 0;
                    ans++;
                }
            }
        }

        return fruits.length - ans;
    }

    public static void main(String[] args) {
        int[] fruits = {3, 6, 1};
        int[] basket = {6, 4, 7};
        System.out.printf("Answer: %d", numOfUnplacedFruits(fruits, basket));
    }
}
