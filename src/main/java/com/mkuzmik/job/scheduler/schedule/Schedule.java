package com.mkuzmik.job.scheduler.schedule;

import java.util.ArrayList;
import java.util.Collection;

public class Schedule {

    private Collection<ScheduledJob> scheduledJobs = new ArrayList<>();

    public void scheduleJob(ScheduledJob scheduledJob) {
        scheduledJobs.add(scheduledJob);
    }

    public Collection<ScheduledJob> getScheduledJobs() {
        return scheduledJobs;
    }
}
