package com.lxl.dataStructures.iterator;

import com.lxl.dataStructures.link.LinkM2;

public class ListIteratorM {

    private LinkM2 current;
    private LinkM2 previous;
    private LinkListForIterator ourlist;

    public ListIteratorM(LinkListForIterator linkListForIterator) {
        ourlist = linkListForIterator;
        reset();
    }

    public void reset() {
        current = ourlist.getFirst();
        previous = null;
    }

    public boolean atEnd() {
        return (current.next == null);
    }

    public void nextLink() {
        previous = current;
        current = current.next;
    }

    public LinkM2 getCurrent() {
        return current;
    }

    public void insertAfter(long dd) {
        LinkM2 newlink = new LinkM2(dd);
        if (ourlist.isEmpty()) {
            ourlist.setFirst(newlink);
        } else {
            newlink.next = current.next;
            current.next = newlink;
            nextLink();
        }
    }

    public void insertBefore(long dd) {
        LinkM2 newlink = new LinkM2(dd);
        if (previous == null) {
            newlink.next = ourlist.getFirst();
            ourlist.setFirst(newlink);
            reset();
        } else {
            newlink.next = previous.next;
            previous.next = newlink;
            current = newlink;
        }
    }

    public long deleteCurrent() {
        long value = current.dData;
        if (previous == null) {
            ourlist.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
