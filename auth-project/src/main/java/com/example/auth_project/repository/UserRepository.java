package com.example.auth_project.repository;

import com.example.auth_project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT COUNT(*) FROM users WHERE username = :username", nativeQuery = true)
    int countByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM users WHERE email = :email", nativeQuery = true)
    int countByEmail(@Param("email") String email);

    User findByUsername(String username);

//    @Procedure(procedureName = "register_user")
//    void registerUser(@Param("p_username") String username,
//                      @Param("p_email") String email,
//                      @Param("p_password") String password,
//                      @Param("p_created_at") Date created_at,
//                      @Param("p_updated_at") Date updated_at);

}
