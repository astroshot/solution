package com.solution.link;

/**
 * Created by dell on 2017/4/24.
 */
public class DLinkNode<T> {
    private T value;
    private DLinkNode prev, next;

    public DLinkNode() {
        this.value = null;
        this.prev = null;
        this.next = null;
    }

    public DLinkNode(T value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DLinkNode getPrev() {
        return prev;
    }

    public void setPrev(DLinkNode prev) {
        this.prev = prev;
    }

    public DLinkNode getNext() {
        return next;
    }

    public void setNext(DLinkNode next) {
        this.next = next;
    }

    public void delDLinkNode(DLinkNode node) {
        DLinkNode p = node.prev;
        DLinkNode q = node.next;
        p.next = q;
        q.prev = p;
    }
}
