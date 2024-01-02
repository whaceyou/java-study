package com.ace.algorithm.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 138
 * https://leetcode.cn/problems/copy-list-with-random-pointer/description/
 */
public class 复制含有随机指针节点的链表 {

    public static void main(String[] args) {

    }


    public static RandNode useMap(RandNode head) {
        Map<RandNode, RandNode> map = new HashMap<>();
        RandNode cur = head;
        while (cur != null) {
            map.put(cur, new RandNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }


    public static RandNode useSelf(RandNode head) {
        RandNode cur = head;
        RandNode next;
        while (cur != null) {
            next = cur.next;
            RandNode copyNode = new RandNode(cur.value);
            cur.next = copyNode;
            copyNode.next = next;
            cur = next;
        }
        cur = head;
        RandNode copy;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }
        RandNode result = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next == null ? null : next.next;
            cur = next;
        }
        return result;
    }

    public static class RandNode {
        int value;
        RandNode next;
        RandNode rand;

        public RandNode(int value) {
            this.value = value;
        }
    }


}



