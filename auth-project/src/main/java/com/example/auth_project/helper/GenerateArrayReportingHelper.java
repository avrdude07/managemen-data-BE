package com.example.auth_project.helper;

import com.example.auth_project.model.entity.Penyuluh;

import java.util.ArrayList;
import java.util.List;

public class GenerateArrayReportingHelper {

    private GenerateArrayReportingHelper() {

    }

    public static List<String[]> listGabung(List<Penyuluh> listPenyuluh) {
        List<String[]> listGabung = new ArrayList<>();

        List<String> namaPenyuluh = new ArrayList<>();
        List<String> nipPenyuluh = new ArrayList<>();
        List<String> jenisKelamin = new ArrayList<>();
        List<String> tempatLahir = new ArrayList<>();
        List<String> tanggalLahir = new ArrayList<>();
        List<String> golongan = new ArrayList<>();
        List<String> jabatanPenyuluh = new ArrayList<>();
        List<String> jurusanPenyuluh = new ArrayList<>();
        List<String> tempatTugas = new ArrayList<>();
        List<String> nomorTelepon = new ArrayList<>();
        List<String> pendidikanTerakhir = new ArrayList<>();
        List<String> kecamatan = new ArrayList<>();
        List<String> provinsi = new ArrayList<>();
        List<String> makerDate = new ArrayList<>();

        for (int i = 0; i < listPenyuluh.size(); i++) {
            namaPenyuluh.add(listPenyuluh.get(i).getNamaPenyuluh());
            nipPenyuluh.add(listPenyuluh.get(i).getNipPenyuluh().toString());
            jenisKelamin.add(listPenyuluh.get(i).getJenisKelamin());
            tempatLahir.add(listPenyuluh.get(i).getTempatLahir());
            tanggalLahir.add(listPenyuluh.get(i).getTanggalLahir().toString());
            golongan.add(listPenyuluh.get(i).getGolongan());
            jabatanPenyuluh.add(listPenyuluh.get(i).getJabatanPenyuluh());
            jurusanPenyuluh.add(listPenyuluh.get(i).getJurusanPenyuluh());
            tempatTugas.add(listPenyuluh.get(i).getTempatTugas());
            nomorTelepon.add(listPenyuluh.get(i).getNomorTelepon());
            pendidikanTerakhir.add(listPenyuluh.get(i).getPendidikanTerakhir());
            kecamatan.add(listPenyuluh.get(i).getKecamatan());
            provinsi.add(listPenyuluh.get(i).getProvinsi());
            makerDate.add(listPenyuluh.get(i).getMakerDate().toString());
        }

        String[] arrayNamaPenyuluh = namaPenyuluh.stream().toArray(String[]::new);
        String[] arrayNipPenyuluh = nipPenyuluh.stream().toArray(String[]::new);
        String[] arrayJenisKelamin = jenisKelamin.stream().toArray(String[]::new);
        String[] arrayTempatLahir = tempatLahir.stream().toArray(String[]::new);
        String[] arrayTanggalLahir = tanggalLahir.stream().toArray(String[]::new);
        String[] arrayGolongan = golongan.stream().toArray(String[]::new);
        String[] arrayJabatanPenyuluh = jabatanPenyuluh.stream().toArray(String[]::new);
        String[] arrayJurusanPenyuluh = jurusanPenyuluh.stream().toArray(String[]::new);
        String[] arrayTempatTugas = tempatTugas.stream().toArray(String[]::new);
        String[] arrayNomorTelepon = nomorTelepon.stream().toArray(String[]::new);
        String[] arrayPendidikanTerakhir = pendidikanTerakhir.stream().toArray(String[]::new);
        String[] arrayKecamatan = kecamatan.stream().toArray(String[]::new);
        String[] arrayProvinsi = provinsi.stream().toArray(String[]::new);
        String[] arrayMakerDate = makerDate.stream().toArray(String[]::new);

        String[] gabung = null;
        for (int i = 0; i < arrayNamaPenyuluh.length; i++){
            gabung = new String[]{
                    arrayNamaPenyuluh[i],
                    arrayNipPenyuluh[i],
                    arrayJenisKelamin[i],
                    arrayTempatLahir[i],
                    arrayTanggalLahir[i],
                    arrayGolongan[i],
                    arrayJabatanPenyuluh[i],
                    arrayJurusanPenyuluh[i],
                    arrayTempatTugas[i],
                    arrayNomorTelepon[i],
                    arrayPendidikanTerakhir[i],
                    arrayKecamatan[i],
                    arrayProvinsi[i],
                    arrayMakerDate[i],
            };
            listGabung.add(gabung);
        }

        return listGabung;
    }
}
