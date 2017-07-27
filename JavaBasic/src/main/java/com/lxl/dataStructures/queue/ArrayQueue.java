package com.lxl.dataStructures.queue;

/**
 * 数组实现队列
 *
 * @author lxl
 */
public class ArrayQueue {

    private int maxSize;
    private long[] queArray;
    //front 游标指到队头
    private int front;
    //front 游标指到队尾
    private int rear;
    //nItems 记录数据个数
    private int nItems;

    public ArrayQueue() {
        maxSize = 10;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public ArrayQueue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
        nItems++;
    }

    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    //返回队头数据项
    public long peekFront() {
        return queArray[front];
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

    }

}
