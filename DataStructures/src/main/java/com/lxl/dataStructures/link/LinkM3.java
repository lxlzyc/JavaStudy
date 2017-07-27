package com.lxl.dataStructures.link;

/**
 * 链结点3 支持双向链表
 *
 * @author lxl
 */
public class LinkM3 {

    public long dData;
    public LinkM3 next;
    public LinkM3 previous;

    public LinkM3(long dd) {
        dData = dd;
    }

    //显示链结点的数据值
    public void displayLink() {
        System.out.print("{" + dData + "}");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
