package com.lxl.timedTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Timer是基于绝对时间的，对系统时间比较敏感，而ScheduledThreadPoolExecutor
 * 则是基于相对时间；Timer是内部是单一线程，而ScheduledThreadPoolExecutor内部是个线程池，所以可以支持多个任务并发执行。
 *
 * @ClassName: ScheduledExecutorTest
 * @Description: TODO
 * @author: lxl
 * @date: 2016年5月23日 上午11:24:49
 */
public class ScheduledExecutorTest {

    private ScheduledExecutorService scheduExec;

    public long start;

    ScheduledExecutorTest() {
        this.scheduExec = Executors.newScheduledThreadPool(2);
        this.start = System.currentTimeMillis();
    }

    public void timerOne() {
        scheduExec.schedule(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                System.out.println("timerOne ,the time:" + (System.currentTimeMillis() - start));
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    public void timerTwo() {
        scheduExec.schedule(new Runnable() {
            public void run() {
                System.out.println("timerTwo, the time:" + (System.currentTimeMillis() - start));
            }
        }, 2000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        ScheduledExecutorTest test = new ScheduledExecutorTest();
        test.timerOne();
        test.timerTwo();
    }

}
