package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;

import java.util.Objects;

public class ScheduledJob {

    private final int startTime;

    private final Job job;

    public ScheduledJob(int startTime, Job job) {
        this.startTime = startTime;
        this.job = job;
    }

    public int getStartTime() {
        return startTime;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledJob that = (ScheduledJob) o;
        return startTime == that.startTime &&
                Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startTime, job);
    }
}
