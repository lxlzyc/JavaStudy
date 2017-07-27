package com.lxl.dataStructures.iterator;

import com.lxl.dataStructures.link.LinkM2;

/**
 * 本类实现linklist  作为迭代器使用的linklist
 *
 * @author lxl
 */
public class LinkListForIterator {
    private LinkM2 first;

    public LinkListForIterator() {
        first = null;
    }

    public LinkM2 getFirst() {
        return first;
    }

    public void setFirst(LinkM2 first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public ListIteratorM getIterator() {
        return new ListIteratorM(this);
    }

    public void displayList() {
        LinkM2 current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}
