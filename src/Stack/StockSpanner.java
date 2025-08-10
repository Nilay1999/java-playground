package Stack;

import java.util.Stack;

class StackItem {
    int stock;
    int stackSpan;

    public StackItem(int price, int val) {
        this.stock = price;
        this.stackSpan = val;
    }
}

class StockSpanner {
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
