package Design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Food Ratings System Design:
 * Support changing food ratings and querying highest-rated food in cuisine.
 * 
 * DATA STRUCTURES:
 * 1. cuisineQueue: Map<cuisine, PriorityQueue<Food>>
 *    - Max heap for each cuisine (by rating, then alphabetically)
 * 2. foodToRating: Map<food, rating>
 *    - Track current rating of each food
 * 3. foodToCuisine: Map<food, cuisine>
 *    - Track which cuisine each food belongs to
 * 
 * ALGORITHM:
 * 
 * changeRating(food, newRating):
 * - Update foodToRating with new rating
 * - Add new Food object to cuisine's heap (don't remove old)
 * - Lazy deletion: old entries become invalid
 * 
 * highestRated(cuisine):
 * - Poll from heap until finding valid entry
 * - Valid = current rating matches heap entry rating
 * - Return food name of valid entry
 * 
 * LAZY DELETION OPTIMIZATION:
 * - Don't remove old entries from heap
 * - When querying, skip invalid entries
 * - Reduces deletion overhead from O(n) to O(1)
 * 
 * COMPARATOR:
 * - Primary: higher rating first (descending)
 * - Secondary: lexicographically smaller name
 * 
 * Example: foods=["kimchi","miso","sushi","moussaka","isukaki"]
 *          cuisines=["korean","japanese","japanese","greek","japanese"]
 *          ratings=[9,12,8,16,7]
 * 
 * changeRating("sushi", 16):
 * - foodToRating["sushi"] = 16
 * - Add Food("sushi", "japanese", 16) to japanese heap
 * 
 * highestRated("japanese"):
 * - Heap top might be old Food("sushi", "japanese", 8)
 * - Check: foodToRating["sushi"] = 16 ≠ 8 → invalid, poll
 * - Next: Food("miso", "japanese", 12)
 * - Check: foodToRating["miso"] = 12 = 12 → valid, return "miso"
 * 
 * Time: O(1) add, O(log n) change/query
 * Space: O(n)
 */
class FoodRatings {
    private final Map<String, PriorityQueue<Food>> cuisineQueue;
    private final Map<String, Integer> foodToRating;
    private final Map<String, String> foodToCuisine;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        Comparator<Food> foodComparator = (f1, f2) -> {
            if (f1.rating != f2.rating) {
                return Integer.compare(f2.rating, f1.rating);
            }
            return f1.foodName.compareTo(f2.foodName);
        };

        int n = cuisines.length;
        cuisineQueue = new HashMap<>();
        foodToRating = new HashMap<>();
        foodToCuisine = new HashMap<>();

        for (int i = 0; i < n; i++) {
            foodToCuisine.put(foods[i], cuisines[i]);
            foodToRating.put(foods[i], ratings[i]);
            PriorityQueue<Food> queue = cuisineQueue.get(cuisines[i]);
            if (queue != null) {
                queue.add(new Food(foods[i], cuisines[i], ratings[i]));
            } else {
                PriorityQueue<Food> q = new PriorityQueue<>(foodComparator);
                q.add(new Food(foods[i], cuisines[i], ratings[i]));
                cuisineQueue.put(cuisines[i], q);
            }
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        PriorityQueue<Food> queue = cuisineQueue.get(cuisine);
        queue.add(new Food(food, cuisine, newRating));
        foodToRating.put(food, newRating);
    }

    public String highestRated(String cuisine) {
        PriorityQueue<Food> queue = cuisineQueue.get(cuisine);
        while (!queue.isEmpty()) {
            Food top = queue.peek();
            if (foodToRating.get(top.foodName) == top.rating) {
                return top.foodName;
            }
            queue.poll();
        }
        return null;
    }

    private static class Food {
        String foodName;
        String cuisine;
        int rating;

        public Food(String foodName, String cuisine, int rating) {
            this.foodName = foodName;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }
}
