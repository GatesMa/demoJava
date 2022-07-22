package coder.t1_100.t1;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i++) {
            map.put(nums[i], i);
        }

        for (int i = 0;i < nums.length;i++) {
            int tmp = target - nums[i];
            if (map.containsKey(tmp) && map.get(tmp) != i) {
                return new int[]{i, map.get(tmp)};
            }
        }
        return new int[]{-1, -1};
        
    }
}