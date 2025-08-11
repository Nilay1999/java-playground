package Stack.StackWithLinkedList;

public class StackWithLinkedList {
    public int size;
    public Node top;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public StackWithLinkedList() {
        this.top = null;
        this.size = 0;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = this.top;
        this.top = newNode;
        this.size++;
    }

    public void pop() {
        if (this.top == null) {
            return;
        }
        this.size--;
        this.top = this.top.next;
    }

    public int peek() {
        return this.top.value;
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        StackWithLinkedList stack = new StackWithLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();

        System.out.println(stack.peek());
    }
}
