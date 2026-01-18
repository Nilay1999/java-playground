package company.Amazon;

/**
 * Add Two Numbers (Linked Lists) Algorithm:
 * Add two numbers represented as linked lists in reverse order.
 * 
 * PROBLEM DESCRIPTION:
 * - Two linked lists represent numbers in reverse order
 * - Each node contains single digit (0-9)
 * - Add the two numbers and return result as linked list
 * - Example: 342 + 465 = 807 represented as [2,4,3] + [5,6,4] = [7,0,8]
 * 
 * ALGORITHM APPROACH:
 * 1. Traverse both lists simultaneously from head
 * 2. At each step, add corresponding digits plus carry from previous step
 * 3. Create new node with (sum % 10) and update carry = (sum / 10)
 * 4. Continue until both lists are exhausted
 * 5. If carry remains, create additional node
 * 
 * KEY INSIGHTS:
 * - Reverse order representation simplifies addition (no need to reverse)
 * - Carry propagates naturally through linked list traversal
 * - Handle lists of different lengths by treating missing nodes as 0
 * - Create dummy node at start to simplify result list construction
 * 
 * STEP-BY-STEP WALKTHROUGH:
 * Example: l1 = [2,4,3], l2 = [5,6,4]
 * Represents: 342 + 465 = 807
 * 
 * Step 1: 2 + 5 = 7, carry = 0, create node(7)
 * Step 2: 4 + 6 = 10, carry = 1, create node(0)
 * Step 3: 3 + 4 + 1 = 8, carry = 0, create node(8)
 * Step 4: No more nodes, no carry
 * Result: [7,0,8] representing 807
 * 
 * Example with different lengths: l1 = [9,9], l2 = [1]
 * Represents: 99 + 1 = 100
 * 
 * Step 1: 9 + 1 = 10, carry = 1, create node(0)
 * Step 2: 9 + 0 + 1 = 10, carry = 1, create node(0)
 * Step 3: carry = 1 remains, create node(1)
 * Result: [0,0,1] representing 100
 * 
 * Time: O(max(len(l1), len(l2))), Space: O(max(len(l1), len(l2))) for result
 */
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
