package com.mkuzmik.job.scheduler.schedule;

import com.mkuzmik.job.scheduler.batch.Job;

import java.util.*;
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

    public List<JobExecution> getNextJobExecutionsAfter(int timestamp) {
        Optional<Integer> maybeNextExecutionTime = scheduledJobs.stream()
                .map(scheduledJob -> scheduledJob.nextExecutionTimeAfter(timestamp))
                .reduce(Integer::min);
        
        if (maybeNextExecutionTime.isPresent()) {
            int nextExecutionTime = maybeNextExecutionTime.get();

            return scheduledJobs.stream()
                    .filter(scheduledJob ->
                        scheduledJob.nextExecutionTimeAfter(timestamp) == nextExecutionTime)
                    .map(scheduledJob -> new JobExecution(nextExecutionTime, scheduledJob))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
