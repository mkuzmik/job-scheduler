package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Batch;
import com.mkuzmik.job.scheduler.batch.Job;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


public class BasicScheduleFactoryTest {
    
    private ScheduleFactory scheduleFactory = new BasicScheduleFactory();

    @Test
    public void shouldCreateBasicScheduleFromBatch() throws Exception {
        // given
        Batch batch = new Batch();
        Job job1 = new Job(1, 1, 1, 1);
        Job job2 = new Job(2, 1, 1, 1);
        batch.addJob(job1);
        batch.addJob(job2);

        // when
        Schedule schedule = scheduleFactory.createSchedule(batch);

        // then
        assertThat(schedule.getScheduledJobs()).containsOnly(
                new ScheduledJob(0, job1),
                new ScheduledJob(0, job2)
        );
    }

    @Test
    public void shouldCreateEmptyScheduleWhenBatchIsEmpty() throws Exception {
        // given
        Batch batch = new Batch();

        // when
        Schedule schedule = scheduleFactory.createSchedule(batch);

        // then
        assertThat(schedule.getScheduledJobs()).isEmpty();
    }
}