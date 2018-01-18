package com.mkuzmik.job.scheduler.batch;


import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Job {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private int period;

    @CsvBindByName
    private int duration;

    @CsvBindByName
    private int cost;

    public Job() {
    }

    public Job(int id, int period, int duration, int cost) {
        this.id = id;
        this.period = period;
        this.duration = duration;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id &&
                period == job.period &&
                duration == job.duration &&
                cost == job.cost;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, period, duration, cost);
    }
}
