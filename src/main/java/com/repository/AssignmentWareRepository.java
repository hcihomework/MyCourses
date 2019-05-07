package com.repository;

import com.entity.Assignment;
import com.entity.AssignmentWare;
import com.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentWareRepository extends JpaRepository<AssignmentWare,String> {
    @Query(value = "select * from assignment_ware where Assignment_id = ?1",nativeQuery = true)
    List<AssignmentWare> findByAssignment_id(String Assignment_id);
}
