package com.example.auth_project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[0-9]{8,18}$", message = "NIP harus berupa angka dengan panjang 8-18 digit")
    private String nipPenyuluh;
    private String jenisKelamin;
    private String tempatLahir;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date tanggalLahir;
    private String golongan;
    private String statusAsn;
    private String jabatanPenyuluh;
    private String jurusanPenyuluh;
    private String tempatTugas;
    @Pattern(regexp = "^[0-9]{12,13}$", message = "No Telepon harus berupa angka dengan panjang 12-13 digit")
    private String nomorTelepon;
    private String pendidikanTerakhir;
    private String kecamatan;
    private String provinsi;
}
