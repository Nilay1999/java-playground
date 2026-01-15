package LinkedList;

public class ReverseNodesInKGroup {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode start, ListNode end) {
        ListNode prev = null;

        while (start != end) {
            ListNode temp = start.next;
            start.next = prev;
            prev = start;
            start = temp;
        }

        return prev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode ptr = head;
        for (int i = 0; i < k; i++) {
            if (ptr == null)
                return null;
            ptr = ptr.next;
        }

        ListNode reversed = reverseList(head, ptr);
        head.next = reverseKGroup(ptr, k);

        return reversed;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int k = 3;

        System.out.println(new ReverseNodesInKGroup().reverseKGroup(head, k));
    }
}
