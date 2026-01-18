package LinkedList;

/**
 * Doubly Linked List Data Structure:
 * Linear data structure where each node contains data and two pointers (prev, next).
 * 
 * ADVANTAGES OVER SINGLY LINKED LIST:
 * - Bidirectional traversal (forward and backward)
 * - O(1) deletion when node reference is given
 * - Better for implementing deque, LRU cache
 * 
 * OPERATIONS & TIME COMPLEXITY:
 * - Insert at beginning/end: O(1)
 * - Insert at position: O(n) - need to traverse
 * - Delete from beginning/end: O(1)
 * - Delete by value: O(n) - need to search
 * - Search: O(n)
 * - Access by index: O(n)
 * 
 * MEMORY: Each node uses extra space for prev pointer
 * 
 * STRUCTURE:
 * [prev|data|next] ←→ [prev|data|next] ←→ [prev|data|next]
 *        ↑                                        ↑
 *      head                                     tail
 * 
 * Space: O(n), where n is number of nodes
 */
public class DoublyLinkedList {

    private static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Constructor
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Insert at the beginning
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Insert at the end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Insert at specific position (0-indexed)
    public void insertAtPosition(int position, int data) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        if (position == size) {
            insertAtEnd(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    // Delete from beginning
    public void deleteFromBeginning() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    // Delete from end
    public void deleteFromEnd() {
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public boolean deleteByValue(int data) {
        if (head == null) {
            return false;
        }

        Node current = head;

        while (current != null) {
            if (current.data == data) {
                if (current == head) {
                    deleteFromBeginning();
                } else if (current == tail) {
                    deleteFromEnd();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Search for a value
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Get element at specific position
    public int get(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Display list forward
    public void displayForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        System.out.print("Forward: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Display list backward
    public void displayBackward() {
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = tail;
        System.out.print("Backward: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    // Get size of the list
    public int size() {
        return size;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clear the list
    public void reverseLinkedList() {
        Node ptr = head;
        Node prev = null;

        while (ptr != null) {
            prev = ptr.prev;
            ptr.prev = ptr.next;
            ptr.next = prev;
            ptr = ptr.prev;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // Test insertions
        dll.insertAtEnd(10);
        dll.insertAtEnd(20);
        dll.insertAtEnd(30);
        dll.insertAtBeginning(5);
        dll.insertAtPosition(2, 15);

        System.out.println("After insertions:");
        dll.displayForward(); // Output: Forward: 5 10 15 20 30
        dll.displayBackward(); // Output: Backward: 30 20 15 10 5

        // Test search
        System.out.println("Search 15: " + dll.search(15)); // true
        System.out.println("Search 100: " + dll.search(100)); // false

        // Test get
        System.out.println("Element at position 2: " + dll.get(2)); // 15

        // Test deletions
        dll.deleteByValue(15);
        dll.deleteFromBeginning();
        dll.deleteFromEnd();

        System.out.println("After deletions:");
        dll.displayForward(); // Output: Forward: 10 20

        System.out.println("Size: " + dll.size()); // 2
    }
}
