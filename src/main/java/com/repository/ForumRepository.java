package com.repository;


import com.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,String> {
    @Query(value = "select * from forum where course_id = ?1 and to_id = 'null'",nativeQuery = true)
    List<Forum> findTalk(String course_id);
    @Query(value = "select * from forum where to_id = ?1",nativeQuery = true)
    List<Forum> findReply(String to_id);
}
