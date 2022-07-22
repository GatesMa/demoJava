package coder.t1_100.t22;

import coder.common.ListNode;

// 快慢指针
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }
}