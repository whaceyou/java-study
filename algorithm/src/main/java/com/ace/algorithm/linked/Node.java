package com.ace.algorithm.linked;

public class Node<E> {
    Node<E> pre;
    E val;
    Node<E> next;

    public Node(Node<E> pre, E val, Node<E> next) {
        this.pre = pre;
        this.val = val;
        this.next = next;
    }

    public Node(E val) {
        this.val = val;
    }

    public Node(E val, Node<E> pre, Node<E> next) {
        this.val = val;
        this.pre = pre;
        this.next = next;
    }

    public void addLast(Node<E> node) {
        node.next = null;
        Node<E> last = findLast();
        node.pre = last;
        last.next = node;
    }

    public void addFirst(Node<E> node) {
        Node<E> first = findFirst();
        first.pre = node;
        node.next = first;
    }


    private Node<E> findLast() {
        if (currentIsLast()) {
            return this;
        }
        Node<E> temp = next;
        Node<E> last = null;
        while (temp != null) {
            if (temp.next == null) {
                last = temp;
            }
            temp = temp.next;

        }
        return last;
    }

    private boolean currentIsLast() {
        return next == null;
    }

    private Node<E> findFirst() {
        if (currentIsFirst()) {
            return this;
        }
        Node<E> temp = pre;
        Node<E> first = null;
        while (temp != null) {
            if (temp.pre == null) {
                first = temp;
            }
            temp = temp.pre;

        }
        return first;
    }

    private boolean currentIsFirst() {
        return pre == null;
    }

}
