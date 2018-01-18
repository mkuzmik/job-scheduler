package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Schedule {

    private Collection<ScheduledJob> scheduledJobs = new ArrayList<>();

    public void scheduleJob(ScheduledJob scheduledJob) {
        scheduledJobs.add(scheduledJob);
    }

    public Collection<ScheduledJob> getScheduledJobs() {
        return scheduledJobs;
    }

    public Set<Job> getJobsRunningAt(int timestamp) {
        return scheduledJobs.stream()
                .filter(scheduledJob -> scheduledJob.isRunningAt(timestamp))
                .map(ScheduledJob::getJob)
                .collect(Collectors.toSet());
    }

    public int costAt(int timestamp) {
        return getJobsRunningAt(timestamp).stream()
                .map(Job::getCost)
                .reduce(0, Integer::sum);
    }
}
