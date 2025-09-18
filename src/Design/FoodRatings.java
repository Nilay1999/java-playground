package Design;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
