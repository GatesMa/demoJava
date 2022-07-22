package coder.t100_200.t142;

import coder.common.ListNode;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */
// 偷鸡摸狗法
public class Solution2 {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        Set<ListNode> set = new HashSet<>();
        while(head != null) {
            if(set.contains(head)) return head;
            else {
                set.add(head);
                head = head.next;
            }
        }
        return null;
    }
}
