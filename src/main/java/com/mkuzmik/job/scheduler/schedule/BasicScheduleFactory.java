package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Batch;

public class BasicScheduleFactory implements ScheduleFactory {

    @Override
    public Schedule createSchedule(Batch batch) {
        Schedule schedule = new Schedule();

        batch.getJobs().forEach((job) -> {
            ScheduledJob scheduledJob = new ScheduledJob(0, job);
            schedule.scheduleJob(scheduledJob);
        });

        return schedule;
    }
}
