package coder.t500_600.t509;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */

// 迭代+状态压缩
class Solution4 {
    public int fib(int n) {
        if (n == 0) return 0;
        else if (n == 1 || n == 2) return 1;
        int pre = 1;
        int cur = 1;
        for (int i = 3;i <= n;i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}