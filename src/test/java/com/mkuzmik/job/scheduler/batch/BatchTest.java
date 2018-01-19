package com.mkuzmik.job.scheduler.batch;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BatchTest {

    @Test
    public void shouldAcceptOnlyJobsWithUniqueIds() throws Exception {
        // given
        Batch batch = new Batch();
        Job job1 = new Job(1, 1, 1, 1);
        Job job2 = new Job(1, 2, 3, 4);

        // when
        batch.addJob(job1);
        batch.addJob(job2);

        // then
        assertThat(batch.getJobs()).containsExactly(job1);
    }
}