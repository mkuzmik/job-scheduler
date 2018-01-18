package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Batch;

public interface ScheduleFactory {

    Schedule createSchedule(Batch batch);
}
