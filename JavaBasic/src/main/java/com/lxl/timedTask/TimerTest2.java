package com.lxl.timedTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @ClassName: TimerTest2
 * @Description: TODO
 * @author: lxl
 * @date: 2016年5月23日 上午10:46:38
 */
public class TimerTest2 {
    Timer timer;

    public TimerTest2() {
        Date time = getTime();
        System.out.println("指定时间time=" + time);
        timer = new Timer();
        timer.schedule(new TimerTaskTest2(), time);
    }

    public Date getTime() {
        //在十点四十四的时候或之后执行定时任务
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 44);
        calendar.set(Calendar.SECOND, 00);
        Date time = calendar.getTime();
        return time;
    }

    public static void main(String[] args) {
        new TimerTest2();
    }
}
