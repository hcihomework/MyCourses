package com.repository;

import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {
    @Query(value = "select email from users where email=?1",nativeQuery = true)
    Optional<Users> findByEmail(String email);
    @Query(value = "select * from users where identity = ?1",nativeQuery = true)
    List<Users> findByIdentity(String identity);
    @Query(value = "delete from users where email=?1",nativeQuery = true)
    boolean deleteByEmail(String email);

}
