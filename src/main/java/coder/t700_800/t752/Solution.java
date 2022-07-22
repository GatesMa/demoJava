package coder.t700_800.t752;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int openLock(String[] deadends, String target) {

        // 死亡数字
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }
        // 判重
        Set<String> visit = new HashSet<>();

        // 待转的密码列
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        // 记录步数
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0;i < sz;i++) {
                String cur = q.poll();

                if (deads.contains(cur)) continue;
                if (target.equals(cur)) return step;

                for (int j = 0;j < 4;j++) {
                    String down = minusOne(cur, j);
                    if (!visit.contains(down)) {
                        q.offer(down);
                        visit.add(down);
                    }
                    String up = plusOne(cur, j);
                    if (!visit.contains(up)) {
                        q.offer(up);
                        visit.add(up);
                    }
                }

            }
            step++;
        }
        return -1;
    }

    // 将密码锁的一位向下转
    public String minusOne(String s, int i) {
        char[] chs = s.toCharArray();
        if (chs[i] == '0') {
            chs[i] = '9';
        } else {
            chs[i] -= 1;
        }
        return new String(chs);
    }

    // 将密码锁的一位向上转
    public String plusOne(String s, int i) {
        char[] chs = s.toCharArray();
        if (chs[i] == '9') {
            chs[i] = '0';
        } else {
            chs[i] += 1;
        }
        return new String(chs);
    }


}