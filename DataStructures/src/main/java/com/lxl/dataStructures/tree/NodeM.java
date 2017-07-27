package com.lxl.dataStructures.tree;

/**
 * 二叉树的基类
 *
 * @author lxl
 */
public class NodeM {
    public int iData;
    public double dData;
    public NodeM leftChild;
    public NodeM rightChild;

    public void displayNodeM() {
        System.out.print("{" + iData + "," + dData + "}");
    }


}
