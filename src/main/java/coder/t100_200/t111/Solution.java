package  coder.t100_200.t111;

import coder.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

// BFS
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while(!q.isEmpty()) {

            int sz = q.size();
            for (int i = 0;i < sz;i++) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            depth++;
        }
        return depth;
    }



}