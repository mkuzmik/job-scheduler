package com.mkuzmik.job.scheduler.batch;

import java.util.ArrayList;
import java.util.List;

public class Batch {

    private final List<Job> jobs;

    public Batch() {
        this.jobs = new ArrayList<>();
    }

    public Batch(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job) {
        this.jobs.add(job);
    }

    public List<Job> getJobs() {
        return new ArrayList<>(jobs);
    }
}
