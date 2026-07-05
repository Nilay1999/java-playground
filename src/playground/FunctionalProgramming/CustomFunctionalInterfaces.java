package playground.FunctionalProgramming;

/**
 * Question 11: Custom Functional Interfaces
 * 
 * Task: Create and use custom functional interfaces:
 * 1. TriFunction - takes 3 parameters
 * 2. StringProcessor - processes strings
 * 3. Calculator - performs operations
 */
public class CustomFunctionalInterfaces {

    // TODO: Create @FunctionalInterface for 3 parameters
    @FunctionalInterface
    interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    // TODO: Create functional interface for string processing
    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
    }

    public static void main(String[] args) {
        // TODO: Implement TriFunction to sum 3 numbers
        TriFunction<Integer, Integer, Integer, Integer> sum = null;

        // TODO: Implement StringProcessor
        StringProcessor reverseString = null;

        System.out.println("Sum: " + sum.apply(1, 2, 3));
        System.out.println("Reversed: " + reverseString.process("hello"));
    }
}
