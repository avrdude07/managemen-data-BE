package com.example.auth_project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PenyuluhRequestDTO {
    private Long penyuluhId;
    private String namaPenyuluh;
    private Long nipPenyuluh;
    private String jenisKelamin;
    private String tempatLahir;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date tanggalLahir;
    private String golongan;
    private String jabatanPenyuluh;
    private String jurusanPenyuluh;
    private String tempatTugas;
    private String nomorTelepon;
    private String pendidikanTerakhir;
    private String kecamatan;
    private String provinsi;
}
