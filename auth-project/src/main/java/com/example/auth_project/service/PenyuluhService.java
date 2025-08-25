package com.example.auth_project.service;

import com.example.auth_project.model.entity.Penyuluh;
import com.example.auth_project.repository.PenyuluhRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class PenyuluhService {

    private final PenyuluhRepository penyuluhRepository;

    public Page<Penyuluh> getPenyuluh(String namaPenyuluh, String fromDate, String toDate, String nipPenyuluh, int offset, int limit, String sortBy, String orderBy) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Pageable pageable = null;
        if (orderBy.equalsIgnoreCase("DESC")){
            pageable = PageRequest.of(offset - 1, limit).withSort(Sort.by(sortBy).descending());
        } else {
            pageable = PageRequest.of(offset - 1, limit).withSort(Sort.by(sortBy).ascending());
        }

        if (StringUtils.hasText(fromDate) && StringUtils.hasText(toDate)){
            Date startDate = StringUtils.hasText(fromDate) ? formatter.parse(fromDate + " 00:00:00") : null;
            Date endDate = StringUtils.hasText(toDate) ? formatter.parse(toDate + " 23:59:59") : null;
            log.info("Masuk Filter Penyuluh Dengan Date");
            return penyuluhRepository.getPenyuluhPageFilterWithDate(namaPenyuluh, nipPenyuluh, startDate, endDate, pageable);
        } else {
            log.info("Masuk Filter Penyuluh Tanpa Date");
            return penyuluhRepository.getPenyuluhPageFilter(namaPenyuluh, nipPenyuluh, pageable);
        }
    }

    @Transactional
    public void deleteStock(Long penyuluhId) {
        boolean exists = penyuluhRepository.existsById(penyuluhId);
        if(!exists){
            log.error("An error occurred while deleting  stock");
            throw new RuntimeException("stock with id " + penyuluhId + " does not exists");
        }
        penyuluhRepository.deleteById(penyuluhId);
    }
}
