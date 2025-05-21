package com.example.auth_project.repository;

import com.example.auth_project.model.entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findAllByOrderByIdAsc();

    @Query(value = """
            SELECT * FROM timesheets 
            WHERE maker = :empId
            """, nativeQuery = true)
    List<Timesheet> findByMakerId(@Param("empId") Long empId);

    @Query(value = """
            SELECT * FROM timesheets 
            WHERE checker = :empId
            """, nativeQuery = true)
    List<Timesheet> findByCheckerId(@Param("empId") Long empId);

    Optional<Timesheet> findById(Long id);
}
