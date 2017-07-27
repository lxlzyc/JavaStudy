package com.lxl.dataStructures.link;

/**
 * 双向链表
 *
 * @author lxl
 */
public class DoubleLinkedList {

    private LinkM3 first;
    private LinkM3 last;

    public DoubleLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertFirst(long dd) {
        LinkM3 newlink = new LinkM3(dd);
        if (isEmpty()) {
            last = newlink;
        } else {
            first.previous = newlink;
        }
        newlink.next = first;
        first = newlink;
    }

    public void insertLast(long dd) {
        LinkM3 newlink = new LinkM3(dd);
        if (isEmpty()) {
            first = newlink;
        } else {
            last.previous = newlink;
            newlink.previous = last;
        }
        last = newlink;
    }

    public LinkM3 deleteFirst() {
        LinkM3 temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public LinkM3 deleteLast() {
        LinkM3 temp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    public boolean insertAfter(long key, long dd) {
        LinkM3 current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }
        LinkM3 newlink = new LinkM3(dd);
        if (current == last) {
            newlink.next = null;
            last = newlink;
        } else {
            newlink.next = current.next;
            current.next.previous = newlink;
        }
        newlink.previous = current;
        current.next = newlink;
        return true;
    }

    public LinkM3 deleteKey(long key) {
        LinkM3 current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }

        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    public void dispakyForward() {
        System.out.print("List (first-->last): ");
        LinkM3 current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public void dispakyBackward() {
        System.out.print("List (last-->first): ");
        LinkM3 current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println("");
    }

    public static void main(String[] args) {

    }
}
