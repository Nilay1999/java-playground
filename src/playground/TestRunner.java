package playground;

/**
 * Test Runner for Java Playground
 * 
 * Run all question solutions to verify implementations
 */
public class TestRunner {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Java Playground - Test Runner");
        System.out.println("========================================\n");

        try {
            // Lambdas
            System.out.println(">>> Testing Lambdas <<<");
            playground.Lambdas.LambdaBasics.main(args);
            playground.Lambdas.MethodReferences.main(args);

            // Collections
            System.out.println(">>> Testing Collections <<<");
            playground.Collections.ListOperations.main(args);
            playground.Collections.MapOperations.main(args);

            // Streams
            System.out.println(">>> Testing Streams <<<");
            playground.Streams.StreamBasics.main(args);
            playground.Streams.StreamAdvanced.main(args);

            // Functional Programming
            System.out.println(">>> Testing Functional Programming <<<");
            playground.FunctionalProgramming.CustomFunctionalInterfaces.main(args);
            playground.FunctionalProgramming.OptionalHandling.main(args);

            // Multithreading (commented out as they take time)
            System.out.println(">>> Testing Multithreading <<<");
            System.out.println("Note: Multithreading tests are available but not run automatically.");
            System.out.println("Run them individually:");
            System.out.println("  - playground.Multithreading.ThreadBasics");
            System.out.println("  - playground.Multithreading.Synchronization");
            System.out.println("  - playground.Multithreading.ExecutorService");
            System.out.println("  - playground.Multithreading.ProducerConsumer");

            System.out.println("\n========================================");
            System.out.println("   All Tests Completed Successfully!");
            System.out.println("========================================");

        } catch (Exception e) {
            System.err.println("Test failed with error:");
            e.printStackTrace();
        }
    }
}
