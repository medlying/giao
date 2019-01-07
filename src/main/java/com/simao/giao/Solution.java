package com.simao.giao;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<ListNode> result = new ArrayList<>();
        boolean flag = false;
        do {
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;
            if (flag) {
                if (x1 + x2 + 1 < 10) {
                    result.add(new ListNode(x1 + x2 + 1));
                    flag = false;
                } else {
                    result.add(new ListNode((x1 + x2 + 1) % 10));
                    flag = true;
                }
            } else {
                if (x1 + x2 < 10) {
                    result.add(new ListNode(x1 + x2));
                    flag = false;
                } else {
                    result.add(new ListNode((x1 + x2) % 10));
                    flag = true;
                }
            }
            if (l1 != null) {
                l1 = l1.next != null ? l1.next : null;
            }
            if (l2 != null) {
                l2 = l2.next != null ? l2.next : null;
            }
        } while(l1 != null || l2 != null || flag);
        for(int i=0;i<result.size() -1;i++) {
            result.get(i).next = result.get(i+1);
        }
        return result.get(0);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5 );

        ListNode l4 = new ListNode(5);

        Solution solution = new Solution();
        System.out.println(solution.addTwoNumbers(l1, l4).next.val);
    }
}