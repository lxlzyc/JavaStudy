package com.lxl.dataStructures.stack;

/**
 * 实现一个栈  数组实现
 *
 * @author lxl
 */
public class ArrayStack {

    //栈的size
    private int maxSize;
    private long[] stackArray;
    //栈的top
    private int top;

    public ArrayStack() {
        maxSize = 10;
        stackArray = new long[maxSize];
        top = -1;
    }

    public ArrayStack(int s) {
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;
    }

    public void push(long j) {
        stackArray[++top] = j;
    }

    public long pop() {
        return stackArray[top--];
    }

    public long peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }


    public static void main(String[] args) {
        ArrayStack sx = new ArrayStack();
        sx.push(1);
        sx.push(1);
        sx.push(2);
        sx.push(3);

        while (!sx.isEmpty()) {
            System.out.println(sx.pop());
        }
    }

}
