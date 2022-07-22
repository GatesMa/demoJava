package coder.t500_600.t509;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */
// 迭代
class Solution3 {
    public int fib(int n) {
        if (n == 0) return 0;
        else if (n == 1 || n == 2) return 1;
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;
        for (int i = 3;i <= n;i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }
}
