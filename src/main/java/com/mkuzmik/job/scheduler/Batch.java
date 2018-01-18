package com.mkuzmik.job.scheduler;

import java.util.ArrayList;
import java.util.List;

public class Batch {

    private final List<Job> jobs;

    public Batch(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getJobs() {
        return new ArrayList<>(jobs);
    }
}
