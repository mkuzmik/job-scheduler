package com.mkuzmik.job.scheduler;

import com.mkuzmik.job.scheduler.batch.Batch;
import com.mkuzmik.job.scheduler.batch.BatchReader;
import com.mkuzmik.job.scheduler.schedule.BasicScheduleFactory;
import com.mkuzmik.job.scheduler.schedule.Schedule;
import com.mkuzmik.job.scheduler.schedule.ScheduleFactory;
import io.vavr.control.Try;

public class App {

    public static void main(String[] args) {
        BatchReader batchReader = new BatchReader();
        ScheduleFactory scheduleFactory = new BasicScheduleFactory();

        Batch batch = Try.of(() -> batchReader.fromCSVFile("src/main/resources/sample_batch.csv")).get();
        Schedule schedule = scheduleFactory.createSchedule(batch);

        System.out.println(String.format("Max cost: %d", schedule.maximumCost()));
    }
}
