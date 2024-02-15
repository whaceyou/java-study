package com.ace.algorithm.linked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeUtilsTest {

    LinkedList<Integer> linkedList = new LinkedList<>();



    @Test
    void loopNode() {
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(7);
        linkedList.addLast(8);
        linkedList.addLast(9);
        linkedList.addLast(10);
        linkedList.addLast(111);


        linkedList.last.next = linkedList.first.next.next;


//        Node<Integer> loop = NodeUtils.loopNode(linkedList.first);
//        NodeUtils.toString(loop);
        NodeUtils.toString(linkedList.first);

    }

    @Test
    void reverseSingleList() {
    }

    @Test
    void reverseList() {
    }
}