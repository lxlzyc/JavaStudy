package com.lxl.timedTask;

import java.util.Timer;

/**
 * @ClassName: TimerTest1
 * @Description: 指定延迟时间执行定时任务
 * @author: lxl
 * @date: 2016年5月23日 上午10:36:07
 */
public class TimerTest1 {

    Timer timer;

    public TimerTest1(int time) {
        timer = new Timer();
        timer.schedule(new TimerTaskTest1(), time * 1000);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("timer begin...");
        new TimerTest1(3);
    }

}
