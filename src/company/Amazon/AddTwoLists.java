package company.Amazon;

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

        ListNode ptr1 = head1;
        ListNode ptr2 = head2;
        ListNode head = new ListNode(0);
        ListNode ptr = head;

        while (ptr1 != null || ptr2 != null) {
            int val1 = ptr1 != null ? ptr1.val : 0;
            int val2 = ptr2 != null ? ptr2.val : 0;

            int total = val1 + val2 + carry;
            carry = total > 9 ? 1 : 0;

            ptr.next = new ListNode(total % 10, null);

            ptr = ptr.next;
            ptr1 = ptr1 != null ? ptr1.next : null;
            ptr2 = ptr2 != null ? ptr2.next : null;
        }

        if (carry > 0) {
            ptr.next = new ListNode(1);
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        System.out.println(new AddTwoLists().addTwoNumbers(l1, l2));
    }
}
