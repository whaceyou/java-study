package com.ace.algorithm.linked;


/**
 * 一个数    将链表分割为  <   =   > 区域
 */
public class 链表荷兰国旗问题 {


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(9);
        linkedList.addLast(8);
        linkedList.addLast(28);
        linkedList.addLast(6);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(15);
        linkedList.addLast(20);
        linkedList.addLast(14);
        linkedList.addLast(14);
//        Node<Integer> solution = solution1(linkedList.first, 5);
//        System.out.println(solution.asString());
        Node<Integer> solution2 = solution2(linkedList.first, 5);
        NodeUtils.toString(solution2);

    }

    public static void swap(Node<Integer>[] arr, int first, int next) {
        Node<Integer> temp = arr[first];
        arr[first] = arr[next];
        arr[next] = temp;

    }

    public static Node<Integer> solution2(Node<Integer> head, int num) {
        Node<Integer> smallHead = null;
        Node<Integer> smallTail = null;
        Node<Integer> midHead = null;
        Node<Integer> midTail = null;
        Node<Integer> bigHead = null;
        Node<Integer> bigTail = null;
        Node<Integer> cur = head;
        while (cur != null) {
            Node<Integer> next = cur.next;
            cur.next = null;
            if (cur.val.compareTo(num) < 0) {
                if (smallTail == null) {
                    smallHead = cur;
                } else {
                    smallTail.next = cur;
                }
                smallTail = cur;
            } else if (cur.val.compareTo(num) == 0) {
                if (midTail == null) {
                    midHead = cur;
                } else {
                    midTail.next = cur;
                }
                midTail = cur;
            } else {
                if (bigTail == null) {
                    bigHead = cur;
                } else {
                    bigTail.next = cur;
                }
                bigTail = cur;
            }
            cur = next;
        }

        if (smallTail != null) { // 存在小于区域
            smallTail.next = midHead;
            // 谁去连大于区域的头,谁就变成 midTail
            midTail = midTail == null ? smallTail : midTail;
        }

        if (midTail != null) { // 存在中间区域
            midTail.next = bigHead;
        }

        return smallHead != null ? smallHead : (midHead != null ? midHead : bigHead);


    }

    public static Node<Integer> solution1(Node<Integer> head, int num) {
        int size = 0;
        Node<Integer> cur = head;
        while (cur != null) {
            cur = cur.next;
            size++;
        }
        Node<Integer>[] nodes = new Node[size];
        cur = head;
        int i = 0;
        while (cur != null) {
            nodes[i++] = cur;
            cur = cur.next;
        }

        i = 0;
        int less = i - 1;
        int more = size;
        while (i < more) {
            if (nodes[i].val < num) {
                swap(nodes, ++less, i++);
            } else if (nodes[i].val > num) {
                swap(nodes, --more, i);
            } else {
                i++;
            }
        }
        for (i = 0; i < nodes.length; i++) {
            if (i == nodes.length - 1) {
                nodes[i].next = null;
            } else {
                nodes[i].next = nodes[i + 1];
            }
            nodes[i].pre = null;
        }

        return nodes[0];


    }

}
