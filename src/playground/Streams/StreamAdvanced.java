package playground.Streams;

import java.util.*;
import java.util.stream.*;

/**
 * Question 6: Advanced Stream Operations
 * 
 * Task: Implement complex stream operations:
 * 1. FlatMap - Flatten nested lists
 * 2. Collectors.groupingBy - Group and count
 * 3. Collectors.partitioningBy - Partition by condition
 * 4. Custom collector - Join strings with custom format
 */
public class StreamAdvanced {

    static class Order {
        String customer;
        List<String> items;
        double total;

        public Order(String customer, List<String> items, double total) {
            this.customer = customer;
            this.items = items;
            this.total = total;
        }
    }

    public static void main(String[] args) {
        testAdvancedStreams();
    }

    // TODO: Implement - Flatten list of lists into single list
    public static List<String> flattenLists(List<List<String>> nestedLists) {
        return null;
    }

    // TODO: Implement - Count frequency of each word
    public static Map<String, Long> wordFrequency(List<String> words) {
        return null;
    }

    // TODO: Implement - Partition orders into high value (>100) and low value
    public static Map<Boolean, List<Order>> partitionOrders(List<Order> orders) {
        return null;
    }

    // TODO: Implement - Get all unique items from all orders
    public static Set<String> getAllUniqueItems(List<Order> orders) {
        return null;
    }

    // TODO: Implement - Calculate average order total per customer
    public static Map<String, Double> averageOrderByCustomer(List<Order> orders) {
        return null;
    }

    private static void testAdvancedStreams() {
        System.out.println("=== Advanced Stream Operations Test ===");

        // Test 1: Flatten
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e"),
                Arrays.asList("f"));
        List<String> flattened = flattenLists(nested);
        System.out.println("Flattened: " + flattened);
        // Expected: [a, b, c, d, e, f]

        // Test 2: Word frequency
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
        Map<String, Long> frequency = wordFrequency(words);
        System.out.println("Word frequency: " + frequency);
        // Expected: {apple=3, banana=2, cherry=1}

        // Test 3: Partition orders
        List<Order> orders = Arrays.asList(
                new Order("Alice", Arrays.asList("laptop", "mouse"), 150.0),
                new Order("Bob", Arrays.asList("pen"), 5.0),
                new Order("Charlie", Arrays.asList("monitor", "keyboard"), 200.0),
                new Order("Alice", Arrays.asList("cable"), 10.0));
        Map<Boolean, List<Order>> partitioned = partitionOrders(orders);
        System.out.println("High value orders: " + partitioned.get(true).size());
        System.out.println("Low value orders: " + partitioned.get(false).size());

        // Test 4: Unique items
        Set<String> uniqueItems = getAllUniqueItems(orders);
        System.out.println("All unique items: " + uniqueItems);

        // Test 5: Average by customer
        Map<String, Double> avgByCustomer = averageOrderByCustomer(orders);
        System.out.println("Average order by customer: " + avgByCustomer);

        System.out.println("All tests completed!\n");
    }
}
