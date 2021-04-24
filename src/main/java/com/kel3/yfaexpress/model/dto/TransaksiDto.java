package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class TransaksiDto {
    private Integer idTransaksi;

    private Integer idUser;
    private String UserName;
    private String emailUser;
    private String password;
    private String fullName;
    private Number telepon;
    private String email;

    //    copas isi dto Barang
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
    private Number telpPengirim;
    private String kotaPengirim;
    private String alamatPengirim;
    private Number kodePosPengirim;

    private Integer idPenerima;
    private String namaPenerima;
    private Number telpPenerima;
    private String kotaPenerima;
    private String alamatPenerima;
    private Number kodePosPenerima;


    private String statusDelivery;
    private String statusPayment;

    private Integer idTotalBiaya;
//    copas isi Dto TotalBiaya

    private Integer idKurir;
    private String namaKurir;
    private Number noTelpKurir;
    private String fotoKurir;
}
