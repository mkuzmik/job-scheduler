package com.mkuzmik.job.scheduler.batch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Batch {

    private final Set<Job> jobs;

    public Batch() {
        this.jobs = new HashSet<>();
    }

    public Batch(List<Job> jobs) {
        this.jobs = new HashSet<>(jobs);
    }

    public void addJob(Job job) {
        this.jobs.add(job);
    }

    public List<Job> getJobs() {
        return new ArrayList<>(jobs);
    }
}
