package com.ace.algorithm.linked;

/**
 * @see NodeUtilsTest
 */
public class NodeUtils {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(1);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(1);


        System.out.println(linkedList.first);
        System.out.println(linkedList.size);
        System.out.println(linkedList.reverseList());

    }

    /**
     * 找到一个链表的入环节点
     *
     * @param head 头
     * @param <E>  e
     * @return 如果有环 返回入环节点 ;  如果无环返回 null
     */
    public static <E> Node<E> loopNode(Node<E> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // plan 1
//        Node<E> slow = head.next;
//        Node<E> fast = head.next.next;
//
//        while (slow != fast) {
//            if (fast.next == null || fast.next.next == null) {
//                return null;
//            }
//            slow = slow.next;
//            fast = fast.next.next;
//        }

        // plan 2
        Node<E> slow = head;
        Node<E> fast = head;

        do {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while ((slow != fast));

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }


        return slow;
    }

    public static <E> void toString(Node<E> node) {
        if (node == null) {
            System.out.println("入参为null");
            return;
        }
        Node<E> loop = loopNode(node);
        if (loop == null) {
            StringBuilder sb = new StringBuilder();
            for (Node<E> x = node; x != null; x = x.next) {
                sb.append(x.val).append("->");
            }
            System.out.println(sb.substring(0, sb.lastIndexOf("->")));
        } else {
            System.out.println("有环链表,入环节点值:" + loop.val + "-> next:" + loop.next.val);
        }
    }


    /**
     * 翻转单向链表
     */
    public static <E> Node<E> reverseSingleList(Node<E> head) {
        Node<E> pre = null;
        Node<E> cur = head;
        while (cur != null) {
            Node<E> temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }


    /**
     * 翻转双向链表
     *
     * @param head
     */
    public static <E> Node<E> reverseList(Node<E> head) {
        Node<E> pre = null;
        Node<E> cur = head;
        while (cur != null) {
            Node<E> temNext = cur.next;
            cur.next = pre;
            cur.pre = temNext;
            pre = cur;
            cur = temNext;
        }
        return pre;
    }
}
