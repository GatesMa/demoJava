package coder.t500_600.t509;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */
// 备忘录，剪枝
class Solution2 {
    public int fib(int n) {
        int[] memo = new int[n + 1];
        return helper(memo, n);
    }
    public int helper(int[] memo, int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        }
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }
}