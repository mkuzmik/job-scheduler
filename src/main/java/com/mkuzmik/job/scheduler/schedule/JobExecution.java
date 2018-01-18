package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;

import java.util.Objects;

public class JobExecution {

    private int executionTime;

    private Job job;

    public JobExecution(int executionTime, Job job) {
        this.executionTime = executionTime;
        this.job = job;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobExecution that = (JobExecution) o;
        return executionTime == that.executionTime &&
                Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {

        return Objects.hash(executionTime, job);
    }
}
