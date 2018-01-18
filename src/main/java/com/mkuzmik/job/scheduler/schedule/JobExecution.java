package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;

import java.util.Objects;

public class JobExecution {

    private int executionTime;

    private ScheduledJob scheduledJob;

    public JobExecution(int executionTime, ScheduledJob job) {
        this.executionTime = executionTime;
        this.scheduledJob = job;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public ScheduledJob getScheduledJob() {
        return scheduledJob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobExecution that = (JobExecution) o;
        return executionTime == that.executionTime &&
                Objects.equals(scheduledJob, that.scheduledJob);
    }

    @Override
    public int hashCode() {

        return Objects.hash(executionTime, scheduledJob);
    }
}
