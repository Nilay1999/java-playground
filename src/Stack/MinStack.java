package Stack;

import java.util.Stack;

public class MinStack {
    /**
     * Min Stack Algorithm (Auxiliary Stack Approach):
     * Design stack supporting push, pop, top, and getMin in O(1) time.
     * 
     * SOLUTION: Use two stacks
     * 1. Main stack: stores all elements
     * 2. Monotonic stack: stores minimum elements in non-increasing order
     * 
     * OPERATIONS:
     * - push(x): Always push to main. Push to min stack if x ≤ current min
     * - pop(): Pop from main. If popped value equals min stack top, pop min stack too
     * - top(): Return main stack top
     * - getMin(): Return min stack top
     * 
     * KEY INSIGHT: Min stack maintains minimums for each "level" of main stack
     * 
     * Time: O(1) all operations, Space: O(n) for two stacks
     */
    Stack<Integer> stack;
    Stack<Integer> monotonicStack;

    public MinStack() {
        stack = new Stack<>();
        monotonicStack = new Stack<>();
    }

    public void push(int val) {
        // Always push to main stack
        stack.push(val);
        
        // Push to min stack if it's empty or val is new minimum
        if (monotonicStack.isEmpty() || val <= monotonicStack.peek()) {
            monotonicStack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int val = stack.pop(); // pop from main stack
            
            // If popped value is current minimum, pop from min stack too
            if (!monotonicStack.isEmpty() && monotonicStack.peek() == val) {
                monotonicStack.pop();
            }
        }
    }

    public int top() {
        // Return top of main stack
        return !stack.isEmpty() ? stack.peek() : -1;
    }

    public int getMin() {
        // Return top of min stack (current minimum)
        return !monotonicStack.isEmpty() ? monotonicStack.peek() : -1;
    }

    public static void main(String[] args) {
        MinStack init = new MinStack();
        init.push(-2);
        init.push(0);
        init.push(-3);
        init.getMin();
        init.pop();
        init.top();
        init.getMin();
    }
}
