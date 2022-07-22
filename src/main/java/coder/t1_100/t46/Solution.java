package coder.t1_100.t46;

import java.util.LinkedList;
import java.util.List;

// 利用链表
class Solution {
    public List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(nums, path);
        return res;
    }

    public void backtrack(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new LinkedList(path));
            return;
        }
        for (int i = 0;i < nums.length;i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backtrack(nums, path);
            path.removeLast();
        }
    }
}



