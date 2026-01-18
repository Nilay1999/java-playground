package SlidingWindow;

/**
 * Fruits Into Basket II Algorithm (Greedy Matching):
 * Place fruits into baskets where each basket can hold fruits of weight <= basket capacity.
 * Find minimum number of unplaced fruits.
 * 
 * GREEDY APPROACH:
 * 1. For each fruit, try to place it in any available basket
 * 2. A basket is available if its capacity >= fruit weight
 * 3. Once a fruit is placed, that basket becomes unavailable (capacity = 0)
 * 4. Count fruits that couldn't be placed
 * 
 * ALGORITHM:
 * 1. Iterate through each fruit
 * 2. For each fruit, check all baskets
 * 3. If basket capacity >= fruit weight:
 *    - Place fruit (set basket capacity to 0)
 *    - Increment placed count
 *    - Break (move to next fruit)
 * 4. Return total_fruits - placed_fruits
 * 
 * Example: fruits=[3,6,1], baskets=[6,4,7]
 * - Fruit 3: fits in basket 6 → basket becomes [0,4,7], placed=1
 * - Fruit 6: fits in basket 7 → basket becomes [0,4,0], placed=2  
 * - Fruit 1: fits in basket 4 → basket becomes [0,0,0], placed=3
 * Unplaced = 3-3 = 0
 * 
 * Time: O(n*m), Space: O(1) where n=fruits, m=baskets
 */
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
        int[] fruits = { 3, 6, 1 };
        int[] basket = { 6, 4, 7 };
        System.out.printf("Answer: %d", numOfUnplacedFruits(fruits, basket));
    }
}
