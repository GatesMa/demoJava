package coder.t300_400.t322;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */
// 备忘录递归
class Solution2 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        int[] memo = new int[amount+1];
        return helper(coins, amount, memo);
    }
    public int helper(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        else if (amount < 0) return -1;
        if (memo[amount] != 0) return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int i = 0;i < coins.length;i++) {
            int sub = helper(coins, amount - coins[i], memo);
            if (sub != -1) res = Math.min(res, sub + 1);
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[amount];
    }
}