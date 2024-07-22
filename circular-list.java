package linklist;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        // keep track of head(start)
        ListNode start = head;

        // find mid, using two pointer
        ListNode mid = findMid(head);
        System.out.println(mid.val);

        // reverse from mid
        mid.next = reverseList(mid);
        printList(start);

        // start, mid+1 compare --> if same,return true |
        // else return false

        // optional: reverse from mid again to make same state as from begaining.
        return false;

    }

    public ListNode findMid(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        // 1 2 5 5 2 1
        // p1
        // p2

        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        // -> 4 -> 5 -> 6 -> null
        // crr
        // 6 5 4

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        System.out.println("hi");
    }
}