package com.usd.Orkestrasi.repository;

import com.usd.Orkestrasi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT u FROM User u WHERE LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<User> findUsersByFullNameLike(@Param("fullName") String fullName);
}

