package playground.Multithreading;

/**
 * Question 7: Thread Creation and Execution
 * 
 * Task: Create threads using different approaches:
 * 1. Extend Thread class
 * 2. Implement Runnable interface
 * 3. Use lambda expression
 * 4. Demonstrate thread lifecycle states
 */
public class ThreadBasics {

    // TODO: Implement - Create a thread by extending Thread class
    static class CounterThread extends Thread {
        private String name;
        private int count;

        public CounterThread(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public void run() {
            // TODO: Print numbers from 1 to count with thread name
        }
    }

    // TODO: Implement - Create a thread using Runnable
    static class PrinterTask implements Runnable {
        private String message;
        private int times;

        public PrinterTask(String message, int times) {
            this.message = message;
            this.times = times;
        }

        @Override
        public void run() {
            // TODO: Print message 'times' number of times
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread Basics Test ===");

        // TODO: Create and start CounterThread
        CounterThread t1 = new CounterThread("Counter-1", 5);

        // TODO: Create and start thread using Runnable
        Thread t2 = new Thread(new PrinterTask("Hello", 3));

        // TODO: Create thread using lambda
        Thread t3 = new Thread(() -> {
            // Print "Lambda thread" 3 times
        });

        // TODO: Start all threads and wait for completion

        System.out.println("All threads completed!\n");
    }
}
