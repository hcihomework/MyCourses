package com.repository;

import com.entity.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoursewareRepository extends JpaRepository<Courseware,String> {
    @Query(value = "select * from courseware where courses_id=?1",nativeQuery = true)
    List<Courseware> findByCourses_id(String courses_id);
}
