package Stack;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> monotonicStack;

    public MinStack() {
        stack = new Stack<>();
        monotonicStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (!monotonicStack.isEmpty() && val <= monotonicStack.peek()) {
            monotonicStack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int val = stack.pop();
            if (!monotonicStack.isEmpty() && monotonicStack.peek() == val) {
                monotonicStack.pop();
            }
        }
    }

    public int top() {
        return !stack.isEmpty() ? stack.peek() : -1;
    }

    public int getMin() {
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
