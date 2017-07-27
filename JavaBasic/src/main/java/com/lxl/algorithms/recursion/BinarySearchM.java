package com.lxl.algorithms.recursion;

/**
 * 递归实现二分查找
 *
 * @author lxl
 */
public class BinarySearchM {

    private long[] a;
    private int nElems;

    public BinarySearchM() {
        a = new long[10];
        nElems = 0;
    }

    public BinarySearchM(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    public int find(long searchKey) {
        return recFind(searchKey, 0, nElems - 1);
    }

    private int recFind(long searchKey, int lowerBound, int upperBound) {
        int curIn;
        curIn = (lowerBound + upperBound) / 2;
        if (a[curIn] == searchKey) {
            return curIn;
        } else if (lowerBound > upperBound) {
            return nElems;
        } else {
            if (a[curIn] < searchKey) {
                return recFind(searchKey, curIn + 1, upperBound);
            } else {
                return recFind(searchKey, lowerBound, curIn - 1);
            }
        }
    }

    //有序插入
    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] > value) {
                break;
            }
            for (int k = nElems; k > j; k--) {
                a[k] = a[k - 1];
            }
            a[j] = value;
            nElems++;
        }
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
