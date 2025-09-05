package com.example.auth_project.repository;

import com.example.auth_project.model.entity.Penyuluh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PenyuluhRepository extends JpaRepository<Penyuluh, Long> {
    @Query(value = "SELECT s FROM Penyuluh s WHERE " +
            "(?1 IS NULL OR UPPER(CONCAT(s.namaPenyuluh, '')) LIKE %?1%) AND " +
            "(?2 IS NULL OR CONCAT(s.nipPenyuluh, '') LIKE %?2%)")
    Page<Penyuluh> getPenyuluhPageFilter(String namaPenyuluh, String nipPenyuluh, Pageable pageable);

    @Query(value = "SELECT s FROM Penyuluh s WHERE " +
            "(?1 IS NULL OR UPPER(CONCAT(s.namaPenyuluh, '')) LIKE %?1%) AND " +
            "(?2 IS NULL OR CONCAT(s.nipPenyuluh, '') LIKE %?2%) AND " +
            "s.makerDate BETWEEN ?3 AND ?4")
    Page<Penyuluh> getPenyuluhPageFilterWithDate(String namaPenyuluh, String nipPenyuluh, Date startDate, Date endDate, Pageable pageable);

    Penyuluh findByNamaPenyuluh(String productName);
    Penyuluh findByNipPenyuluh(String nip);

    boolean existsByNamaPenyuluh(String productName);
}
