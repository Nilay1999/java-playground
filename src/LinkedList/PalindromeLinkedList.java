package LinkedList;

/**
 * Palindrome Linked List Algorithm:
 * Check if linked list reads the same forwards and backwards.
 * 
 * ALGORITHM (O(1) Space):
 * 1. Find middle using slow/fast pointers (Floyd's algorithm)
 * 2. Reverse the second half of the list
 * 3. Compare first half with reversed second half
 * 4. Return true if all values match
 * 
 * STEPS:
 * 1. slow moves 1 step, fast moves 2 steps
 * 2. When fast reaches end, slow is at middle
 * 3. Reverse from slow to end
 * 4. Compare head→middle with reversed_middle→end
 * 
 * Example: 1→2→2→1
 * Step 1: Find middle (slow at second 2)
 * Step 2: Reverse second half: 1→2→2←1
 * Step 3: Compare: 1,2 vs 1,2 ✓
 * 
 * Alternative: Use stack/array (O(n) space) to store values and compare
 * 
 * Time: O(n), Space: O(1)
 */
public class PalindromeLinkedList {
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

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        ListNode right = prev;
        ListNode left = head;

        while (right != null) {
            if (right.val != left.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }
}
