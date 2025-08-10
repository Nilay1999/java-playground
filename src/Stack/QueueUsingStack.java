package Stack;

import java.util.Stack;

public class QueueUsingStack {

    public Stack<Integer> input;
    public Stack<Integer> output;

    public QueueUsingStack() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        while (!input.isEmpty()) {
            output.push(input.pop());
        }
        input.push(x);
        while (!output.isEmpty()) {
            input.push(output.pop());
        }
    }

    public int pop() {
        return input.pop();
    }

    public int peek() {
        return input.peek();
    }

    public boolean empty() {
        return output.empty() && input.empty();
    }

    public static void main(String[] args) {
        QueueUsingStack init = new QueueUsingStack();
        init.push(1);
        init.push(2);
        System.out.println(init.peek());
        init.push(3);
        System.out.println(init.pop());
        System.out.println(init.empty());
    }
}
