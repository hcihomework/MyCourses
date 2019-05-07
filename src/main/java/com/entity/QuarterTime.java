package com.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quarter_time")
public class QuarterTime {
    @Id
    private String quarter;
    private String begin_time;
    private String end_time;
    public QuarterTime(){}
    public QuarterTime(String quarter,String begin_time,String end_time){
        this.quarter=quarter;
        this.begin_time=begin_time;
        this.end_time=end_time;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
