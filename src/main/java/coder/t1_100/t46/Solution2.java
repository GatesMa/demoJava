package  coder.t1_100.t46;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */
// 利用元素交换
class Solution2 {

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    public void backtrack(int[] nums, int pos) {
        if (pos == nums.length) {
            List<Integer> path = new ArrayList<>();
            for (int i : nums) {
                path.add(i);
            }
            res.add(path);
            return;
        }

        for (int i = pos;i < nums.length;i++) {
            swap(nums, pos, i);
            backtrack(nums, pos + 1);
            swap(nums, pos, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}

