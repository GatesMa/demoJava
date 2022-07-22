package coder.t100_200.t141;

import coder.common.ListNode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        // 快慢指针一定要判断next是不是null
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            low = low.next;
            if (fast == low) return true;
        }
        return false;
    }
}