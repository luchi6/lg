package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author luch
 * @date 2021/10/24 23:43
 */


public class QuartzMan {
    public static Scheduler createScheduler() throws SchedulerException {
        return new StdSchedulerFactory().getScheduler();
    }

    public static JobDetail createJob() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("jobName","myJob")
                .build();
    }

    public static Trigger createTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("triggerName","myTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();
    }

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = createScheduler();
        JobDetail job = createJob();
        Trigger trigger = createTrigger();
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
    }
}
