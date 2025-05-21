package com.example.auth_project.repository;

import com.example.auth_project.model.entity.Employee;
import com.example.auth_project.model.entity.Timesheet;
import com.example.auth_project.model.projection.EmployeeProjection;
import com.example.auth_project.model.projection.EmployeeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.empId AS empId, " +
            "e.empName AS empName, " +
            "e.empRole AS empRole, " +
            "e.empEmail AS empEmail, " +
            "e.empPhone AS empPhone, " +
            "e.created_at AS created_at, " +
            "e.updated_at AS updated_at, " +
            "e.user.id AS userId " +
            "FROM Employee e WHERE e.user.username = :username")
    Optional<Employee> findByUsername(@Param("username") String username);

    Optional<Employee> findByUserUsername(String username);

    List<Employee>findAllByOrderByEmpIdAsc();

    List<EmployeeSummary> findAllProjectedByOrderByEmpIdAsc();

    @Query(value = """
            SELECT * FROM employees e
            WHERE e.employee_id = :id
            """, nativeQuery = true)
    Optional<Employee> findById(@Param("id") Long id);
}
