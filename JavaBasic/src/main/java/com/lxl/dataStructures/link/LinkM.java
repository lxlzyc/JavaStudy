package com.lxl.dataStructures.link;

/**
 * 链结点1
 *
 * @author lxl
 */
public class LinkM {

    public int iData;
    public double dData;
    public LinkM next;

    public LinkM(int id, double dd) {
        iData = id;
        dData = dd;
    }

    //显示链结点的数据值
    public void displayLink() {
        System.out.print("{" + iData + "," + dData + "}");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
