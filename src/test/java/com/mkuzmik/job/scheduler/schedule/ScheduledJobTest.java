package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class ScheduledJobTest {

    @DataProvider(name = "isRunningAt")
    public static Object[][] isRunningAtData() {
        return new Object[][] {
                {0, 2, 1, 2, true},
                {0, 2, 1, 7, false},

                {1, 2, 1, 7, true},
                {1, 2, 1, 2, false},

                {1, 5, 2, 6, true},
                {1, 5, 2, 7, true},
                {1, 5, 2, 8, false},

                {4, 6, 3, 3, false},
                {4, 6, 3, 4, true},
                {4, 6, 3, 17, true}
        };
    }

    @Test(dataProvider = "isRunningAt")
    public void shouldReturnWhetherIsRunningAtGivenTimestamp(int startTime, int period, int duration, int timestamp, boolean shouldBeRunningAt) throws Exception {
        // given
        Job job = new Job(1, period, duration, 2);
        ScheduledJob scheduledJob = new ScheduledJob(startTime, job);

        //when
        boolean isRunningAt = scheduledJob.isRunningAt(timestamp);

        //then
        assertThat(isRunningAt).isEqualTo(shouldBeRunningAt);
    }

    @Test
    public void shouldReturnNextExecutionTimeWhenJobPeriodIsEqualToDuration() throws Exception {
        // given
        Job job = new Job(1,1,1,1);
        ScheduledJob scheduledJob = new ScheduledJob(0, job);

        // when
        int nextExecutionTime = scheduledJob.nextExecutionTimeAfter(3);

        // then
        assertThat(nextExecutionTime).isEqualTo(4);
    }

    @Test
    public void shouldReturnNextExecutionTime() throws Exception {
        // given
        Job job = new Job(1,100,1,1);
        ScheduledJob scheduledJob = new ScheduledJob(0, job);

        // when
        int nextExecutionTime = scheduledJob.nextExecutionTimeAfter(1);

        // then
        assertThat(nextExecutionTime).isEqualTo(100);
    }

    @Test
    public void shouldReturnNextExecutionTimeAsStartTimeWhenTimestampIsLowerThanStartTime() throws Exception {
        // given
        Job job = new Job(1,1,1,1);
        ScheduledJob scheduledJob = new ScheduledJob(0, job);

        // when
        int nextExecutionTime = scheduledJob.nextExecutionTimeAfter(3);

        // then
        assertThat(nextExecutionTime).isEqualTo(4);
    }

    @Test
    public void shouldReturnNextExecutionTimeAsStartTimeWhenTimestampIsLowerThanZero() throws Exception {
        // given
        Job job = new Job(1,100,1,1);
        ScheduledJob scheduledJob = new ScheduledJob(0, job);

        // when
        int nextExecutionTime = scheduledJob.nextExecutionTimeAfter(-50);

        // then
        assertThat(nextExecutionTime).isEqualTo(0);
    }
}