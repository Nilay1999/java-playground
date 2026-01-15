package company.Amazon;

public class MaximumTwinSumOfLinkedList {
    class ListNode {
        int val;
        ListNode next;
    }

    public int pairSum(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode prev = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        ListNode ptr = head;
        ListNode ptr2 = prev;
        int max = 0;
        while (ptr2 != null) {
            max = Math.max(max, ptr.val + ptr2.val);
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }

        return max;
    }
}
