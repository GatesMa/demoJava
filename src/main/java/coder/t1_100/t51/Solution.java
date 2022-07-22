package  coder.t1_100.t51;

import java.util.ArrayList;
import java.util.List;

// 自己的
public class Solution {

    public List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> path = new ArrayList<>();

        // 初始化矩阵
        //初始化棋盘
        for(int i = 0; i < n; i++){
            StringBuilder str = new StringBuilder();
            for(int j = 0; j < n; j++){
                str.append('.');
            }
            path.add(str.toString());
        }



        // 开始回溯暴力查找
        backtrack(path, n, 0);
        return res;
    }

    public void backtrack(List<String> board, int n, int row) {

        if (row == n) {
            List<String> tmp = new ArrayList<>();
            for (String str : board) {
                tmp.add(str);
            }
            res.add(tmp);
            return;
        }

        for (int col = 0;col < n;col++) {
            // 不符合条件就continue
            if (!vaild(board, row, col)) {
                continue;
            }
            char[] chs = board.get(row).toCharArray();
            chs[col] = 'Q';
            board.set(row, new String(chs));
            //board.get(row).replace(col, col + 1, "Q");
            backtrack(board, n, row + 1);
            //board.get(row).replace(col, col + 1, ".");
            chs = board.get(row).toCharArray();
            chs[col] = '.';
            board.set(row, new String(chs));
        }
    }

    public boolean vaild(List<String> board, int row, int col) {
        int n = board.size();
        // 正上方
        for (int i = 0;i < row;i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        // 右上方
        for (int i = row - 1, j = col + 1;i >= 0 && j < n;i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        // 左上方
        for (int i = row - 1, j = col - 1;i >= 0 && j >= 0;i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}


