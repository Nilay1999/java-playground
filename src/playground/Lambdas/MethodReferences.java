package playground.Lambdas;

import java.util.*;
import java.util.function.*;

/**
 * Question 2: Method References
 * 
 * Task: Convert lambda expressions to method references where possible:
 * 1. Static method reference
 * 2. Instance method reference
 * 3. Constructor reference
 */
public class MethodReferences {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "bob", "CHARLIE", "david");

        // TODO: Replace with method reference to Integer::parseInt
        Function<String, Integer> parser = s -> Integer.parseInt(s);

        // TODO: Replace with method reference to String::toUpperCase
        Function<String, String> upperCase = s -> s.toUpperCase();

        // TODO: Replace with constructor reference to ArrayList::new
        Supplier<List<String>> listSupplier = () -> new ArrayList<>();

        // TODO: Replace with method reference to System.out::println
        Consumer<String> printer = s -> System.out.println(s);

        testMethodReferences(names, parser, upperCase, listSupplier, printer);
    }

    private static void testMethodReferences(List<String> names,
            Function<String, Integer> parser,
            Function<String, String> upperCase,
            Supplier<List<String>> listSupplier,
            Consumer<String> printer) {
        System.out.println("=== Method References Test ===");

        System.out.println("Parsing '42': " + parser.apply("42"));

        System.out.println("\nConverting to uppercase:");
        names.stream().map(upperCase).forEach(printer);

        List<String> newList = listSupplier.get();
        newList.add("Test");
        System.out.println("\nNew list created: " + newList);

        System.out.println("All tests passed!\n");
    }
}
