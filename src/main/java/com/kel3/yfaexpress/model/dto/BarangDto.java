package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class BarangDto {
    private Integer idBarang;
    private String namaBarang;
    private Integer jumlahBarang;
//    private String keteranganBarang;

//    private Integer idBeratBarang;
//    private String kategoriBeratBarang;
//    private String biayaKategori;

    private Integer idLayanan;
    private String kategoriLayanan;
    private Integer biayaLayanan;

    //private Integer idJarak;

    private Integer idPengirim;
    private String namaPengirim;
    private String telpPengirim;
    private Integer province_id;
    private Integer cityPengirimId;
    private String provinceName;
    private String cityName;
    private String alamatPengirim;
    private String kodePosPengirim;

    private Integer idPenerima;
    private String namaPenerima;
    private String telpPenerima;
    private Integer provinceIdPenerima;
    private Integer cityPenerimaId;
    private String provinceNamePenerima;
    private String cityNamePenerima;
    private String alamatPenerima;
    private String kodePosPenerima;

    private Integer beratBarang;

}
