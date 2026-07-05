package playground.Multithreading;

/**
 * Question 8: Thread Synchronization
 * 
 * Task: Implement thread-safe operations:
 * 1. Synchronized method to increment counter
 * 2. Synchronized block for critical section
 * 3. Demonstrate race condition without synchronization
 * 4. Use volatile keyword appropriately
 */
public class Synchronization {

    // TODO: Implement thread-safe counter
    static class Counter {
        private int count = 0;

        // TODO: Make this method thread-safe
        public void increment() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    // TODO: Implement bank account with synchronized withdrawal
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        // TODO: Make this method thread-safe
        public boolean withdraw(double amount) {
            if (balance >= amount) {
                // Simulate processing time
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= amount;
                return true;
            }
            return false;
        }

        public double getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testCounter();
        testBankAccount();
    }

    private static void testCounter() throws InterruptedException {
        System.out.println("=== Counter Synchronization Test ===");

        Counter counter = new Counter();

        // TODO: Create 10 threads, each incrementing counter 1000 times
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // Wait for all threads
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final count: " + counter.getCount());
        System.out.println("Expected: 10000");
        System.out.println();
    }

    private static void testBankAccount() throws InterruptedException {
        System.out.println("=== Bank Account Synchronization Test ===");

        BankAccount account = new BankAccount(1000);

        // TODO: Create 5 threads, each trying to withdraw 300
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                boolean success = account.withdraw(300);
                System.out.println("Thread " + threadNum + " withdrawal: " +
                        (success ? "SUCCESS" : "FAILED"));
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final balance: " + account.getBalance());
        System.out.println("Expected: 100 or 400 (depending on which threads succeeded)");
        System.out.println();
    }
}
