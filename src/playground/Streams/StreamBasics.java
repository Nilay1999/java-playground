package playground.Streams;

import java.util.*;
import java.util.stream.*;

/**
 * Question 5: Stream API Basics
 * 
 * Task: Use Stream API to solve the following:
 * 1. Filter even numbers and square them
 * 2. Find the sum of all numbers greater than 10
 * 3. Get distinct elements and sort them in descending order
 * 4. Check if all elements satisfy a condition
 */
public class StreamBasics {

    public static void main(String[] args) {
        testStreamOperations();
    }

    // TODO: Implement - Filter even numbers, square them, and collect to list
    public static List<Integer> filterAndSquareEvens(List<Integer> numbers) {
        return null;
    }

    // TODO: Implement - Sum of numbers greater than 10
    public static int sumGreaterThan10(List<Integer> numbers) {
        return 0;
    }

    // TODO: Implement - Get distinct elements sorted in descending order
    public static List<Integer> distinctDescending(List<Integer> numbers) {
        return null;
    }

    // TODO: Implement - Check if all numbers are positive
    public static boolean allPositive(List<Integer> numbers) {
        return false;
    }

    // TODO: Implement - Find first element greater than 50, or return -1
    public static int findFirstGreaterThan50(List<Integer> numbers) {
        return -1;
    }

    private static void testStreamOperations() {
        System.out.println("=== Stream Operations Test ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20, 3, 8);

        // Test 1
        List<Integer> evenSquares = filterAndSquareEvens(numbers);
        System.out.println("Even numbers squared: " + evenSquares);
        // Expected: [4, 16, 36, 64, 100, 400, 64]

        // Test 2
        int sum = sumGreaterThan10(numbers);
        System.out.println("Sum of numbers > 10: " + sum);
        // Expected: 35

        // Test 3
        List<Integer> distinct = distinctDescending(numbers);
        System.out.println("Distinct descending: " + distinct);
        // Expected: [20, 15, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

        // Test 4
        boolean allPos = allPositive(numbers);
        System.out.println("All positive? " + allPos);
        // Expected: true

        // Test 5
        int first = findFirstGreaterThan50(numbers);
        System.out.println("First > 50: " + first);
        // Expected: -1

        System.out.println("All tests completed!\n");
    }
}
