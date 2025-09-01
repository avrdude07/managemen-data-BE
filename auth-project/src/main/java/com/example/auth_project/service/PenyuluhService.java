package com.example.auth_project.service;

import com.example.auth_project.exception.GeneralException;
import com.example.auth_project.helper.FileDownloadHelper;
import com.example.auth_project.helper.GenerateArrayReportingHelper;
import com.example.auth_project.helper.GenerateExcelHelper;
import com.example.auth_project.model.dto.PenyuluhRequestDTO;
import com.example.auth_project.model.entity.Penyuluh;
import com.example.auth_project.repository.PenyuluhRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

        if (StringUtils.hasText(namaPenyuluh)) {
            namaPenyuluh = namaPenyuluh.toUpperCase();
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
    public void addNewStock(PenyuluhRequestDTO penyuluhRequestDTO) {
        Penyuluh penyuluh = new Penyuluh();

        penyuluh.setPenyuluhId(penyuluhRequestDTO.getPenyuluhId());
        penyuluh.setNamaPenyuluh(penyuluhRequestDTO.getNamaPenyuluh());
        penyuluh.setNipPenyuluh(penyuluhRequestDTO.getNipPenyuluh());
        penyuluh.setJenisKelamin(penyuluhRequestDTO.getJenisKelamin());
        penyuluh.setTempatLahir(penyuluhRequestDTO.getTempatLahir());
        penyuluh.setTanggalLahir(penyuluhRequestDTO.getTanggalLahir());
        penyuluh.setGolongan(penyuluhRequestDTO.getGolongan());
        penyuluh.setStatusAsn(penyuluhRequestDTO.getStatusAsn());
        penyuluh.setJabatanPenyuluh(penyuluhRequestDTO.getJabatanPenyuluh());
        penyuluh.setJurusanPenyuluh(penyuluhRequestDTO.getJurusanPenyuluh());
        penyuluh.setTempatTugas(penyuluhRequestDTO.getTempatTugas());
        penyuluh.setNomorTelepon(penyuluhRequestDTO.getNomorTelepon());
        penyuluh.setPendidikanTerakhir(penyuluhRequestDTO.getPendidikanTerakhir());
        penyuluh.setKecamatan(penyuluhRequestDTO.getKecamatan());
        penyuluh.setProvinsi(penyuluhRequestDTO.getProvinsi());

        Penyuluh checkStock= penyuluhRepository.findByNamaPenyuluh(penyuluhRequestDTO.getNamaPenyuluh());
        if (checkStock != null){
            log.error("An error occurred while updating penyuluh");
            throw new GeneralException("Data Penyuluh Sudah Ada");
        }

        Penyuluh existing = penyuluhRepository.findByNipPenyuluh(penyuluhRequestDTO.getNipPenyuluh());
        if (existing != null) {
            log.error("Duplicate NIP detected: {}", penyuluhRequestDTO.getNipPenyuluh());
            throw new GeneralException("NIP Penyuluh sudah terdaftar");
        }

        penyuluhRepository.save(penyuluh);
    }

    @Transactional
    public void updateStock(PenyuluhRequestDTO penyuluhRequestDTO) {
        Penyuluh penyuluh = penyuluhRepository.findById(penyuluhRequestDTO.getPenyuluhId())
                .orElseThrow(() -> new GeneralException("penyuluh with id " + penyuluhRequestDTO.getPenyuluhId() + " does not exist"));

        penyuluh.setPenyuluhId(penyuluhRequestDTO.getPenyuluhId());
        penyuluh.setNamaPenyuluh(penyuluhRequestDTO.getNamaPenyuluh());
        penyuluh.setNipPenyuluh(penyuluhRequestDTO.getNipPenyuluh());
        penyuluh.setJenisKelamin(penyuluhRequestDTO.getJenisKelamin());
        penyuluh.setTempatLahir(penyuluhRequestDTO.getTempatLahir());
        penyuluh.setTanggalLahir(penyuluhRequestDTO.getTanggalLahir());
        penyuluh.setGolongan(penyuluhRequestDTO.getGolongan());
        penyuluh.setStatusAsn(penyuluhRequestDTO.getStatusAsn());
        penyuluh.setJabatanPenyuluh(penyuluhRequestDTO.getJabatanPenyuluh());
        penyuluh.setJurusanPenyuluh(penyuluhRequestDTO.getJurusanPenyuluh());
        penyuluh.setTempatTugas(penyuluhRequestDTO.getTempatTugas());
        penyuluh.setNomorTelepon(penyuluhRequestDTO.getNomorTelepon());
        penyuluh.setPendidikanTerakhir(penyuluhRequestDTO.getPendidikanTerakhir());
        penyuluh.setKecamatan(penyuluhRequestDTO.getKecamatan());
        penyuluh.setProvinsi(penyuluhRequestDTO.getProvinsi());
        penyuluhRepository.save(penyuluh);
    }

    @Transactional
    public void deleteStock(Long penyuluhId) {
        boolean exists = penyuluhRepository.existsById(penyuluhId);
        if(!exists){
            log.error("An error occurred while deleting  stock");
            log.error("stock with id " + penyuluhId + " does not exists");
            throw new GeneralException("Error deleting penyuluh");
        }
        penyuluhRepository.deleteById(penyuluhId);
    }

    public Penyuluh getPenyuluhById(Long penyuluhId) {
        return penyuluhRepository.findById(penyuluhId)
                .orElseThrow(() -> new GeneralException("penyuluh with id " + penyuluhId + " does not exist"));
    }

    @SneakyThrows
    public ResponseEntity<Object> createExcelResponse(String namaPenyuluh, String fromDate, String toDate, String nipPenyuluh, int offset, int limit, String sortBy, String orderBy) {
        FileDownloadHelper downloadHelper = new FileDownloadHelper();
        String resultExcel = generateExcelMps(namaPenyuluh, fromDate, toDate, nipPenyuluh, offset, limit, sortBy, orderBy);
        if (resultExcel.contains("error")) {
           throw new GeneralException("Error at parsing excel");
        } else {
            Resource resource;
            try {
                resource = downloadHelper.getFileAsResource(resultExcel);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }
            if (resource == null) {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

            String contentType = "application/vnd.ms-excel";
            String headerValue = "ATTACHMENT" + resource.getFilename() + "\"";
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue).contentLength(resource.contentLength()).body(resource);
        }
    }

    public String generateExcelMps(String namaPenyuluh, String fromDate, String toDate, String nipPenyuluh, int offset, int limit, String sortBy, String orderBy) throws ParseException {
        Page<Penyuluh> penyuluhPage =  getPenyuluh(namaPenyuluh, fromDate, toDate, nipPenyuluh, offset, limit, sortBy, orderBy);
        List<Penyuluh> listPenyuluh = penyuluhPage.getContent();
        List<String[]> listGabung = GenerateArrayReportingHelper.listGabung(listPenyuluh);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("ddMMyyyy");
        String currentDate = dateObj.format(formatterDate);
        String excelHeader = "Reporting Penyuluh_" + currentDate;
        //String uploadPath = "D:\\application\\note\\" + excelHeader + ".xlsx";
        String uploadPath = "C:\\Users\\basaj\\Downloads\\Rekap Data Penyuluh" + excelHeader + ".xlsx";
        String reportingType = "MPS";
        String resultExcel = GenerateExcelHelper.excelWrite(uploadPath, reportingType, listGabung);
        return resultExcel;
    }
}
