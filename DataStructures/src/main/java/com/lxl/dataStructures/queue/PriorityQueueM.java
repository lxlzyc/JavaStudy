package com.lxl.dataStructures.queue;

/**
 * 数组实现  优先级队列(小的排在前面)
 * java中提供优先级队列PriorityQueue
 *
 * @author lxl
 */
public class PriorityQueueM {

    private int maxSize;
    private long[] queArray;
    private int nItems;

    public PriorityQueueM() {
        maxSize = 10;
        queArray = new long[maxSize];
        nItems = 0;
    }

    public PriorityQueueM(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        nItems = 0;
    }

    public void insert(long item) {
        int j;
        if (nItems == 0) {
            queArray[nItems++] = item;
        } else {
            //从数组后面往前找，数组越往后越小，右移动比item小的值，直到找到空位，然后插入
            for (j = nItems - 1; j >= 0; j--) {
                if (item > queArray[j]) {
                    queArray[j + 1] = queArray[j];
                } else {
                    break;
                }
            }
            queArray[j + 1] = item;
            nItems++;
        }
    }

    public long remove() {
        return queArray[--nItems];
    }

    //返回队头数据项
    public long peekMin() {
        return queArray[nItems - 1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PriorityQueueM thispq = new PriorityQueueM();
        thispq.insert(1);
        thispq.insert(3);
        thispq.insert(2);
        thispq.insert(3);
        thispq.insert(4);
        while (!thispq.isEmpty()) {
            System.out.println(thispq.remove());
        }
    }

}
