package com.repository;

import com.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment,String>{
    @Query(value = "select * from assignment where courses = ?1",nativeQuery = true)
    List<Assignment> findByCourses(String courses);
    @Query(value = "select * from assignment a,courses c,issue_courses i where c.teacher = ?1 and a.courses=i.id and i.class_id=c.id",nativeQuery = true)
    List<Assignment> findByTeacher(String teacher);
}
