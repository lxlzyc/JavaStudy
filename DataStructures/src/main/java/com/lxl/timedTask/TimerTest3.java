package com.lxl.timedTask;

import java.util.Timer;

/**
 * @ClassName: TimerTest3
 * @Description: 指定延迟时间后指定时间循环执行任务
 * @author: lxl
 * @date: 2016年5月23日 上午10:46:55
 */
public class TimerTest3 {

    Timer timer;

    public TimerTest3() {
        timer = new Timer();
        timer.schedule(new TimerTaskTest3(), 1000, 2000);
    }

    public static void main(String[] args) {
        new TimerTest3();

    }

}
