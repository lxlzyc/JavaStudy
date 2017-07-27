package com.lxl.timedTask;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @ClassName: QuartzManager
 * @Description: 定时任务管理类 使用quartz-1.8.6 jar
 * @author: lxl
 * @date: 2016年5月23日 下午2:31:51
 */
public class QuartzManager {
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

    /**
     * @param jobName 任务名
     * @param cls     任务
     * @param time    时间设置
     * @Title: addJob
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     * @return: void
     */
    @SuppressWarnings("unchecked")
    public static void addJob(String jobName, Class cls, String time) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);
            CronTrigger trigger = new CronTrigger(jobName, JOB_GROUP_NAME, TRIGGER_GROUP_NAME);

            trigger.setCronExpression(time);
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param time             时间设置
     * @Title: addJob
     * @Description: 添加一个定时任务
     * @return: void
     */
    @SuppressWarnings("unchecked")
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
                              Class jobClass, String time) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail(jobName, jobGroupName, jobClass);
            CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);

            trigger.setCronExpression(time);
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param jobName
     * @param time
     * @Title: modifyJobTime
     * @Description: 修改一个任务的出发时间 使用默认的任务组名，触发器名，触发器组名
     * @return: void
     */
    @SuppressWarnings("unchecked")
    public static void modifyJobTime(String jobName, String time) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName, TRIGGER_GROUP_NAME);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                JobDetail jobDetail = sched.getJobDetail(jobName, JOB_GROUP_NAME);
                Class objJobClass = jobDetail.getJobClass();
                removeJob(jobName);
                addJob(jobName, objJobClass, time);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param triggerName
     * @param triggerGroupName
     * @param time
     * @Title: modifyJobTime
     * @Description: 修改一个任务的触发时间
     * @return: void
     */
    @SuppressWarnings("unchecked")
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName, triggerGroupName);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                CronTrigger ct = trigger;
                ct.setCronExpression(time);
                sched.resumeJob(triggerName, triggerGroupName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param jobName
     * @Title: removeJob
     * @Description: 移除一个任务，使用默认的属性
     * @return: void
     */
    public static void removeJob(String jobName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);
            sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);
            sched.deleteJob(jobName, JOB_GROUP_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @Title: removeJob
     * @Description: 移除一个任务
     * @return: void
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseTrigger(triggerName, triggerGroupName);
            sched.unscheduleJob(triggerName, triggerGroupName);
            sched.deleteJob(jobName, jobGroupName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Title: startJobs
     * @Description: 启动所有任务
     * @return: void
     */
    public static void startJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Title: shutdownJobs
     * @Description: 关闭所有应用
     * @return: void
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
