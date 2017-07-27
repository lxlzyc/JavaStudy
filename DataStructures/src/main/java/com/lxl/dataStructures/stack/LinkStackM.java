package com.lxl.dataStructures.stack;

import com.lxl.dataStructures.link.FirstLastListM;

/**
 * 通过链表形式实现栈
 * 调用自己的FirstLastListM  (单条long数据)
 *
 * @author Administrator
 */
public class LinkStackM {

    private FirstLastListM thelist;

    public LinkStackM() {
        thelist = new FirstLastListM();
    }

    //压入数据
    public void push(long j) {
        thelist.insertFirst(j);
    }

    //弹出数据
    public long pop() {
        return thelist.deleteFirst();
    }

    public boolean isEmpty() {
        return (thelist.isEmpty());
    }

    public void displayStack() {
        System.out.print("Stack (top-->bottom):");
        thelist.displayList();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
