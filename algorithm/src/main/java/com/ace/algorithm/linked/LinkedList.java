package com.ace.algorithm.linked;

public class LinkedList<E> {

    int size;
    Node<E> first;
    Node<E> last;

    public void addFirst(E e) {
        linkFirst(e);
    }

    private void linkFirst(E e) {
        final Node<E> l = first;
        final Node<E> newNode = new Node<>(null, e, l);
        first = newNode;
        if (l == null) {
            last = newNode;
        } else {
            l.pre = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }


    public Node<E> reverseList() {
        Node<E> pre = null;
        Node<E> cur = first;
        Node<E> l = null;
        while (cur != null) {
            if (cur.next == null) {
                l = cur;
            }
            Node<E> temNext = cur.next;
            cur.next = pre;
            cur.pre = temNext;
            pre = cur;
            cur = temNext;
        }

        first = pre;
        last = l;
        return first;
    }

}
