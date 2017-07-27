package com.lxl.dataStructures.stack;

import java.util.Stack;

/**
 * 栈
 *
 * @author lxl
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.add("1");
        stack.add("2");
        //push()将一个值压入栈顶
        stack.push("1");
        //pop()弹出栈顶值
        //peek()返回栈顶值
        //isEmpty()是否为空     isFull()是否满（maxSize-1）
        System.out.println(stack.pop());
    }
}
