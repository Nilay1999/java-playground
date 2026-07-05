package playground.Lambdas;

import java.util.function.*;

/**
 * Question 1: Lambda Basics
 * 
 * Task: Implement the following functional interfaces using lambda expressions:
 * 1. Create a lambda that takes two integers and returns their sum
 * 2. Create a lambda that checks if a string is empty
 * 3. Create a lambda that converts a string to uppercase
 * 4. Create a lambda that takes no arguments and prints "Hello Lambda!"
 */
public class LambdaBasics {

    public static void main(String[] args) {
        // TODO: Implement a BinaryOperator<Integer> for addition
        BinaryOperator<Integer> add = null;

        // TODO: Implement a Predicate<String> to check if string is empty
        Predicate<String> isEmpty = null;

        // TODO: Implement a Function<String, String> to convert to uppercase
        Function<String, String> toUpperCase = null;

        // TODO: Implement a Runnable to print "Hello Lambda!"
        Runnable greet = null;

        // Test cases
        testLambdas(add, isEmpty, toUpperCase, greet);
    }

    private static void testLambdas(BinaryOperator<Integer> add,
            Predicate<String> isEmpty,
            Function<String, String> toUpperCase,
            Runnable greet) {
        System.out.println("=== Lambda Basics Test ===");

        if (add != null) {
            System.out.println("5 + 3 = " + add.apply(5, 3));
            assert add.apply(5, 3) == 8 : "Addition failed";
        }

        if (isEmpty != null) {
            System.out.println("Is '' empty? " + isEmpty.test(""));
            System.out.println("Is 'hello' empty? " + isEmpty.test("hello"));
            assert isEmpty.test("") : "Empty check failed";
            assert !isEmpty.test("hello") : "Empty check failed";
        }

        if (toUpperCase != null) {
            System.out.println("'hello' to uppercase: " + toUpperCase.apply("hello"));
            assert "HELLO".equals(toUpperCase.apply("hello")) : "Uppercase conversion failed";
        }

        if (greet != null) {
            greet.run();
        }

        System.out.println("All tests passed!\n");
    }
}
