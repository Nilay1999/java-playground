package playground.Multithreading;

import java.util.*;
import java.util.concurrent.*;

/**
 * Question 9: ExecutorService and Thread Pools
 * 
 * Task: Use ExecutorService for thread management:
 * 1. Create a fixed thread pool
 * 2. Submit tasks and get results using Future
 * 3. Use Callable instead of Runnable
 * 4. Handle task completion with CompletableFuture
 */
public class ExecutorService {

    // TODO: Implement - Calculate factorial using Callable
    static class FactorialCalculator implements Callable<Long> {
        private int number;

        public FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public Long call() throws Exception {
            // TODO: Calculate factorial
            return null;
        }
    }

    // TODO: Implement - Simulate API call with delay
    static class ApiCallTask implements Callable<String> {
        private String endpoint;
        private int delayMs;

        public ApiCallTask(String endpoint, int delayMs) {
            this.endpoint = endpoint;
            this.delayMs = delayMs;
        }

        @Override
        public String call() throws Exception {
            // TODO: Simulate delay and return result
            return null;
        }
    }

    public static void main(String[] args) {
        testFixedThreadPool();
        testCallableWithFuture();
        testCompletableFuture();
    }

    private static void testFixedThreadPool() {
        System.out.println("=== Fixed Thread Pool Test ===");

        // TODO: Create fixed thread pool with 3 threads
        java.util.concurrent.ExecutorService executor = null;

        // TODO: Submit 10 tasks
        for (int i = 1; i <= 10; i++) {
            final int taskNum = i;
            // Submit task that prints task number and thread name
        }

        // TODO: Shutdown executor

        System.out.println();
    }

    private static void testCallableWithFuture() {
        System.out.println("=== Callable with Future Test ===");

        // TODO: Create executor service
        java.util.concurrent.ExecutorService executor = null;

        try {
            // TODO: Submit factorial calculations for 5, 10, 15
            List<Future<Long>> futures = new ArrayList<>();

            // TODO: Get results from futures

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // TODO: Shutdown executor
        }

        System.out.println();
    }

    private static void testCompletableFuture() {
        System.out.println("=== CompletableFuture Test ===");

        // TODO: Create multiple async API calls
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return "Result from API 1";
            } catch (InterruptedException e) {
                return "Error";
            }
        });

        // TODO: Chain operations with thenApply, thenCompose
        // TODO: Combine multiple futures with allOf or anyOf

        System.out.println();
    }
}
