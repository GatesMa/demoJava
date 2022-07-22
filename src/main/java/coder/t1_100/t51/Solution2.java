package  coder.t1_100.t51;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution2
 *
 * @author by gatesma on 2022/2/27.
 */
// 使用StringBuilder
class Solution2 {

    public List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<StringBuilder> path = new ArrayList<>();

        // 初始化矩阵
        //初始化棋盘
        for(int i = 0; i < n; i++){
            StringBuilder str = new StringBuilder();
            for(int j = 0; j < n; j++){
                str.append('.');
            }
            path.add(str);
        }



        // 开始回溯暴力查找
        backtrack(path, n, 0);
        return res;
    }

    public void backtrack(List<StringBuilder> board, int n, int row) {

        if (row == n) {
            List<String> tmp = new ArrayList<>();
            for (StringBuilder str : board) {
                tmp.add(str.toString());
            }
            res.add(tmp);
            return;
        }

        for (int col = 0;col < n;col++) {
            // 不符合条件就continue
            if (!vaild(board, row, col)) {
                continue;
            }

            board.get(row).setCharAt(col, 'Q');
            backtrack(board, n, row + 1);
            board.get(row).setCharAt(col, '.');
        }
    }

    public boolean vaild(List<StringBuilder> board, int row, int col) {
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

