package com.mkuzmik.job.scheduler;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BatchReaderTest {

    private BatchReader batchReader;

    @BeforeMethod
    public void setUp() throws Exception {
        batchReader = new BatchReader();
    }

    @Test
    public void shouldCreateBatchOfValidCSVFile() throws Exception {
        // given
        Job[] expectedJobs = new Job[] {
            new Job(0, 10, 4, 2),
            new Job(1, 5, 2, 3),
            new Job(2, 10, 2, 2),
            new Job(3, 5, 1, 4)
        };

        // when
        Batch actualBatch = batchReader.fromCSVFile("src/test/resources/batch_csv/sample_batch.csv");

        // then
        assertThat(actualBatch.getJobs()).containsExactly(expectedJobs);
    }
}