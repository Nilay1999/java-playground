package LinkedList;

/**
 * Remove Nth Node From End Algorithm:
 * Remove the nth node from the end of linked list in one pass.
 * 
 * TWO-POINTER TECHNIQUE:
 * 1. Use fast and slow pointers with n-gap between them
 * 2. Move fast pointer n steps ahead first
 * 3. If fast becomes null, remove head (nth from end is head)
 * 4. Move both pointers until fast.next is null
 * 5. slow will be at (n+1)th node from end
 * 6. Remove slow.next (which is nth from end)
 * 
 * KEY INSIGHT: Maintain constant gap of n between pointers
 * When fast reaches end, slow is at the node before target
 * 
 * Example: 1→2→3→4→5, n=2 (remove 4)
 * Step 1: fast moves 2 steps: fast at 3, slow at 1
 * Step 2: Move both until fast.next=null: fast at 5, slow at 3
 * Step 3: Remove slow.next: 3.next = 5 (skip 4)
 * Result: 1→2→3→5
 * 
 * Time: O(n), Space: O(1)
 */
public class RemoveNthNodeFromEnd {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            return head.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
