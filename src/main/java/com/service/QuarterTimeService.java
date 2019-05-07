package com.service;

import com.entity.QuarterTime;

import java.util.List;

public interface QuarterTimeService {
    boolean saveQuarterTime(QuarterTime quarterTime);
    List<QuarterTime> getQuarterTime();
    String getQuarter(String time);
}
