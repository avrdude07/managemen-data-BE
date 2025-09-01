package com.example.auth_project.controller;

import com.example.auth_project.model.dto.ErrorResponseDto;
import com.example.auth_project.model.dto.PenyuluhRequestDTO;
import com.example.auth_project.model.dto.SuccessResponseDto;
import com.example.auth_project.model.entity.Penyuluh;
import com.example.auth_project.service.PenyuluhService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/penyuluh")
@Slf4j
@RequiredArgsConstructor
public class PenyuluhController {

    private final PenyuluhService penyuluhService;

    @GetMapping
    public ResponseEntity<Object> getPenyuluh(@RequestParam(required = false) String namaPenyuluh,
                                            @RequestParam(required = false) String nipPenyuluh,
                                            @RequestParam(required = false) String fromDate,
                                            @RequestParam(required = false) String toDate,
                                            @RequestParam(defaultValue = "1") int offset,
                                            @RequestParam(defaultValue = "10") int limit,
                                            @RequestParam(defaultValue = "makerDate") String sortBy,
                                            @RequestParam(defaultValue = "DESC") String orderBy,
                                            @RequestParam(defaultValue = "N") String downloadExcel
    ){
        Map<String, Object> map = new HashMap<>();
        SuccessResponseDto successResponseDto = new SuccessResponseDto();
        try {
            if (downloadExcel.equals("N")) {
                Page<Penyuluh> page =  penyuluhService.getPenyuluh(namaPenyuluh, fromDate, toDate, nipPenyuluh, offset, limit, sortBy, orderBy);
                setMetaData(successResponseDto, map, page);
                successResponseDto.setMessage("Success Get Data From Table Penyuluh");
                return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
            }else {
                return penyuluhService.createExcelResponse(namaPenyuluh, fromDate, toDate, nipPenyuluh, offset, limit, sortBy, orderBy);
            }

        } catch (Exception e){
            ErrorResponseDto errorResponseDto = new ErrorResponseDto();
            log.error("Error Get Data From Table Penyuluh ", e);
            errorResponseDto.setErrors("Error Get Data From Table Penyuluh " + e.getMessage());
            errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static void setMetaData(SuccessResponseDto successResponseDto, Map<String, Object> map, Page<?> page){
        map.put("pageSize", String.valueOf(page.getPageable().getPageSize()));
        map.put("currentData", String.valueOf(page.getPageable().getOffset() + 1));
        map.put("currentPage", String.valueOf(page.getPageable().getPageNumber() + 1));
        map.put("totalPage", String.valueOf(page.getTotalPages()));
        map.put("totalData", String.valueOf(page.getTotalElements()));

        successResponseDto.setData(page.getContent());
        successResponseDto.setPaging(map);
        successResponseDto.setStatus(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SuccessResponseDto> addStock(@RequestBody PenyuluhRequestDTO penyuluhRequestDTO) {
        penyuluhService.addNewStock(penyuluhRequestDTO);
        SuccessResponseDto successResponseDto = new SuccessResponseDto();
        successResponseDto.setStatus(HttpStatus.OK);
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<SuccessResponseDto> updateStock(@RequestBody PenyuluhRequestDTO penyuluhRequestDTO) {
        penyuluhService.updateStock(penyuluhRequestDTO);
        SuccessResponseDto successResponseDto = new SuccessResponseDto();
        successResponseDto.setStatus(HttpStatus.OK);
        return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{penyuluhId}")
    public ResponseEntity<SuccessResponseDto> deleteStock(@PathVariable("penyuluhId") Long penyuluhId) {
        penyuluhService.deleteStock(penyuluhId);
        return new ResponseEntity<>(new SuccessResponseDto(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/{penyuluhId}")
    public ResponseEntity<Object> getPenyuluhById(@PathVariable("penyuluhId") Long penyuluhId) {
        Penyuluh penyuluh = penyuluhService.getPenyuluhById(penyuluhId);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("response", penyuluh);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
