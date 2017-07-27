package com.lxl.timedTask;

import java.util.TimerTask;

public class TimerTaskTest2 extends TimerTask {

    @Override
    public void run() {
        System.out.println("指定时间执行线程任务。。。");
    }

}
