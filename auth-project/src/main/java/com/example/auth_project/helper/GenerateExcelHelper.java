package com.example.auth_project.helper;

import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GenerateExcelHelper {
    private GenerateExcelHelper() {
        // private constructor to prevent instantiation
    }

    public static String excelWrite(String uploadPath,
                                    String reportingType, List<String[]> listGenerateExcel ) {

        //Path of the file
        log.info("Generate excel, Type: {}", reportingType);

        File generateExcel = new File(uploadPath);
        String generateExcelStatus = generateExcel.getPath();

        try (FileOutputStream fos = new FileOutputStream(generateExcel)) {
            String[] tableHeader = {"Nama Penyuluh", "NIP Penyuluh", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir", "Golongan", "Jabatan Penyuluh", "Jurusan Penyuluh", "Tempat Tugas", "Nomor Telepon", "Pendidikan Terakhir", "Kecamatan", "Provinsi", "Maker Date"};
            log.info("Table header: {}", (Object) tableHeader);
            writeExcelContent(listGenerateExcel, fos, tableHeader);

        }catch (Exception e){
            generateExcelStatus = "Error generate excel " + reportingType + " with message " + e.getMessage();
            log.error(generateExcelStatus, e);
        }
        listGenerateExcel.clear();

        return generateExcelStatus;
    }

    private static void writeExcelContent(List<String[]> listGabung, FileOutputStream fos, String[] tableHeader) throws IOException {
        String[] excelContent;
        Workbook workbook = new Workbook(fos, "Application", "1.0");
        Worksheet worksheet = workbook.newWorksheet("Data");

        for (int i = 0; i < tableHeader.length ; i++) {
            worksheet.value(0, i, tableHeader[i]);
        }

        // Content Excel
        int rowIndex = 1;
        for (int i = 0; i < listGabung.size(); i++) {
            for (int j = 0; j < tableHeader.length ; j++) {
                if (j == 0) {
                    worksheet.flush();
                }
                excelContent = listGabung.get(i);
                worksheet.value(rowIndex,j,excelContent[j]);
            }
            rowIndex++;
        }

        workbook.finish();
    }
}
