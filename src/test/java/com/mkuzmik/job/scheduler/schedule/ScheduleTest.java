package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class ScheduleTest {

    private ScheduledJob scheduledJob1;
    private ScheduledJob scheduledJob2;
    private ScheduledJob scheduledJob3;

    private Job job1;
    private Job job2;
    private Job job3;

    private Schedule schedule;

    @BeforeMethod
    public void setUp() throws Exception {
        job1 = new Job(0, 5, 3, 1);
        scheduledJob1 = new ScheduledJob(0, job1);
        job2 = new Job(1, 7, 2, 2);
        scheduledJob2 = new ScheduledJob(1, job2);
        job3 = new Job(2, 3, 1, 4);
        scheduledJob3 = new ScheduledJob(2, job3);

        schedule = new Schedule();
        Arrays.asList(scheduledJob1, scheduledJob2, scheduledJob3).forEach(
                scheduledJob -> schedule.scheduleJob(scheduledJob)
        );
    }

    @Test
    public void shouldGetJobsRunningAtSpecifiedTime() throws Exception {
        // when
        Set<Job> runningJobs = schedule.getJobsRunningAt(10);

        // then
        assertThat(runningJobs).containsOnly(job1);
    }

    @Test
    public void shouldGetEmptySetWhenNoJobsRunningAtSpecifiedTime() throws Exception {
        // when
        Set<Job> runningJobs = schedule.getJobsRunningAt(13);

        // then
        assertThat(runningJobs).isEmpty();
    }

    @Test
    public void shouldGetCostAtSpecifiedTime() throws Exception {
        // when
        int cost = schedule.costAt(5);

        // then
        assertThat(cost).isEqualTo(5);
    }

    @Test
    public void shouldGetZeroCostWhenNoJobIsRunning() throws Exception {
        // when
        int cost = schedule.costAt(4);

        // then
        assertThat(cost).isEqualTo(0);
    }
}