package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleTest {

    private Job job1;
    private Job job2;
    private Job job3;

    ScheduledJob scheduledJob1;
    ScheduledJob scheduledJob2;
    ScheduledJob scheduledJob3;

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

    @Test
    public void shouldGetNextJobExecutionAfterGivenTimestamp() throws Exception {
        // when
        List<JobExecution> nextJobExecutions = schedule.getNextJobExecutionsAfter(5);

        // then
        JobExecution expectedJobExecution1 = new JobExecution(8, scheduledJob2);
        JobExecution expectedJobExecution2 = new JobExecution(8, scheduledJob3);

        assertThat(nextJobExecutions).containsOnly(expectedJobExecution1, expectedJobExecution2);
    }

    @Test
    public void shouldGetNextJobExecutionAfterGivenTimestamp2() throws Exception {
        // when
        List<JobExecution> nextJobExecutions = schedule.getNextJobExecutionsAfter(0);

        // then
        JobExecution expectedJobExecution = new JobExecution(1, scheduledJob2);

        assertThat(nextJobExecutions).containsOnly(expectedJobExecution);
    }

    @Test
    public void shouldGetNextJobExecutionAfterGivenTimestamp3() throws Exception {
        // given
        Job job = new Job(4,1,1,1);
        ScheduledJob scheduledJob = new ScheduledJob(0, job);

        schedule.scheduleJob(scheduledJob);

        // when
        List<JobExecution> nextJobExecutions = schedule.getNextJobExecutionsAfter(3);

        // then
        JobExecution expectedJobExecution = new JobExecution(4, scheduledJob);

        assertThat(nextJobExecutions).containsOnly(expectedJobExecution);
    }

    @Test
    public void shouldReturnProperMaximumCost() throws Exception {
        // when
        int maxCost = schedule.maximumCost();

        // then
        assertThat(maxCost).isEqualTo(7);
    }

    @Test
    public void shouldProperMaximumCost2() throws Exception {
        // given
        ScheduledJob newJob = new ScheduledJob(2000, new Job(1,1,1,20));
        schedule.scheduleJob(newJob);

        // when
        int maxCost = schedule.maximumCost();

        // then
        assertThat(maxCost).isEqualTo(25);
    }

    @Test
    public void shouldReturnZeroMaximumCostWhenScheduleIsEmpty() throws Exception {
        // given
        Schedule schedule = new Schedule();

        // when
        int maxCost = schedule.maximumCost();

        // then
        assertThat(maxCost).isEqualTo(0);
    }

    @Test
    public void shouldReturnSumOfAllJobsCostWhenAllStartAtTheSameTime() throws Exception {
        // given
        Schedule schedule = new Schedule();
        ScheduledJob newJob1 = new ScheduledJob(2000, new Job(1,35,1,20));
        schedule.scheduleJob(newJob1);
        ScheduledJob newJob2 = new ScheduledJob(2000, new Job(2,34,1,20));
        schedule.scheduleJob(newJob2);

        // when
        int maxCost = schedule.maximumCost();

        // then
        assertThat(maxCost).isEqualTo(40);
    }

    @Test
    public void shouldReturnCostOfOneJobAsMaxWhenTheyNeverSpotsEachOther() throws Exception {
        // given
        Schedule schedule = new Schedule();
        ScheduledJob newJob1 = new ScheduledJob(0, new Job(1,2,1,20));
        schedule.scheduleJob(newJob1);
        ScheduledJob newJob2 = new ScheduledJob(1, new Job(2,2,1,20));
        schedule.scheduleJob(newJob2);

        // when
        int maxCost = schedule.maximumCost();

        // then
        assertThat(maxCost).isEqualTo(20);
    }
}