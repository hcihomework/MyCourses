package com.repository;

import com.entity.Forum;
import com.entity.QuarterTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuarterTimeRepository extends JpaRepository<QuarterTime,String> {
    @Query(value = "select * from forum where begin_time < ?1 or begin_time = ?1 and end_time > ?1",nativeQuery = true)
    Optional<QuarterTime> findQuarterTimeByBeginAndEnd(String time);
}
