package com.repository;

import com.entity.Assignment;
import com.entity.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment,String> {
    @Query(value = "select * from student_assignment where assignment = ?1",nativeQuery = true)
    List<StudentAssignment> findByAssignment(String assignment);
    @Query(value = "select * from student_assignment where student=?1 and assignment = ?2",nativeQuery = true)
    Optional<StudentAssignment> findByStudentAndAssignment(String student,String assignment);
    @Query(value = "select * from student_assignment where student = ?1",nativeQuery = true)
    List<StudentAssignment> findByStudent(String student);
}
