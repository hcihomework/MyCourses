package com.repository;

import com.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses,String> {
    @Query(value = "select * from courses where teacher=?1 and approval=?2",nativeQuery = true)
    List<Courses> findByTeacherAndApproval(String teacher,boolean approval);
    @Query(value = "select * from courses where teacher=?1",nativeQuery = true)
    List<Courses> findByTeacher(String teacher);
    @Query(value = "select * from courses where approval=false",nativeQuery = true)
    List<Courses> findByApproval();
    @Query(value = "select * from courses where teacher=?1 and approval=?2",nativeQuery = true)
    List<Courses> findByApprovalAAndTeacher(String teacher,boolean approval);
}
