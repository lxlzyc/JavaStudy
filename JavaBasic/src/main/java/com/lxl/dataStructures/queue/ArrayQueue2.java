package com.lxl.dataStructures.queue;

/**
 * 数组实现队列  没有数据个数记录nItems
 *
 * @author lxl
 */
public class ArrayQueue2 {

    private int maxSize;
    private long[] queArray;
    //front 游标指到队头
    private int front;
    //front 游标指到队尾
    private int rear;

    public ArrayQueue2() {
        maxSize = 10;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
    }

    public ArrayQueue2(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
    }

    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
    }

    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        return temp;
    }

    //返回队头数据项
    public long peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return ((rear + 1 == front) || (front + maxSize - 1 == rear));
    }

    public boolean isFull() {
        return ((rear + 2 == front) || (front + maxSize - 2 == rear));
    }

    public int size() {
        if (rear >= front) {
            return rear - front + 1;
        } else {
            return (maxSize - front) + (rear + 1);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
