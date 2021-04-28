package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class BarangDto {
    private Integer idBarang;
    private String namaBarang;
    private Integer jumlahBarang;

    private Integer idBeratBarang;
    private String kategoriBeratBarang;
    private String biayaKategori;

    private Integer idLayanan;
    private String kategoriLayanan;
    private Integer biayaLayanan;

    //private Integer idJarak;

    private Integer idPengirim;
    private String namaPengirim;
    private String telpPengirim;
    private String kotaPengirim;
    private String alamatPengirim;
    private String kodePosPengirim;

    private Integer idPenerima;
    private String namaPenerima;
    private String telpPenerima;
    private String kotaPenerima;
    private String alamatPenerima;
    private String kodePosPenerima;

}
