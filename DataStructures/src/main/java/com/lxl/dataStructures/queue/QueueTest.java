package com.lxl.dataStructures.queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 队列
 *
 * @author Administrator
 */
public class QueueTest {
    public static void main(String[] args) {

        /**
         * Queue使用时要尽量避免Collection的add()和remove()方法，而是要使用offer()来加入元素，使用poll()
         * 来获取并移出元素。它们的优点是通过返回值可以判断成功与否，add()和remove()方法在失败的时候会抛出异常。
         * 如果要使用前端而不移出该元素，使用 element()或者peek()方法。
         *
         * LinkedList实现了Queue接口
         */
        Queue que = new LinkedList();

    }
}
