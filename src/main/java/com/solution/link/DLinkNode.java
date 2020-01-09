package com.solution.link;


public class DLinkNode<T> {

    private T value;

    private DLinkNode<T> prev, next;

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

    public DLinkNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DLinkNode<T> prev) {
        this.prev = prev;
    }

    public DLinkNode<T> getNext() {
        return next;
    }

    public void setNext(DLinkNode<T> next) {
        this.next = next;
    }

    public void delDLinkNode(DLinkNode<T> node) {
        DLinkNode<T> p = node.prev;
        DLinkNode<T> q = node.next;
        p.next = q;
        q.prev = p;
    }
}
