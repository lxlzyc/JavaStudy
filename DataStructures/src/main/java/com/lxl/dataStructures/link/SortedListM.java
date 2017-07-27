package com.lxl.dataStructures.link;

/**
 * 无序数组转成有序列表
 *
 * @author lxl
 */
public class SortedListM {

    private LinkM2 first;

    public SortedListM() {
        first = null;
    }

    public SortedListM(LinkM2[] linkarr) {
        first = null;
        for (int j = 0, l = linkarr.length; j < l; j++) {
            insert(linkarr[j]);
        }
    }

    public void insert(LinkM2 k) {
        LinkM2 previous = null;
        LinkM2 current = first;
        while (current != null && k.dData > current.dData) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = k;
        } else {
            previous.next = k;
        }
        k.next = current;
    }

    public LinkM2 remove() {
        LinkM2 temp = first;
        first = first.next;
        return temp;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
