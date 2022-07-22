package coder.t300_400.t322;

// 暴力递归，超时
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        else if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int i = 0;i < coins.length;i++) {
            int sub = coinChange(coins, amount - coins[i]);
            if (sub != -1) res = Math.min(res, sub + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}





