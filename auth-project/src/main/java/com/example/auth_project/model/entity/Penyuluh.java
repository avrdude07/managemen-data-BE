package com.example.auth_project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "data_penyuluh")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Penyuluh {

    @Id
    @SequenceGenerator(name = "penyuluh_sequence", sequenceName = "penyuluh_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penyuluh_sequence")
    private Long penyuluhId;

    @Column(name = "nama_penyuluh", nullable = false)
    private String namaPenyuluh;

    @Column(name = "nip_penyuluh", nullable = false, unique = true)
    private String nipPenyuluh;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;

    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @Column(name = "tanggal_lahir", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;

    @Column(name = "golongan", nullable = false)
    private String golongan;

    @Column(name = "status_asn", nullable = false)
    private String statusAsn;

    @Column(name = "jabatan_penyuluh", nullable = false)
    private String jabatanPenyuluh;

    @Column(name = "jurusan_penyuluh", nullable = false)
    private String jurusanPenyuluh;

    @Column(name = "tempat_tugas", nullable = false)
    private String tempatTugas;

    @Column(name = "nomor_telepon", nullable = false)
    private String nomorTelepon;

    @Column(name = "pendidikan_terakhir", nullable = false)
    private String pendidikanTerakhir;

    @Column(name = "kecamatan")
    private String kecamatan;

    @Column(name = "provinsi")
    private String provinsi;

    @CreatedDate
    @Column(name = "maker_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date makerDate;
}
