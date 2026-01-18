package Stack;

import java.util.Stack;

/**
 * Stock Spanner Algorithm (Monotonic Stack):
 * For each stock price, find span = number of consecutive days with price <= current.
 * 
 * SPAN DEFINITION:
 * - Span of price on day i = number of consecutive days before i (including i)
 *   where price[j] <= price[i]
 * 
 * MONOTONIC STACK APPROACH:
 * 1. Maintain stack of (price, span) pairs in decreasing order of prices
 * 2. For each new price:
 *    - Initialize span = 1 (current day)
 *    - While stack not empty AND top.price <= current.price:
 *      - Pop top and add its span to current span
 *      - This "jumps over" consecutive smaller prices
 *    - Push (current_price, current_span) to stack
 * 3. Return current span
 * 
 * KEY INSIGHT: By storing span with price, we can skip over multiple days
 * with smaller prices in one operation.
 * 
 * Example: prices = [100,80,60,70,60,75,85]
 * 
 * Day 0 (100): span=1, stack=[(100,1)]
 * Day 1 (80): 80<100, span=1, stack=[(100,1),(80,1)]
 * Day 2 (60): 60<80, span=1, stack=[(100,1),(80,1),(60,1)]
 * Day 3 (70): 70>60, pop (60,1) span+=1=2
 *             70<80, span=2, stack=[(100,1),(80,1),(70,2)]
 * Day 4 (60): 60<70, span=1, stack=[(100,1),(80,1),(70,2),(60,1)]
 * Day 5 (75): 75>60, pop (60,1) span+=1=2
 *             75>70, pop (70,2) span+=2=4
 *             75<80, span=4, stack=[(100,1),(80,1),(75,4)]
 * Day 6 (85): 85>75, pop (75,4) span+=4=5
 *             85>80, pop (80,1) span+=1=6
 *             85<100, span=6, stack=[(100,1),(85,6)]
 * 
 * Result: [1,1,1,2,1,4,6]
 * 
 * Time: O(n), Space: O(n)
 */
class StockSpanner {
    private static class StackItem {
        int stock;
        int stackSpan;

        StackItem(int stock, int stackSpan) {
            this.stock = stock;
            this.stackSpan = stackSpan;
        }
    }

    private final Stack<StackItem> stock = new Stack<>();

    public StockSpanner() {
    }

    public int next(int price) {
        int value = 1;
        while (!stock.isEmpty() && stock.peek().stock <= price) {
            value += stock.pop().stackSpan;
        }
        stock.push(new StackItem(price, value));
        return value;
    }
}
