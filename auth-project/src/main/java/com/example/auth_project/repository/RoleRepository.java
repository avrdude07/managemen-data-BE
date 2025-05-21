package com.example.auth_project.repository;

import com.example.auth_project.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);

    @Query(value = "SELECT * FROM ROLES", nativeQuery = true)
    List<Role> testNativeQuery();
}
