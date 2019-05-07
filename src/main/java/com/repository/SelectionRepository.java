package com.repository;

import com.entity.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SelectionRepository extends JpaRepository<Selection,String> {
    @Query(value = "select * from selection where issue_course=?1 and drop_course=false",nativeQuery = true)
    List<Selection> findByIssue_course(String issue_course);
    @Query(value = "select * from selection where student=?1 and drop_course=false",nativeQuery = true)
    List<Selection> findByStudent(String student);
    @Query(value = "select * from selection where student=?1 and issue_course = ?2 and drop_course =false ",nativeQuery = true)
    Optional<Selection> findByStudentAAndIssue_course(String student, String issue_course);
    @Query(value = "select * from selection where student=?1 and drop_course=?2",nativeQuery = true)
    List<Selection> findByStudentAndDrop_course(String student,boolean drop_course);
}
