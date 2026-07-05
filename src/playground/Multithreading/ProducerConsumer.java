package playground.Multithreading;

import java.util.*;
import java.util.concurrent.*;

/**
 * Question 10: Producer-Consumer Pattern
 * 
 * Task: Implement producer-consumer using:
 * 1. BlockingQueue
 * 2. wait() and notify()
 */
public class ProducerConsumer {

    // TODO: Implement using BlockingQueue
    static class BlockingQueueExample {
        private BlockingQueue<Integer> queue;

        public BlockingQueueExample(int capacity) {
            this.queue = new LinkedBlockingQueue<>(capacity);
        }

        class Producer implements Runnable {
            @Override
            public void run() {
                // TODO: Produce items
            }
        }

        class Consumer implements Runnable {
            @Override
            public void run() {
                // TODO: Consume items
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Producer-Consumer Test ===");
        // TODO: Test implementation
    }
}
