package com.repository;

import com.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password,String> {
    @Query(value = "select * from password where email = ?1",nativeQuery = true)
    String findByEmail(String email);
}
