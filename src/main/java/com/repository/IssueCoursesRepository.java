package com.repository;

import com.entity.Courses;
import com.entity.IssueCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueCoursesRepository extends JpaRepository<IssueCourses,String> {
    @Query(value = "select * from issue_courses i,courses c where c.teacher=?1 and i.class_id=c.id",nativeQuery = true)
    List<IssueCourses> findByTeacher(String teacher);
    @Query(value = "select * from issue_courses where approval=false",nativeQuery = true)
    List<IssueCourses> findByApproval();
    @Query(value = "select * from issue_courses where approval=true",nativeQuery = true)
    List<IssueCourses> findByApprovalThrough();
    @Query(value = "select * from issue_courses i,courses c where c.teacher=?1 and i.class_id=c.id and i.approval=?2",nativeQuery = true)
    List<IssueCourses> findByTeacherAndApproval(String teacher,boolean approval);
}
