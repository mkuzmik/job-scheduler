package com.mkuzmik.job.scheduler.batch;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BatchReader {

    public Batch fromCSVFile(String path) throws FileNotFoundException {
        List<Job> jobs = new CsvToBeanBuilder(new FileReader(path))
                .withType(Job.class)
                .build()
                .parse();

        return new Batch(jobs);
    }
}
