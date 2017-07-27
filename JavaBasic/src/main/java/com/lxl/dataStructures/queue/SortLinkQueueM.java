package com.lxl.dataStructures.queue;

import com.lxl.dataStructures.link.FirstLastListM;

/**
 * 用链表实现的队列
 *
 * @author lxl
 */
public class SortLinkQueueM {
    private FirstLastListM thelist;

    public SortLinkQueueM() {
        thelist = new FirstLastListM();
    }

    //插入
    public void insert(long j) {
        thelist.insertLast(j);
    }

    //移除
    public long remove() {
        return thelist.deleteFirst();
    }

    public boolean isEmpty() {
        return (thelist.isEmpty());
    }

    public void displayStack() {
        System.out.print("Queue (front-->rear):");
        thelist.displayList();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
