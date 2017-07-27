package com.lxl.dataStructures.link;

/**
 * 链结点2
 *
 * @author lxl
 */
public class LinkM2 {

    public long dData;
    public LinkM2 next;

    public LinkM2(long dd) {
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
