package coder.t100_200.t124;

/**
 * Solution
 *
 * @author by gatesma on 2022/2/27.
 */

import coder.common.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {


    // 全局最大和
    int ans = Integer.MIN_VALUE;


    public int maxPathSum(TreeNode root) {
        this.maxPathSumHelper(root);
        return ans;
    }


    /**
     * 全局路径上子节点的最大贡献值 = max(left, right) + val
     * 全局路径上根节点的最大贡献值 = left + right + val
     *
     *
     * 这个方法返回的是当前节点root作为全局路径`子节点` 的`最大贡献值`
     * 一个子节点的最大贡献值 = max(left(负数的话舍弃为0), right(负数的话舍弃为0)) + val
     *
     *
     * 维护一个全局最大值，一定是：某一个节点作为`根节点`的最大贡献值(左+右+val)，只需要维护一个 ans全局最大值即可
     *
     */
    public int maxPathSumHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int left = Math.max(0, maxPathSumHelper(root.left));
        int right = Math.max(0, maxPathSumHelper(root.right));

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        // 更新答案
        ans = Math.max(ans, left + right + root.val);

        // 返回节点的最大贡献值
        return Math.max(left, right) + root.val;
    }

}
