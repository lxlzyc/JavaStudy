package com.lxl.timedTask;

import java.util.Date;
import java.util.TimerTask;

public class TimerTaskTest3 extends TimerTask {

    @Override
    public void run() {
        Date date = new Date(this.scheduledExecutionTime());
        System.out.println("本次执行该线程的时间为：" + date);

    }

}
