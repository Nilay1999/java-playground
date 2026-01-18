package LinkedList;

/**
 * Odd Even Linked List Algorithm:
 * Rearrange linked list so all odd-positioned nodes come first, then even-positioned nodes.
 * 
 * ALGORITHM:
 * 1. Keep two pointers: odd (starts at head) and even (starts at head.next)
 * 2. Save even head to connect later
 * 3. Traverse and relink:
 *    - odd.next = even.next (skip even node)
 *    - even.next = even.next.next (skip odd node)
 *    - Move both pointers forward
 * 4. Connect odd tail to even head
 * 
 * KEY INSIGHT: Maintain two separate chains (odd and even) then merge
 * 
 * Example: 1→2→3→4→5
 * Step 1: odd=1, even=2, evenHead=2
 * Step 2: 1→3, 2→4 (odd chain: 1→3→5, even chain: 2→4)
 * Step 3: Connect: 1→3→5→2→4
 * 
 * Time: O(n), Space: O(1) - only rearranging pointers
 */
public class OddEvenLinkedList {
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

    static class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;

            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = even.next.next;
                even = even.next;
            }

            odd.next = evenHead;
            return head;
        }
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        System.out.println("Original list:");
        printList(head);

        Solution solution = new Solution();
        head = solution.oddEvenList(head);

        System.out.println("After reordering (odd-even):");
        printList(head);
    }
}
