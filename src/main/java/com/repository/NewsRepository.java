package com.repository;

import com.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,String> {
    @Query(value = "select * from news where to_where = ?1",nativeQuery = true)
    List<News> findByToWhere(String to_where);
}
