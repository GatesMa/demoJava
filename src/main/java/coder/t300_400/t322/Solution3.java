package coder.t300_400.t322;

/**
 * Solution3
 *
 * @author by gatesma on 2022/2/27.
 */
// 迭代
class Solution3 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        else if (amount == 0) return 0;

        int[] dp = new int[amount + 1];
        for (int i = 0;i < amount + 1;i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;
        for (int i = 0;i <= amount;i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}