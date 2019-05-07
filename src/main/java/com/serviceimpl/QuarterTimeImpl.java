package com.serviceimpl;

import com.entity.QuarterTime;
import com.repository.QuarterTimeRepository;
import com.service.QuarterTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
@Component
public class QuarterTimeImpl implements QuarterTimeService {
    @Autowired
    private QuarterTimeRepository quarterTimeRepository;
    private static QuarterTimeImpl quarterTimeimpl;
    @PostConstruct
    public void init(){
        quarterTimeimpl=this;
        quarterTimeimpl.quarterTimeRepository=this.quarterTimeRepository;
    }
    @Override
    public boolean saveQuarterTime(QuarterTime quarterTime) {
        quarterTimeimpl.quarterTimeRepository.save(quarterTime);
        return true;
    }

    @Override
    public List<QuarterTime> getQuarterTime() {
        return quarterTimeimpl.quarterTimeRepository.findAll();
    }

    @Override
    public String getQuarter(String time) {
        QuarterTime quarterTime=quarterTimeimpl.quarterTimeRepository.findQuarterTimeByBeginAndEnd(time).get();
        return quarterTime.getQuarter();
    }
}
