package Stack;

import Stack.StackWithLinkedList.ListNode;

/**
 * Reorder List Algorithm (Two Pointers + Reversal):
 * Reorder linked list to L0→Ln→L1→Ln-1→L2→Ln-2→...
 * 
 * PROBLEM DESCRIPTION:
 * - Given linked list: L0→L1→...→Ln-1→Ln
 * - Reorder to: L0→Ln→L1→Ln-1→L2→Ln-2→...
 * - Modify list in-place
 * 
 * ALGORITHM APPROACH:
 * 1. Find middle of list using slow/fast pointers
 * 2. Reverse second half of list
 * 3. Merge first half with reversed second half
 * 
 * STEP-BY-STEP:
 * 1. Slow/fast pointer to find middle
 *    - slow moves 1 step, fast moves 2 steps
 *    - When fast reaches end, slow is at middle
 * 2. Split list at middle
 *    - First half: head to slow
 *    - Second half: slow.next to end
 * 3. Reverse second half
 *    - Use standard iterative reversal
 * 4. Merge two halves
 *    - Alternate nodes from first and reversed second
 * 
 * KEY INSIGHTS:
 * - Slow/fast pointers find middle in O(n) time
 * - Reversing second half is standard linked list reversal
 * - Merging alternates between two lists
 * - In-place modification: no extra list needed
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: 1→2→3→4→5
 * 
 * Step 1: Find middle
 * slow at 3, fast at 5
 * 
 * Step 2: Split
 * First: 1→2→3
 * Second: 4→5
 * 
 * Step 3: Reverse second
 * Reversed: 5→4
 * 
 * Step 4: Merge
 * 1→5→2→4→3
 * 
 * Example: 1→2→3→4
 * 
 * Step 1: Find middle
 * slow at 2, fast at 4
 * 
 * Step 2: Split
 * First: 1→2
 * Second: 3→4
 * 
 * Step 3: Reverse second
 * Reversed: 4→3
 * 
 * Step 4: Merge
 * 1→4→2→3
 * 
 * Time: O(n), Space: O(1)
 */
public class ReOrderList {

    public static void reorderList(ListNode head) {
        if (head == null) return;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;
        ListNode prev = null;

        while (second != null) {
            ListNode temp = second.next;
            second.next = prev;
            prev = second;
            second = temp;
        }

        ListNode first = head;
        second = prev;

        while (second != null) {
            ListNode temp1 = first.next, temp2 = second.next;
            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head = head.next;
        head.next = new ListNode(2);
        head = head.next;
        head.next = new ListNode(3);
        head = head.next;
        head.next = new ListNode(4);
        head = head.next;
        head.next = new ListNode(5);
        head = head.next;

        reorderList(head);
    }
}


// 1 -> 2 -> 3

