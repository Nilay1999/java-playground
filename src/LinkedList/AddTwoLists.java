package LinkedList;

public class AddTwoLists {
    static class ListNode {
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

    public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int carry = 0;

        ListNode l1 = head1;
        ListNode l2 = head2;
        ListNode l = null;

        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;

            int total = a + b + carry;
            if (total > 9) {
                total = total % 10;
                carry = 1;
            }

            l = new ListNode(1, null);
            l1 = l1.next;
            l2 = l2.next;
        }

        if (carry > 1) {
            l.next = new ListNode(carry);
        }

        return l;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        System.out.println(new AddTwoLists().addTwoNumbers(l1, l2));
    }
}
