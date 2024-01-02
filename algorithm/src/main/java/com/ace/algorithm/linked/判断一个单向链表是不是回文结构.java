package com.ace.algorithm.linked;

import java.util.Stack;

public class 判断一个单向链表是不是回文结构 {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
//        linkedList.addLast(7);
//        linkedList.addLast(8);
//        linkedList.addLast(5);
//        linkedList.addLast(5);
//        linkedList.addLast(5);
//        linkedList.addLast(1);

        System.out.println(isXX1(linkedList.first));
        System.out.println(isXXvV2(linkedList.first));
        System.out.println(isXXvV3(linkedList.first));
        System.out.println();
    }


    public static boolean isXX1(Node<Integer> head) {
        Node<Integer> cur = head;
        Stack<Node<Integer>> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (!cur.val.equals(stack.pop().val)) {
                return false;
            }
            cur = cur.next;
        }
        return true;

    }

    public static boolean isXXvV2(Node<Integer> head) {
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        Stack<Node<Integer>> stack = new Stack<>();
        boolean fastIsEnd = false;
        while (slow != null) {
            if (fastIsEnd) {
                stack.push(slow);
            } else {
                if (fast.next == null || fast.next.next == null) {
                    fastIsEnd = true;
                } else {
                    fast = fast.next.next;
                }
            }
            slow = slow.next;
        }

        slow = head;
        boolean r = true;
        while (slow != null && !stack.isEmpty()) {
            if (!slow.val.equals(stack.pop().val)) {
                r = false;
            }
            slow = slow.next;
        }
        return r;
    }

    /**
     * 偶数:  fast 停在倒数第二个    slow 停在
     *
     * @param head
     * @return
     */
    public static boolean isXXvV3(Node<Integer> head) {
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
//        boolean fastIsEnd = false;
//        while (!fastIsEnd) {
//            if (fast.next == null || fast.next.next == null) {
//                fastIsEnd = true;
//            } else {
//                fast = fast.next.next;
//                slow = slow.next;
//            }
//        }

        Node<Integer> n1 = head;
        // 保存反转之后的 ,方便后面恢复
        Node<Integer> midNext = slow.next;
        slow.next = null;
        final Node<Integer> temp = NodeUtils.reverseSingleList(midNext);
        Node<Integer> n2 = temp;
        boolean r = true;
        while (n2 != null && n1 != null) {
            if (!n2.val.equals(n1.val)) {
                r = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 恢复
        slow.next = NodeUtils.reverseSingleList(temp);
        return r;

    }


}
