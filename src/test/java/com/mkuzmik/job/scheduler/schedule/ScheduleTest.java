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

    private Schedule schedule;

    @BeforeMethod
    public void setUp() throws Exception {
        job1 = new Job(0, 5, 3, 1);
        ScheduledJob scheduledJob1 = new ScheduledJob(0, job1);

        job2 = new Job(1, 7, 2, 2);
        ScheduledJob scheduledJob2 = new ScheduledJob(1, job2);

        job3 = new Job(2, 3, 1, 4);
        ScheduledJob scheduledJob3 = new ScheduledJob(2, job3);

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
        JobExecution expectedJobExecution1 = new JobExecution(8, job2);
        JobExecution expectedJobExecution2 = new JobExecution(8, job3);

        assertThat(nextJobExecutions).containsExactly(expectedJobExecution1, expectedJobExecution2);
    }

    @Test
    public void shouldGetNextJobExecutionAfterGivenTimestamp2() throws Exception {
        // when
        List<JobExecution> nextJobExecutions = schedule.getNextJobExecutionsAfter(0);

        // then
        JobExecution expectedJobExecution = new JobExecution(1, job2);

        assertThat(nextJobExecutions).containsExactly(expectedJobExecution);
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
        JobExecution expectedJobExecution = new JobExecution(4, job);

        assertThat(nextJobExecutions).containsExactly(expectedJobExecution);
    }
}