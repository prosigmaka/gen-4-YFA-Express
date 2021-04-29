package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class TransaksiDto {
    //BARANG
    private Integer idBarang;
    private String namaBarang;
    private Integer jumlahBarang;

    //BERAT BARANG
    private Integer idBeratBarang;
    private String kategoriBeratBarang;
    private String biayaKategori;

    //LAYANAN
    private Integer idLayanan;
    private String kategoriLayanan;
    private Integer biayaLayanan;

    //PENGIRIM
    private Integer idPengirim;
    private String namaPengirim;
    private String telpPengirim;
    private String alamatPengirim;
    private String kodePosPengirim;

    //PENERIMA
    private Integer idPenerima;
    private String namaPenerima;
    private String telpPenerima;
    private String alamatPenerima;
    private String kodePosPenerima;

    //KOTA
    private Integer idKota;
    private String kota;
    private Integer estimasiBiaya;

    //KURIR
    private Integer idKurir;
    private String namaKurir;
    private Number noTelpKurir;
    private String fotoKurir;

    //USER
    private Integer idUser;
    private String UserName;
    private String emailUser;
    private String password;
    private String fullName;
    private Number telepon;
    private String email;

    //TRANSAKSI
    private Integer idTransaksi;
    private String statusDelivery;
    private String statusPayment;
}
