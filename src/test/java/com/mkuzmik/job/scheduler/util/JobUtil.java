package com.mkuzmik.job.scheduler.util;

import com.mkuzmik.job.scheduler.batch.Job;

public class JobUtil {

    public static boolean areJobsParametersEqual(Job job1, Job job2) {
        if (job1.getId() != job2.getId()) return false;
        if (job1.getPeriod() != job2.getPeriod()) return false;
        if (job1.getCost() != job2.getCost()) return false;
        if (job1.getDuration() != job2.getDuration()) return false;
        return true;
    }
}
